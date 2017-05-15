package org.ohdsi.webapi.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ohdsi.webapi.source.Source;

import org.ohdsi.webapi.mpevidence.Claim;
import org.ohdsi.webapi.mpevidence.DrugEntity;
import org.ohdsi.webapi.mpevidence.Method;

import org.ohdsi.webapi.helper.ResourceHelper;
import org.springframework.stereotype.Component;

@Path("mpevidence/")
@Component
public class MPEvidenceService  extends AbstractDaoService {

    @GET
    @Path("{sourceKey}/claim")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Claim> getAllClaim(@PathParam("sourceKey") String sourceKey) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getAllMPClaim.sql");
	List<Claim> claimList = new ArrayList<Claim>();
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	
	for (Map rs: rows) {
	    System.out.println((String) rs.get("label"));
	    System.out.println((String) rs.get("method"));
	    
	    Claim claim = new Claim();	    
	    claim.label = (String) rs.get("label");
	    claim.method = (String) rs.get("method");
	    claim.precipitant = (String) rs.get("precipitant");
	    claim.object = (String) rs.get("object");
	    claim.claim_text = (String) rs.get("claim_text");
	    claimList.add(claim);
	}
	return claimList;
    }

    	
    @GET
    @Path("{sourceKey}/objectdrugname")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getAllObjectDrugName(@PathParam("sourceKey") String sourceKey) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getObjectDrugNames.sql");
	List<DrugEntity> drugList = new ArrayList<DrugEntity>();	    
	List<String> filter = new ArrayList<String>();

	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	for (Map rs: rows) {
	    DrugEntity item = new DrugEntity();
	    item.drugName = (String) rs.get("qvalue");
	    drugList.add(item);
	    }
	return drugList;	  
    }


    // get precipitant drug names based on specified object drug
    @GET
    @Path("{sourceKey}/precipitantdrugname/{objectname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DrugEntity> getAllPrecipitantDrugName(@PathParam("sourceKey") String sourceKey, @PathParam("objectname") final String objectname) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getPrecipitantDrugNames.sql");

	System.out.println(sql_statement);
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<DrugEntity> drugList = new ArrayList<DrugEntity>();
	
	for (Map rs: rows) {
	    String precipitant = (String) rs.get("precipitant_name");
	    String object = (String) rs.get("object_name");
	    if (object.equals(objectname)) {
		DrugEntity item = new DrugEntity();
		item.drugName = precipitant;
		drugList.add(item);
	    }
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
    @Path("{sourceKey}/method/{objectname}/{precipitantname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Method> getEvidenceType(@PathParam("sourceKey") String sourceKey, @PathParam("objectname") final String objectname, @PathParam("precipitantname") final String precipitantname) throws Exception {
    	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getMethodByDrugNames.sql");
    	sql_statement = sql_statement.replaceAll("@object", objectname).replaceAll("@precipitant", precipitantname);
	
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

    
    // /**
    //  * @param assertion label
    //  * @param precipitant drug name
    //  * @param evidence source
    //  * @return
    //  */
    // @GET
    // @Path("{sourceKey}/search/{label}/{drug}/{evType}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Collection<DetailsDBModel> searchEvidence(@PathParam("sourceKey") String sourceKey, @PathParam("label") final String label, @PathParam("drug") final String drug, @PathParam("evType") final String evType) throws Exception {
    // 	Source source = getSourceRepository().findBySourceKey(sourceKey);
    // 	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/searchEvidenceByLabel.sql");
	
    // 	// query by label, drug and evidenceType if source type not "other"
    // 	if(evType.equalsIgnoreCase("other")) { 
    // 	    sql_statement += "'%" + label + "_%' and researchStatementLabel LIKE '%" + drug + "%' and evidencetype='' order by assertType, researchStatementLabel;";
    // 	} else {
    // 	    sql_statement += "'%" + label + "_%' and researchStatementLabel LIKE '%" + drug + "%' and evidencetype like '%" + evType + "%' order by assertType, researchStatementLabel;";
    // 	}
    // 	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
    // 	List<DetailsDBModel> evidenceList = new ArrayList<DetailsDBModel>();
	
    // 	for (Map rs: rows) { 	    	    		    
    // 	    DetailsDBModel evidence = new DetailsDBModel();
    // 	    String assertType = (String) rs.get("assertType");
    // 	    evidence.Object = label;
    // 	    evidence.assertType = assertType;	    
    // 	    evidence.Precipitant = ((String) rs.get("researchStatementLabel")).replaceAll(assertType, "").split("__")[1];
    // 	    evidence.evidenceRole = (String) rs.get("evidenceRole");
    // 	    evidence.evidence = ((String) rs.get("evidence")).replaceAll("https://dbmi-icode-01.dbmi.pitt.edu/dikb/resource/Evidence/", "");
    // 	    evidence.evidenceSource = (String) rs.get("evidenceSource");
    // 	    evidence.evidenceType = (String) rs.get("evidenceType");
    // 	    evidence.evidenceStatement = (String) rs.get("evidenceStatement");
    // 	    evidenceList.add(evidence); 	    
    // 	}
    // 	return evidenceList;	  
    // }
	

    // /**
    //  * Qurey drug information by name
    //  * @param drugname
    //  * @return
    //  */
    // @GET
    // @Path("{sourceKey}/drugInfo/{drug}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Collection<InfoDBModel> getDrugInfo(@PathParam("sourceKey") String sourceKey, @PathParam("drug") final String drug) throws Exception {
    // 	Source source = getSourceRepository().findBySourceKey(sourceKey);
    //     String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/searchEvidenceByLabel.sql");
    //     sql_statement += " '%" + drug + "%';";
	
    // 	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);	       
    // 	List<InfoDBModel> infoList = new ArrayList<InfoDBModel>();

    // 	for (Map rs: rows) {	    
    // 	    InfoDBModel item = new InfoDBModel();
    // 	    String tempLabel = (String) rs.get("researchStatementLabel");
    // 	    String [] labelList = tempLabel.split("_");

    // 	    String precipitant = labelList[0];
    // 	    item.predicate = (String) rs.get("asserttype");
	    
    // 	    if (item.precipitantL == null) { // TODO: filter out duplications in qry results
    // 		item.precipitantL = new ArrayList<String>();
    // 	    }
    // 	    item.precipitantL.add(precipitant);
    // 	    infoList.add(item);
    // 	}       
    // 	return infoList;	  
    // }

    // /**
    //  * Qurey evidence details by evidenceId
    //  * @param evidence Id
    //  * @return
    //  */
    // @GET
    // @Path("{sourceKey}/evidenceDetails/{evidenceID}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Collection<EvidenceDetailsDBModel> getEvidenceDetails(@PathParam("sourceKey") String sourceKey, @PathParam("evidenceID") final String evidenceID) throws Exception {
    // 	Source source = getSourceRepository().findBySourceKey(sourceKey);
    // 	String sql_statement = ResourceHelper.GetResourceAsString("/resources/mpevidence/sql/getEvidenceDetails.sql");
    // 	sql_statement += "'https://dbmi-icode-01.dbmi.pitt.edu/dikb/resource/Evidence/" + evidenceID + "';";

    // 	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);	     
    // 	List<EvidenceDetailsDBModel> detailsList = new ArrayList<EvidenceDetailsDBModel>();

    // 	for (Map rs: rows) {	    
    // 	    EvidenceDetailsDBModel item = new EvidenceDetailsDBModel();
    // 	    item.evidenceStatement = (String) rs.get("evidenceStatement");
    // 	    item.label = (String) rs.get("label");
    // 	    item.asrt = (String) rs.get("researchstatementlabel");
    // 	    item.dateAnnotated = (String) rs.get("dateAnnotated");
    // 	    item.whoAnnotated = (String) rs.get("whoAnnotated");
    // 	    item.evidence = (String) rs.get("evidence");
    // 	    item.evidenceRole = (String) rs.get("evidenceRole");
    // 	    item.evidenceSource = (String) rs.get("evidenceSource");
    // 	    item.numOfSubjects = (Integer) rs.get("numOfSubjects");
    // 	    item.objectDose = (Double) rs.get("objectDose");
    // 	    item.precipDose = (Double) rs.get("precipDose");
    // 	    item.evidenceVal = (Double) rs.get("evidenceVal");
    // 	    item.tag = (String) rs.get("tag");
    // 	    detailsList.add(item);
    // 	}	
    // 	return detailsList;	  
    // }		
	
	
}
