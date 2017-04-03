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

import org.ohdsi.webapi.DIKB.DetailsDBModel;
import org.ohdsi.webapi.DIKB.DrugDBModel;
import org.ohdsi.webapi.DIKB.EvidenceDBModel;
import org.ohdsi.webapi.DIKB.EvidenceDetailsDBModel;
import org.ohdsi.webapi.DIKB.InfoDBModel;
import org.ohdsi.webapi.DIKB.OverviewDBModel;
import org.ohdsi.webapi.DIKB.SourceDBModel;
import org.ohdsi.webapi.DIKB.TimeDBModel;
import org.ohdsi.webapi.helper.ResourceHelper;
import org.springframework.stereotype.Component;

@Path("DIKB/")
@Component
public class DIKBService  extends AbstractDaoService {
	
	@GET
	@Path("{sourceKey}/assertion")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EvidenceDBModel> getAllEvidence(@PathParam("sourceKey") String sourceKey) throws Exception {
	    Source source = getSourceRepository().findBySourceKey(sourceKey);
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getAllEvidence.sql");
	    List<EvidenceDBModel> evidenceList = new ArrayList<EvidenceDBModel>();
	    List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	    
	    for (Map rs: rows) {
		EvidenceDBModel evidence = new EvidenceDBModel();
		evidence.researchStatementLabel = (String) rs.get("researchStatementLabel");
		evidence.assertType = (String) rs.get("assertType");
		evidence.dateAnnotated = (String) rs.get("dateAnnotated");
		evidence.evidenceRole = (String) rs.get("evidenceRole");
		evidence.evidence = (String) rs.get("evidence");
		evidenceList.add(evidence);
	    }
	    return evidenceList;
	}
	
	@GET
	@Path("{sourceKey}/drug")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DrugDBModel> getAllDrug(@PathParam("sourceKey") String sourceKey) throws Exception {
	    Source source = getSourceRepository().findBySourceKey(sourceKey);
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getAllEvidence.sql");
	    List<DrugDBModel> drugList = new ArrayList<DrugDBModel>();	    
	    List<String> filter = new ArrayList<String>();
	    String tempdrug;

	    List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	    for (Map rs: rows) {
	    	tempdrug = (String) rs.get("researchStatementLabel");
	    	if(!filter.contains((tempdrug.split("_"))[0])) {
	    	    DrugDBModel item = new DrugDBModel();
	    	    item.drugName = (tempdrug.split("_"))[0];
	    	    drugList.add(item);
	    	    filter.add((tempdrug.split("_"))[0]);
	    	}
	    }
	    return drugList;	  
	}
	
	@GET
	@Path("{sourceKey}/precipitant/{drugname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DrugDBModel> getPrecipitant(@PathParam("sourceKey") String sourceKey, @PathParam("drugname") final String drugname) throws Exception {
	    Source source = getSourceRepository().findBySourceKey(sourceKey);
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getPrecipitant.sql");
	    sql_statement = sql_statement.replaceAll("example", drugname);
	    
	    List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	    List<DrugDBModel> drugList = new ArrayList<DrugDBModel>();
	    List<String> filter = new ArrayList<String>();
	    String tempdrug;
	    String tempfilter = "";

	    for (Map rs: rows) { 	   
		tempdrug = (String) rs.get("researchStatementLabel");
		DrugDBModel item = new DrugDBModel();
		String[] temp = tempdrug.split("_");
		int length = temp.length;
		
		if(!filter.contains((tempdrug.split("_"))[length-1])) {			
			tempfilter = (tempdrug.split("_"))[length-1];
			item.drugName = (tempdrug.split("_"))[length-1];
			drugList.add(item);
			filter.add(tempfilter);
		    }			
	    }
	    return drugList;	  
	}	
	
	
    @GET
    @Path("{sourceKey}/recent/{num}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EvidenceDBModel> getRecentEvidence(@PathParam("sourceKey") String sourceKey, @PathParam("num") final String num) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getRecentEvidence.sql");
	sql_statement += " LIMIT " + num + ";";
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<EvidenceDBModel> evidenceList = new ArrayList<EvidenceDBModel>();
	String tempSourceType;
	
