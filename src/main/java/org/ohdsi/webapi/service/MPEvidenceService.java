package org.ohdsi.webapi.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ohdsi.webapi.source.Source;

import org.ohdsi.webapi.mpevidence.Statement;
import org.ohdsi.webapi.mpevidence.Claim;
import org.ohdsi.webapi.mpevidence.CTClaim;
import org.ohdsi.webapi.mpevidence.CRClaim;
import org.ohdsi.webapi.mpevidence.PhenotypeClaim;
import org.ohdsi.webapi.mpevidence.DrugEntity;
import org.ohdsi.webapi.mpevidence.Method;
import org.ohdsi.webapi.mpevidence.Evidence;
import org.ohdsi.webapi.mpevidence.CTEvidence;
import org.ohdsi.webapi.mpevidence.CREvidence;
import org.ohdsi.webapi.mpevidence.PhenotypeEvidence;

import org.ohdsi.webapi.helper.ResourceHelper;
import org.springframework.stereotype.Component;


@Path("mpevidence/")
@Component
public class MPEvidenceService  extends AbstractDaoService {

    // concept code mapping initialization
    private static final Map<String, String> conceptCodeMap;
    static {
	conceptCodeMap = new HashMap<String, String>();
	conceptCodeMap.put("precipitant", "DIDEO_00000013");
	conceptCodeMap.put("object", "DIDEO_00000012");
    }
    

    /** get all drug concept names
     * @param drug concept name 1
     * @param drug role 1
    */
    @GET
    @Path("{sourceKey}/drugname/{drugRole}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getAllDrugName(@PathParam("sourceKey") String sourceKey, @PathParam("drugRole") String drugRole) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getAllDrugNames.sql");
	
	if (drugRole.equals("precipitant") || drugRole.equals("object")) {
	    sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getAllDrugNamesByRole.sql");
	    sql_statement = sql_statement.replaceAll("@roleconceptcode", conceptCodeMap.get(drugRole));
	}

