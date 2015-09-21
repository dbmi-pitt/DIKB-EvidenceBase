package org.ohdsi.webapi.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ohdsi.webapi.DIKB.DetailsDBModel;
import org.ohdsi.webapi.DIKB.DrugDBModel;
import org.ohdsi.webapi.DIKB.EvidenceDBModel;
import org.ohdsi.webapi.DIKB.InfoDBModel;
import org.ohdsi.webapi.DIKB.OverviewDBModel;
import org.ohdsi.webapi.DIKB.SourceDBModel;
import org.ohdsi.webapi.DIKB.TimeDBModel;
import org.ohdsi.webapi.helper.ResourceHelper;
import org.ohdsi.webapi.source.JdbcUtil;
import org.springframework.stereotype.Component;

@Path("DIKB/")
@Component
public class DIKBService {
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EvidenceDBModel> getAllEvidence() throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getAllEvidence.sql");
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<EvidenceDBModel> evidenceList = new ArrayList<EvidenceDBModel>();
		
		while(resultSet.next())
		{
 	    
			EvidenceDBModel evidence = new EvidenceDBModel();
			evidence.researchStatementLabel = resultSet.getString("researchStatementLabel");
			evidence.assertType = resultSet.getString("assertType");
			evidence.dateAnnotated = resultSet.getString("dateAnnotated");
			evidence.evidenceRole = resultSet.getString("evidenceRole");
			evidence.evidence = resultSet.getString("evidence");
			evidenceList.add(evidence);
 	    
		}
		connection.close();
		return evidenceList;	  
	}
	
	@GET
	@Path("drug")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DrugDBModel> getAllDrug() throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getAllEvidence.sql");
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<DrugDBModel> drugList = new ArrayList<DrugDBModel>();
		List<String> filter = new ArrayList<String>();
		String tempdrug;
		
		while(resultSet.next())
		{
 	    
			tempdrug = resultSet.getString("researchStatementLabel");
			
			
			if(!filter.contains((tempdrug.split("_"))[0]))
			{
				DrugDBModel item = new DrugDBModel();
				item.drugName = (tempdrug.split("_"))[0];
				drugList.add(item);
			}
			filter.add((tempdrug.split("_"))[0]);
		}
		connection.close();
		return drugList;	  
	}
	
	
	
	@GET
	@Path("recent/{num}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<EvidenceDBModel> getRecentEvidence(@PathParam("num") final String num) throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getRecentEvidence.sql");
	    sql_statement += " LIMIT " + num + ";";
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<EvidenceDBModel> evidenceList = new ArrayList<EvidenceDBModel>();
		String tempSourceType;
		while(resultSet.next())
		{
 	    
			EvidenceDBModel evidence = new EvidenceDBModel();
			tempSourceType = resultSet.getString("evidenceType");
			if(tempSourceType.length() == 0)
			{
				evidence.name = "Other";
				evidence.fullname = "Other";
			}else{
				evidence.name= resultSet.getString("tag");
				evidence.fullname = tempSourceType.replaceAll("http://dbmi-icode-01.dbmi.pitt.edu/dikb-evidence/DIKB_evidence_ontology_v1.3.owl#", "");
			}
			evidence.researchStatementLabel = resultSet.getString("researchStatementLabel");
			evidence.assertType = resultSet.getString("assertType");
			String dateAnnotated = resultSet.getString("dateAnnotated");
			dateAnnotated = dateAnnotated.replaceAll("-", "");
			dateAnnotated = dateAnnotated.replaceAll(":", "");
			dateAnnotated = dateAnnotated.replaceAll(" ", "");
			dateAnnotated = dateAnnotated.substring(2);
			dateAnnotated = dateAnnotated.substring(0, dateAnnotated.length()-2);
			dateAnnotated += "0";
			evidence.dateAnnotated = dateAnnotated;
			evidence.evidenceRole = resultSet.getString("evidenceRole");
			evidence.evidence = resultSet.getString("evidence");
			evidence.name = resultSet.getString("tag");
			evidence.label = resultSet.getString("tag");
			evidence.object = resultSet.getString("researchStatementLabel").split("_")[0];
			evidence.value = 1;
			ArrayList<TimeDBModel> timeList = new ArrayList<TimeDBModel>();
			
			TimeDBModel timemodel = new TimeDBModel();
			timemodel.setTimeDBModel(dateAnnotated, dateAnnotated);
			timeList.add(timemodel);
			evidence.times = timeList;
			evidenceList.add(evidence);
 	    
		}
		connection.close();
		return evidenceList;	  
	}
	
	@GET
	@Path("search/{label}/{source}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<DetailsDBModel> searchEvidence(@PathParam("label") final String label, @PathParam("source") final String source) throws Exception {
		
		//String source = label.split("+")[1];
		//String drugname = label.split("%2B")[0];
	    String sql_statement;
	    if(source.equalsIgnoreCase("Other"))
	    {
	    	sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/searchEvidenceByLabel.sql");
	    	sql_statement += " '%" + label + "%' and evidenceType='' order by assertType, researchStatementLabel;";
	    }else{
	    	sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/searchEvidenceByLabel.sql");
	    	sql_statement += " '%" + label + "%' and evidenceType like '%" + source + "%' order by assertType, researchStatementLabel;";
	    }
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<DetailsDBModel> evidenceList = new ArrayList<DetailsDBModel>();
		
		while(resultSet.next())
		{
 	    
			DetailsDBModel evidence = new DetailsDBModel();
			String assertType = resultSet.getString("assertType");
			evidence.Object = label;
			evidence.assertType = assertType;
			evidence.Precipitant = resultSet.getString("researchStatementLabel").replaceAll(assertType, "").split("__")[1];
			evidence.evidenceRole = resultSet.getString("evidenceRole");
			evidence.evidence = resultSet.getString("evidence");
			evidence.evidenceSource = resultSet.getString("evidenceSource");
			evidence.evidenceType = resultSet.getString("evidenceType");
			evidence.evidenceStatement = resultSet.getString("evidenceStatement");
			evidenceList.add(evidence);
 	    
		}
		connection.close();
		return evidenceList;	  
	}
	
	@GET
	@Path("source/{drug}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SourceDBModel> getSourceType(@PathParam("drug") final String drug) throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getSourceType.sql");
	    sql_statement = sql_statement.replaceAll("example", drug);
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<SourceDBModel> itemList = new ArrayList<SourceDBModel>();
		String tempSourceType;
		
		while(resultSet.next())
		{
 	    
			SourceDBModel item = new SourceDBModel();
			tempSourceType = resultSet.getString("evidenceType");
			if(tempSourceType.length() == 0)
			{
				item.sourceType = "Other";
				item.fullname = "Other";
			}else{
				item.sourceType = resultSet.getString("tag");
				item.fullname = tempSourceType.replaceAll("http://dbmi-icode-01.dbmi.pitt.edu/dikb-evidence/DIKB_evidence_ontology_v1.3.owl#", "");
			}
			item.sourceNum = resultSet.getInt("num");
			itemList.add(item);
 	    
		}
		/*SourceDBModel item1 = new SourceDBModel();
		item1.sourceType = "EV_EX_Trans_Prot_ID";
		item1.fullname = "EV_EX_Trans_Prot_ID";
		item1.sourceNum = 2;
		itemList.add(item1);*/
		connection.close();
		return itemList;	  
	}
	
	@GET
	@Path("info/{drug}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<InfoDBModel> getDrugInfo(@PathParam("drug") final String drug) throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/searchEvidenceByLabel.sql");
	    sql_statement += " '%" + drug + "%';";
		Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<InfoDBModel> infoList = new ArrayList<InfoDBModel>();
		
		while(resultSet.next())
		{
 	    
			InfoDBModel item = new InfoDBModel();
			String tempLabel = resultSet.getString("researchStatementLabel");
			String predicate = tempLabel.split("_")[1];
			item.predicate = predicate;
			item.precipitant.add(tempLabel.split("_")[2]);
 	    
		}
		connection.close();
		return infoList;	  
	}
	
	@GET
	@Path("overview")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<OverviewDBModel> getOverview() throws Exception {
		
	    String sql_statement = ResourceHelper.GetResourceAsString("/resources/DIKB/sql/getRecentEvidence.sql");
	    sql_statement += " LIMIT 10 ;";
	    Connection connection = JdbcUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql_statement);
		List<OverviewDBModel> overviewList = new ArrayList<OverviewDBModel>();
		String researchStatementLabel = null;
		int countNum = 0;
		
		while(resultSet.next())
		{
			researchStatementLabel = resultSet.getString(researchStatementLabel);//.split("_")[0];
			OverviewDBModel overview = new OverviewDBModel();
			overview.OverviewDrug = researchStatementLabel;
			overview.OverviewTag = "Drugs with the most recent evidences";
			overview.OverviewAttribute = resultSet.getString("dateAnnotated");
			overviewList.add(overview);
			//overviewList.get(0).OverviewDrug
			//++countNum;
 	    
		}
		connection.close();
		return overviewList;	  
	}
	
	
}