	for (Map rs: rows) { 	    	    
	    EvidenceDBModel evidence = new EvidenceDBModel();
	    tempSourceType = (String) rs.get("evidenceType");
	    if(tempSourceType.length() == 0) {
		evidence.name = "Other";
		evidence.fullname = "Other";
	    }else{
		evidence.name= (String) rs.get("tag");
		evidence.fullname = tempSourceType.replaceAll("http://dbmi-icode-01.dbmi.pitt.edu/dikb-evidence/DIKB_evidence_ontology_v1.3.owl#", "");
	    }
	    evidence.researchStatementLabel = ((String) rs.get("researchStatementLabel")).replaceAll("_", " ");
	    evidence.assertType = (String) rs.get("assertType");
	    String dateAnnotated = (String) rs.get("dateAnnotated");
	    dateAnnotated = dateAnnotated.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").replaceAll("/", "");
	    dateAnnotated = dateAnnotated.substring(2);
	    dateAnnotated = dateAnnotated.substring(3, dateAnnotated.length()-3);
	    dateAnnotated += "000";
	    String tempDate = (String) rs.get("dateAnnotated");
	    evidence.dateAnnotated = tempDate.substring(0, tempDate.length()-1);
	    evidence.evidenceRole = (String) rs.get("evidenceRole");
	    evidence.evidence = (String) rs.get("evidence");
	    evidence.name = (String) rs.get("tag");
	    evidence.label = (String) rs.get("tag");
	    evidence.object = ((String) rs.get("researchStatementLabel")).split("_")[0];
	    evidence.value = 1;
	    
	    ArrayList<TimeDBModel> timeList = new ArrayList<TimeDBModel>();		
	    TimeDBModel timemodel = new TimeDBModel();
	    timemodel.setTimeDBModel(dateAnnotated, dateAnnotated);
	    timeList.add(timemodel);
	    evidence.times = timeList;
	    evidenceList.add(evidence); 	    
	    }	 
	return evidenceList;	  
    }

    /**
     * @param assertion label
     * @param precipitant drug name
     * @param evidence source
     * @return
     */
    @GET
    @Path("{sourceKey}/search/{label}/{drug}/{evType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<DetailsDBModel> searchEvidence(@PathParam("sourceKey") String sourceKey, @PathParam("label") final String label, @PathParam("drug") final String drug, @PathParam("evType") final String evType) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/searchEvidenceByLabel.sql");
	
	// query by label, drug and evidenceType if source type not "other"
	if(evType.equalsIgnoreCase("other")) { 
	    sql_statement += "'%" + label + "_%' and researchStatementLabel LIKE '%" + drug + "%' and evidencetype='' order by assertType, researchStatementLabel;";
	} else {
	    sql_statement += "'%" + label + "_%' and researchStatementLabel LIKE '%" + drug + "%' and evidencetype like '%" + evType + "%' order by assertType, researchStatementLabel;";
	}
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);
	List<DetailsDBModel> evidenceList = new ArrayList<DetailsDBModel>();
	
	for (Map rs: rows) { 	    	    		    
	    DetailsDBModel evidence = new DetailsDBModel();
	    String assertType = (String) rs.get("assertType");
	    evidence.Object = label;
	    evidence.assertType = assertType;	    
	    evidence.Precipitant = ((String) rs.get("researchStatementLabel")).replaceAll(assertType, "").split("__")[1];
	    evidence.evidenceRole = (String) rs.get("evidenceRole");
	    evidence.evidence = ((String) rs.get("evidence")).replaceAll("https://dbmi-icode-01.dbmi.pitt.edu/dikb/resource/Evidence/", "");
	    evidence.evidenceSource = (String) rs.get("evidenceSource");
	    evidence.evidenceType = (String) rs.get("evidenceType");
	    evidence.evidenceStatement = (String) rs.get("evidenceStatement");
	    evidenceList.add(evidence); 	    
	}
	return evidenceList;	  
    }
    
    
    /**
     * Qurey evidence type by two drug names
     * @param drugname
     * @param precipitant drug name
     * @return
     */
    @GET
    @Path("{sourceKey}/evidencetype/{drug}/{precipitant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SourceDBModel> getEvidenceType(@PathParam("sourceKey") String sourceKey, @PathParam("drug") final String drug, @PathParam("precipitant") final String precipitant) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getEvidenceType.sql");
	sql_statement = sql_statement.replaceAll("example1", drug).replaceAll("example2", precipitant);
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);	
	List<SourceDBModel> itemList = new ArrayList<SourceDBModel>();
	String tempSourceType;
	
	for (Map rs: rows) { 	    	    	 	    
	    SourceDBModel item = new SourceDBModel();
	    tempSourceType = (String) rs.get("evidenceType");
	    if(tempSourceType.length() == 0) {
		item.sourceType = "Other";
		item.fullname = "Other";
	    } else {
		item.sourceType = (String) rs.get("tag");
		item.fullname = tempSourceType.replaceAll("http://dbmi-icode-01.dbmi.pitt.edu/dikb-evidence/DIKB_evidence_ontology_v1.3.owl#", "");
	    }
	    item.sourceNum = (Long) rs.get("num");
	    itemList.add(item); 	    
	}
	return itemList;	  
    }

    /**
     * Qurey drug information by name
     * @param drugname
     * @return
     */
    @GET
    @Path("{sourceKey}/drugInfo/{drug}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<InfoDBModel> getDrugInfo(@PathParam("sourceKey") String sourceKey, @PathParam("drug") final String drug) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
        String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/searchEvidenceByLabel.sql");
        sql_statement += " '%" + drug + "%';";
	
	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);	       
	List<InfoDBModel> infoList = new ArrayList<InfoDBModel>();

	for (Map rs: rows) {	    
	    InfoDBModel item = new InfoDBModel();
	    String tempLabel = (String) rs.get("researchStatementLabel");
	    String [] labelList = tempLabel.split("_");

	    String precipitant = labelList[0];
	    item.predicate = (String) rs.get("asserttype");
	    
	    if (item.precipitantL == null) { // TODO: filter out duplications in qry results
		item.precipitantL = new ArrayList<String>();
	    }
	    item.precipitantL.add(precipitant);
	    infoList.add(item);
	}       
	return infoList;	  
    }

    /**
     * Qurey evidence details by evidenceId
     * @param evidence Id
     * @return
     */
    @GET
    @Path("{sourceKey}/evidenceDetails/{evidenceID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EvidenceDetailsDBModel> getEvidenceDetails(@PathParam("sourceKey") String sourceKey, @PathParam("evidenceID") final String evidenceID) throws Exception {
	Source source = getSourceRepository().findBySourceKey(sourceKey);
	String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getEvidenceDetails.sql");
	sql_statement += "'https://dbmi-icode-01.dbmi.pitt.edu/dikb/resource/Evidence/" + evidenceID + "';";

	List<Map<String, Object>> rows = getSourceJdbcTemplate(source).queryForList(sql_statement);	     
	List<EvidenceDetailsDBModel> detailsList = new ArrayList<EvidenceDetailsDBModel>();

	for (Map rs: rows) {	    
	    EvidenceDetailsDBModel item = new EvidenceDetailsDBModel();
	    item.evidenceStatement = (String) rs.get("evidenceStatement");
	    item.label = (String) rs.get("label");
	    item.asrt = (String) rs.get("researchstatementlabel");
	    item.dateAnnotated = (String) rs.get("dateAnnotated");
	    item.whoAnnotated = (String) rs.get("whoAnnotated");
	    item.evidence = (String) rs.get("evidence");
	    item.evidenceRole = (String) rs.get("evidenceRole");
	    item.evidenceSource = (String) rs.get("evidenceSource");
	    item.numOfSubjects = (Integer) rs.get("numOfSubjects");
	    item.objectDose = (Double) rs.get("objectDose");
	    item.precipDose = (Double) rs.get("precipDose");
	    item.evidenceVal = (Double) rs.get("evidenceVal");
	    item.tag = (String) rs.get("tag");
	    detailsList.add(item);
	}	
	return detailsList;	  
    }
	
	// @GET
	// @Path("{sourceKey}/overview")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Collection<OverviewDBModel> getOverview(@PathParam("sourceKey") String sourceKey) throws Exception {
	//     Source source = getSourceRepository().findBySourceKey(sourceKey);
	//     String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getRecentEvidence.sql");
	//     sql_statement += " LIMIT 10 ;";
	//     Connection connection = JdbcUtil.getConnection();
	// 	Statement statement = connection.createStatement();
	// 	ResultSet rs = statement.executeQuery(sql_statement);
	// 	List<OverviewDBModel> overviewList = new ArrayList<OverviewDBModel>();
	// 	String researchStatementLabel = null;
	// 	int countNum = 0;
		
	// 	while(rs.next())
	// 	{
	// 		researchStatementLabel = rs.get(researchStatementLabel);//.split("_")[0];
	// 		OverviewDBModel overview = new OverviewDBModel();
	// 		overview.OverviewDrug = researchStatementLabel;
	// 		overview.OverviewTag = "Drugs with the most recent evidences";
	// 		overview.OverviewAttribute = rs.get("dateAnnotated");
	// 		overviewList.add(overview);
	// 		//overviewList.get(0).OverviewDrug
	// 		//++countNum;
 	    
	// 	}
	// 	connection.close();
	// 	return overviewList;	  
	// }
	
	// @GET
	// @Path("precipitantName")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Collection<OverviewDBModel> getPrecipitantName() throws Exception {
		
	//     String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getRecentEvidence.sql");
	//     sql_statement += " LIMIT 10 ;";
	//     Connection connection = JdbcUtil.getConnection();
	// 	Statement statement = connection.createStatement();
	// 	ResultSet rs = statement.executeQuery(sql_statement);
	// 	List<OverviewDBModel> overviewList = new ArrayList<OverviewDBModel>();
	// 	String researchStatementLabel = null;
	// 	int countNum = 0;
		
	// 	while(rs.next())
	// 	{
	// 		researchStatementLabel = rs.get(researchStatementLabel);//.split("_")[0];
	// 		OverviewDBModel overview = new OverviewDBModel();
	// 		overview.OverviewDrug = researchStatementLabel;
	// 		overview.OverviewTag = "Drugs with the most recent evidences";
	// 		overview.OverviewAttribute = rs.get("dateAnnotated");
	// 		overviewList.add(overview);
	// 		//overviewList.get(0).OverviewDrug
	// 		//++countNum;
 	    
	// 	}
	// 	connection.close();
	// 	return overviewList;	  
	// }
	

	
	
}