	List<DrugEntity> drugList = new ArrayList<DrugEntity>();	    
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	for (Map rs: rows) {
	    DrugEntity item = new DrugEntity();
	    item.drugConceptName = (String) rs.get("concept_name");
	    item.drugConceptCode = (String) rs.get("concept_code");
	    item.vocabularyId = (String) rs.get("vocabulary_id");
	    item.conceptClassId = (String) rs.get("concept_class_id");
	    drugList.add(item);
	}
	return drugList;	  
    }


    /** get options of 2nd drug concept name based on specified 1st drug URI
     * @param drug URI <vocabId>-<concpet_code> 1
     * @param drug role 1
     * @return list of DrugEntity as 2nd drug options
     */
    @GET
    @Path("{sourceKey}/drugname2/{drug1URI}/{drug1Role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getSecondDrugName(@PathParam("sourceKey") String sourceKey, @PathParam("drug1URI") final String drug1URI, @PathParam("drug1Role") final String drug1Role) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getSecondDrugName.sql");
	
	if (drug1Role.equals("precipitant") || drug1Role.equals("object")) {
	    sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getSecondDrugNameWithRole.sql");
	    sql_statement = sql_statement.replaceAll("@roleconceptcode", conceptCodeMap.get(drug1Role));
	}

        String [] uriList = drug1URI.split("-");
	String vocabularyId = uriList[0];
	String conceptCode = uriList[1];	
    	sql_statement = sql_statement.replaceAll("@conceptcode", conceptCode).replaceAll("@vocabularyid", vocabularyId).replaceAll("@roleconceptcode", "");

	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<DrugEntity> drugList = new ArrayList<DrugEntity>();
	Set<String> existsCode = new HashSet<String>();
	MPEvidenceService service = new MPEvidenceService();
	
	for (Map rs: rows) {
	    String subject = (String) rs.get("subject");
	    String subjectVocabularyId = (String) rs.get("s_vocabulary_id");	    
	    String subjectConceptCode = (String) rs.get("s_concept_code");
	    String object = (String) rs.get("object");
	    String objectVocabularyId = (String) rs.get("o_vocabulary_id");
	    String objectConceptCode = (String) rs.get("o_concept_code");
	    
	    if (subjectConceptCode.equals(conceptCode) && !objectConceptCode.equals(conceptCode) && !existsCode.contains(objectConceptCode)) {
		drugList.add(service.createDrugEntity(object, objectVocabularyId, objectConceptCode));
		existsCode.add(objectConceptCode);
	    } else {
		if (!existsCode.contains(subjectConceptCode) && !subjectConceptCode.equals(conceptCode)) {
		    drugList.add(service.createDrugEntity(subject, subjectVocabularyId, subjectConceptCode));
		    existsCode.add(subjectConceptCode);	       
		}
	    }
	}
	return drugList;	  
    }


    /**
     * Get method by two drug names
     * @param 1st drug URI <vocabId>-<concpet_code>
     * @param 2nd drug URI <vocabId>-<concpet_code>
     * @param drug role 1
     * @return list of Method
     */
    @GET
    @Path("{sourceKey}/method/{drugURI1}/{drugURI2}/{drug1Role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Method> getEvidenceType(@PathParam("sourceKey") String sourceKey, @PathParam("drugURI1") final String drugURI1, @PathParam("drugURI2") final String drugURI2, @PathParam("drug1Role") final String drug1Role) throws Exception {
    	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMethodByDrugNames.sql");

	if (drug1Role.equals("precipitant") || drug1Role.equals("object")) {
	    sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMethodByDrugNamesAndRole.sql");
	    sql_statement = sql_statement.replaceAll("@roleconceptcode1", conceptCodeMap.get(drug1Role));
	}
	
	String [] uriList1 = drugURI1.split("-");
	String vocabularyId1 = uriList1[0];
	String conceptCode1 = uriList1[1];

	String [] uriList2 = drugURI2.split("-");
	String vocabularyId2 = uriList2[0];
	String conceptCode2 = uriList2[1];
	
    	sql_statement = sql_statement.replaceAll("@conceptcode1", conceptCode1).replaceAll("@conceptcode2", conceptCode2).replaceAll("@vocabularyid1", vocabularyId1).replaceAll("@vocabularyid2", vocabularyId2);
	
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
    
    
    /**
     * @param drug concept name 1
     * @param drug concept name 2
     * @param method
     * @param drug role 1
     * @return
     */
    @GET
    @Path("{sourceKey}/search/{drugname1}/{drugname2}/{method}/{drug1Role}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Object> searchEvidence(@PathParam("sourceKey") String sourceKey, @PathParam("drugname1") final String drugname1, @PathParam("drugname2") final String drugname2, @PathParam("method") final String method, @PathParam("drug1Role") final String drug1Role) throws Exception {
    	Source source = getSourceRepository().findBySourceKey(sourceKey);
    	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getClaimByMethodAndDrug.sql");
	String methodDecoded = method.replaceAll("-", " ");

	if (drug1Role.equals("precipitant") || drug1Role.equals("object")) {
	    sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getClaimByMethodAndDrugAndRole.sql");
	    sql_statement = sql_statement.replaceAll("@roleconceptcode1", conceptCodeMap.get(drug1Role));
	}
	
    	// query claim by drug names and evidenceType 
	sql_statement = sql_statement.replaceAll("@conceptname1", drugname1).replaceAll("@conceptname2", drugname2).replaceAll("@method", methodDecoded);	    
    	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<Object> claims = new ArrayList<Object>();
	
	for (Map rs: rows) {	    
	    int claimId = (Integer) rs.get("mp_claim_id");	    
	    String claimText = (String) rs.get("claim_text");
	    String subject = (String) rs.get("subject");
	    String object = (String) rs.get("object");
	    String relationship = (String) rs.get("relationship");
		
	    // query data for claim
	    String data_sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getDataByClaimId.sql");
	    data_sql_statement = data_sql_statement.replace("@claimId", String.valueOf(claimId));		
	    List<Map<String, Object>> dataRows = getSourceJdbcTemplate(source).queryForList(data_sql_statement);
	    
	    // query material for claim
	    String material_sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMaterialByClaimId.sql");
	    material_sql_statement = material_sql_statement.replace("@claimId", String.valueOf(claimId));		
	    List<Map<String, Object>> materialRows = getSourceJdbcTemplate(source).queryForList(material_sql_statement);
	    
	    MPEvidenceService service = new MPEvidenceService();
	    
	    if (method.equals("DDI-clinical-trial")) {

		CTClaim ctClaim = new CTClaim(claimId, subject, object, methodDecoded, relationship, claimText);		
		HashMap<Integer, CTEvidence> ctEvidenceMap = service.parseCTEvidences(dataRows, materialRows, subject, object, claimId);
		ctClaim.evidences = ctEvidenceMap;
		claims.add(ctClaim);
		
	    } else if (method.equals("Statement")) {
		Claim claim = new Claim(claimId, subject, object, methodDecoded, relationship, claimText);
		claims.add(claim);
		
	    } else if (method.equals("Phenotype clinical study")) {
		PhenotypeClaim phClaim = new PhenotypeClaim(claimId, subject, object, methodDecoded, relationship, claimText);
		claims.add(phClaim);
		
	    } else if (method.equals("Case-Report")) {
		CRClaim crClaim = new CRClaim(claimId, subject, object, methodDecoded, relationship, claimText);
		HashMap<Integer, CREvidence> crEvidenceMap = service.parseCREvidences(dataRows, materialRows, subject, object, claimId);
		crClaim.evidences = crEvidenceMap;
		claims.add(crClaim);
	    }
	}
	return claims;
    }

    
     /**
     * @param Clinical trial data query results 
     * @param Clinical trial material query results
     * @return
     */
    private HashMap<Integer, CTEvidence> parseCTEvidences(List<Map<String, Object>> dataRows, List<Map<String, Object>> materialRows, String subject, String object, int claimId) {
	HashMap <Integer, CTEvidence> ctEvidenceMap = new HashMap<Integer, CTEvidence>();
	
	for (Map dataRs: dataRows) {
	    int dataIdx = (Integer) dataRs.get("mp_data_index");
	    String dataType = (String) dataRs.get("data_type");
	    String dataFieldType = (String) dataRs.get("data_field_type");
	    String valueStr = (String) dataRs.get("value_as_string");
	    Boolean evRelationship = (Boolean) dataRs.get("ev_supports");
	    
	    CTEvidence ctEvidence;
	    if (ctEvidenceMap.containsKey(dataIdx)) 
		ctEvidence = ctEvidenceMap.get(dataIdx);
	    else 
		ctEvidence = new CTEvidence(claimId, dataIdx, evRelationship);
		    
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
	    Boolean evRelationship = (Boolean) materialRs.get("ev_supports");
	    
	    CTEvidence ctEvidence;
	    if (ctEvidenceMap.containsKey(materialIdx))
		ctEvidence = ctEvidenceMap.get(materialIdx);
	    else
		ctEvidence = new CTEvidence(claimId, materialIdx, evRelationship);
	    
		    
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
	    } else if (materialType.equals("participants")) {
		if (materialFieldType.equals("value")) {
		    ctEvidence.participants = valueStr;
		}
	    }    
	    ctEvidenceMap.put(materialIdx, ctEvidence);
	}
	
	return ctEvidenceMap;
    }


    /**
     * @param Case report data query results 
     * @param Case report material query results
     * @return
     */
    private HashMap<Integer, CREvidence> parseCREvidences(List<Map<String, Object>> dataRows, List<Map<String, Object>> materialRows, String subject, String object, int claimId) {
	HashMap <Integer, CREvidence> crEvidenceMap = new HashMap<Integer, CREvidence>();
	
	for (Map dataRs: dataRows) {
	    int dataIdx = (Integer) dataRs.get("mp_data_index");
	    String dataType = (String) dataRs.get("data_type");
	    String dataFieldType = (String) dataRs.get("data_field_type");
	    String valueStr = (String) dataRs.get("value_as_string");
	    Boolean evRelationship = (Boolean) dataRs.get("ev_supports");
	    
	    CREvidence crEvidence;
	    if (crEvidenceMap.containsKey(dataIdx)) {
		crEvidence = crEvidenceMap.get(dataIdx);
	    } else {
		crEvidence = new CREvidence(claimId, dataIdx, evRelationship);
	    }
	    
	    if (dataType.equals("reviewer")) {
		if (dataFieldType.equals("reviewer")) {
		    crEvidence.reviewer = valueStr;
		} else if (dataFieldType.equals("date")) {
		    crEvidence.reviewerdate = valueStr;
		} else if (dataFieldType.equals("total")) {
		    crEvidence.reviewertotal = valueStr;
		} else if (dataFieldType.equals("lackinfo")) {
		    crEvidence.reviewerlackinfo = valueStr;
		}
	    } else if (dataType.equals("dipsquestion")) {
		// dataFieldType (q1, q2 - q10)
		System.out.println(crEvidence.dipsquestion);
		if (crEvidence.dipsquestion == null) {
		    crEvidence.dipsquestion = new HashMap<String, String>();
		}
		crEvidence.dipsquestion.put(dataFieldType, valueStr);		
	    } 
	    crEvidenceMap.put(dataIdx, crEvidence);
	}

	for (Map materialRs: materialRows) {
	    int materialIdx = (Integer) materialRs.get("mp_data_index");
	    String materialType = (String) materialRs.get("material_type");
	    String materialFieldType = (String) materialRs.get("material_field_type");
	    String valueStr = (String) materialRs.get("value_as_string");
	    Boolean evRelationship = (Boolean) materialRs.get("ev_supports");
	    
	    CREvidence crEvidence;
	    if (crEvidenceMap.containsKey(materialIdx))
		crEvidence = crEvidenceMap.get(materialIdx);
	    else
		crEvidence = new CREvidence(claimId, materialIdx, evRelationship);
	    		    
	    if (materialType.equals("precipitant_dose")) {
		if (materialFieldType.equals("value")) {
		    crEvidence.dose1 = valueStr;
		} else if (materialFieldType.equals("drugname")) {
		    crEvidence.dose1Name = valueStr;
		} else if (materialFieldType.equals("duration")) {
		    crEvidence.dose1Duration = valueStr;
		} else if (materialFieldType.equals("formulation")) {
		    crEvidence.dose1Formulation = valueStr;
		} else if (materialFieldType.equals("regimens")) {
		    crEvidence.dose1Regimens = valueStr;
		}		
	    } else if (materialType.equals("object_dose")) {
		if (materialFieldType.equals("value")) {
		    crEvidence.dose2 = valueStr;
		} else if (materialFieldType.equals("drugname")) {
		    crEvidence.dose2Name = valueStr;
		} else if (materialFieldType.equals("duration")) {
		    crEvidence.dose2Duration = valueStr;
		} else if (materialFieldType.equals("formulation")) {
		    crEvidence.dose2Formulation = valueStr;
		} else if (materialFieldType.equals("regimens")) {
		    crEvidence.dose2Regimens = valueStr;
		}		
	    }	    
	    crEvidenceMap.put(materialIdx, crEvidence);
	}
	
	return crEvidenceMap;
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
	    int claimId = (Integer) rs.get("mp_claim_id");
	    String method = (String) rs.get("method");
	    String methodDecoded = method.replaceAll("-", " ");	    
	    String subject = (String) rs.get("subject");
	    String object = (String) rs.get("object");
	    String relationship = (String) rs.get("relationship");
	    String claimText = (String) rs.get("claim_text");
	    
	    Claim claim = new Claim(claimId, subject, object, methodDecoded, relationship, claimText);	    
	    claimList.add(claim);
	}
	return claimList;
    }


    /** create drug entity instance
     * @param drug concept name
     * @param vocabulary id
     * @param drug concept code
     * @return
     */
    private DrugEntity createDrugEntity(String conceptName, String vocabularyId, String conceptCode) {
	DrugEntity item = new DrugEntity();
	item.drugConceptName = conceptName;
	item.vocabularyId = vocabularyId;
	item.drugConceptCode = conceptCode;
	return item;
    }

}
