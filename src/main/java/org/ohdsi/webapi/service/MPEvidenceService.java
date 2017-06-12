package org.ohdsi.webapi.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ohdsi.webapi.source.Source;

import org.ohdsi.webapi.mpevidence.Statement;
import org.ohdsi.webapi.mpevidence.Claim;
import org.ohdsi.webapi.mpevidence.CTClaim;
import org.ohdsi.webapi.mpevidence.DrugEntity;
import org.ohdsi.webapi.mpevidence.Method;
import org.ohdsi.webapi.mpevidence.Evidence;
import org.ohdsi.webapi.mpevidence.CTEvidence;

import org.ohdsi.webapi.helper.ResourceHelper;
import org.springframework.stereotype.Component;

@Path("mpevidence/")
@Component
public class MPEvidenceService  extends AbstractDaoService {

    // get all drug names
    @GET
    @Path("{sourceKey}/drugname")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getAllDrugName(@PathParam("sourceKey") String sourceKey) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getAllDrugNames.sql");
	List<DrugEntity> drugList = new ArrayList<DrugEntity>();	    
	List<String> filter = new ArrayList<String>();

	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	for (Map rs: rows) {
	    DrugEntity item = new DrugEntity();
	    item.drugConceptName = (String) rs.get("concept_name");
	    item.drugConceptCode = (String) rs.get("concept_code");
	    drugList.add(item);
	    }
	return drugList;	  
    }


    // get 2nd drug names based on specified 1st drug
    @GET
    @Path("{sourceKey}/drugname/{drugConceptName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getAllPrecipitantDrugName(@PathParam("sourceKey") String sourceKey, @PathParam("drugConceptName") final String drugConceptName) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getSecondDrugName.sql");
    	sql_statement = sql_statement.replaceAll("@drugconceptname", drugConceptName);

	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<DrugEntity> drugList = new ArrayList<DrugEntity>();
	
	for (Map rs: rows) {
	    String subject = (String) rs.get("subject");
	    String subjectConceptCode = (String) rs.get("s_concept_code");
	    String object = (String) rs.get("object");
	    String objectConceptCode = (String) rs.get("o_concept_code");
	    
	    DrugEntity item = new DrugEntity();
	    if (subject.equals(drugConceptName)) {
		item.drugConceptName = object;
		item.drugConceptCode = objectConceptCode;
	    } else {
		item.drugConceptName = subject;
		item.drugConceptCode = subjectConceptCode;
	    }
	    drugList.add(item);
	}
	return drugList;	  
    }


    /**
     * Get method by two drug names
     * @param object drug name
     * @param precipitant drug name
     * @return
     */
    @GET
    @Path("{sourceKey}/method/{drugname1}/{drugname2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Method> getEvidenceType(@PathParam("sourceKey") String sourceKey, @PathParam("drugname1") final String drugname1, @PathParam("drugname2") final String drugname2) throws Exception {
    	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMethodByDrugNames.sql");
    	sql_statement = sql_statement.replaceAll("@drugconceptname1", drugname1).replaceAll("@drugconceptname2", drugname2);
	System.out.println(sql_statement);
	
    	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
    	List<Method> methodList = new ArrayList<Method>();
        
    	for (Map rs: rows) { 	    	    	 	    
    	    Method item = new Method();
	    item.method = (String) rs.get("entered_value");
	    item.inferredMethod = (String) rs.get("entered_value");
    	    item.sourceNum = (Long) rs.get("cnts");
    	    methodList.add(item);
    	}
    	return methodList;	  
    }


    @GET
    @Path("{sourceKey}/claim")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Claim> getAllClaim(@PathParam("sourceKey") String sourceKey) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getAllMPClaim.sql");
	List<Claim> claimList = new ArrayList<Claim>();
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	
	for (Map rs: rows) {
	    
	    Claim claim = new Claim();	    
	    claim.label = (String) rs.get("label");
	    claim.method = (String) rs.get("method");
	    claim.subject = (String) rs.get("subject");
	    claim.object = (String) rs.get("object");
	    claim.claim_text = (String) rs.get("claim_text");
	    claimList.add(claim);
	}
	return claimList;
    }
    
    /**
     * @param drug name 1
     * @param drug name 2
     * @param method
     * @return
     */
    @GET
    @Path("{sourceKey}/search/{drugname1}/{drugname2}/{method}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CTClaim> searchEvidence(@PathParam("sourceKey") String sourceKey, @PathParam("drugname1") final String drugname1, @PathParam("drugname2") final String drugname2, @PathParam("method") final String method) throws Exception {
    	Source source = getSourceRepository().findBySourceKey(sourceKey);
    	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getClaimByMethodAndDrug.sql");
	String methodDecoded = method.replaceAll("-", " ");
	
    	// query by label, drug and evidenceType if source type not "other"
	sql_statement = sql_statement.replaceAll("@drugname1", drugname1).replaceAll("@drugname2", drugname2).replaceAll("@method", methodDecoded);	    
    	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);

	if (method.equals("DDI-clinical-trial")) {
	    List<CTClaim> ctClaims = new ArrayList<CTClaim>();
	    for (Map rs: rows) {

		// claim information
		CTClaim claim = new CTClaim();
		claim.mp_claim_id  = (Integer) rs.get("mp_claim_id");
		claim.claim_text = (String) rs.get("claim_text");
		claim.method = methodDecoded;
		claim.subject = (String) rs.get("subject");
		claim.object = (String) rs.get("object");
		claim.relationship = (String) rs.get("relationship");

		// query data for claim
		String data_sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getDataByClaimId.sql");
		data_sql_statement = data_sql_statement.replace("@claimId", String.valueOf(claim.mp_claim_id));		
		List<Map<String, Object>> dataRows = getSourceJdbcTemplate(source).queryForList(data_sql_statement);
		
		// query material for claim
		String material_sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMaterialByClaimId.sql");
		material_sql_statement = material_sql_statement.replace("@claimId", String.valueOf(claim.mp_claim_id));		
		List<Map<String, Object>> materialRows = getSourceJdbcTemplate(source).queryForList(material_sql_statement);

		MPEvidenceService service = new MPEvidenceService();
		HashMap<Integer, CTEvidence> ctEvidenceMap = service.parseCTEvidences(dataRows, materialRows, claim.subject, claim.object, claim.mp_claim_id);		
		claim.evidences = ctEvidenceMap;
		
		ctClaims.add(claim); 	    
	    }
	    return ctClaims;	  
	}

	return null;    
    }

    
     /**
     * @param CT data query results 
     * @param CT material query results
     * @return
     */
    private HashMap<Integer, CTEvidence> parseCTEvidences(List<Map<String, Object>> dataRows, List<Map<String, Object>> materialRows, String subject, String object, int claimId) {
	HashMap <Integer, CTEvidence> ctEvidenceMap = new HashMap<Integer, CTEvidence>();
	
	for (Map dataRs: dataRows) {
	    int dataIdx = (Integer) dataRs.get("mp_data_index");
	    String dataType = (String) dataRs.get("data_type");
	    String dataFieldType = (String) dataRs.get("data_field_type");
	    String valueStr = (String) dataRs.get("value_as_string");
	    
	    CTEvidence ctEvidence;
	    if (ctEvidenceMap.containsKey(dataIdx)) {
		ctEvidence = ctEvidenceMap.get(dataIdx);
	    } else {
		ctEvidence = new CTEvidence();
		ctEvidence.mp_claim_id = claimId;
		ctEvidence.evidence_index = dataIdx;
	    }
		    
	    if (dataType.equals("auc")) {
		if (dataFieldType.equals("value")) {
		    ctEvidence.auc = valueStr;
		} else if (dataFieldType.equals("type")) {
		    ctEvidence.aucType = valueStr;
		} else if (dataFieldType.equals("direction")) {
		    ctEvidence.aucDirection = valueStr;
		}
	    } else if (dataType.equals("cmax")) {
		if (dataFieldType.equals("value")) {
		    ctEvidence.cmax = valueStr;
		} else if (dataFieldType.equals("type")) {
		    ctEvidence.cmaxType = valueStr;
		} else if (dataFieldType.equals("direction")) {
		    ctEvidence.cmaxDirection = valueStr;
		}
	    } else if (dataType.equals("clearance")) {
		if (dataFieldType.equals("value")) {
		    ctEvidence.clearance = valueStr;
		} else if (dataFieldType.equals("type")) {
		    ctEvidence.clearanceType = valueStr;
		} else if (dataFieldType.equals("direction")) {
		    ctEvidence.clearanceDirection = valueStr;
		}
	    } else if (dataType.equals("halflife")) {
		if (dataFieldType.equals("value")) {
		    ctEvidence.halflife = valueStr;
		} else if (dataFieldType.equals("type")) {
		    ctEvidence.halflifeType = valueStr;
		} else if (dataFieldType.equals("direction")) {
		    ctEvidence.halflifeDirection = valueStr;
		}
	    }
	    ctEvidenceMap.put(dataIdx, ctEvidence);
	}


	for (Map materialRs: materialRows) {
	    int materialIdx = (Integer) materialRs.get("mp_data_index");
	    String materialType = (String) materialRs.get("material_type");
	    String materialFieldType = (String) materialRs.get("material_field_type");
	    String valueStr = (String) materialRs.get("value_as_string");
	    
	    CTEvidence ctEvidence;
	    if (ctEvidenceMap.containsKey(materialIdx)) {
		ctEvidence = ctEvidenceMap.get(materialIdx);
	    } else {
		ctEvidence = new CTEvidence();
		ctEvidence.mp_claim_id = claimId;
		ctEvidence.evidence_index = materialIdx;
	    }
		    
	    if (materialType.equals("precipitant_dose")) {
		if (materialFieldType.equals("value")) {
		    ctEvidence.dose1 = valueStr;
		} else if (materialFieldType.equals("drugname")) {
		    ctEvidence.dose1Name = valueStr;
		} else if (materialFieldType.equals("duration")) {
		    ctEvidence.dose1Duration = valueStr;
		} else if (materialFieldType.equals("formulation")) {
		    ctEvidence.dose1Formulation = valueStr;
		} else if (materialFieldType.equals("regimens")) {
		    ctEvidence.dose1Regimens = valueStr;
		}		
	    } else if (materialType.equals("object_dose")) {
		if (materialFieldType.equals("value")) {
		    ctEvidence.dose2 = valueStr;
		} else if (materialFieldType.equals("drugname")) {
		    ctEvidence.dose2Name = valueStr;
		} else if (materialFieldType.equals("duration")) {
		    ctEvidence.dose2Duration = valueStr;
		} else if (materialFieldType.equals("formulation")) {
		    ctEvidence.dose2Formulation = valueStr;
		} else if (materialFieldType.equals("regimens")) {
		    ctEvidence.dose2Regimens = valueStr;
		}		
	    }	    
	    ctEvidenceMap.put(materialIdx, ctEvidence);
	}
	
	return ctEvidenceMap;
    }

}
