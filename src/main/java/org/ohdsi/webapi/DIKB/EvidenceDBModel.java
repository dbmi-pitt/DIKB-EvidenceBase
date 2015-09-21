package org.ohdsi.webapi.DIKB;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EvidenceDBModel {
  @JsonProperty("researchStatementLabel")
  public String researchStatementLabel;

  @JsonProperty("assertType")
  public String assertType;

  @JsonProperty("dateAnnotated")
  public String dateAnnotated;

  @JsonProperty("evidenceRole")
  public String evidenceRole;

  @JsonProperty("evidence")
  public String evidence;
  
  @JsonProperty("source")
  public String source;
  
  @JsonProperty("value")
  public int value;
  
  @JsonProperty("name")
  public String name;
  
  @JsonProperty("label")
  public String label;
  
  @JsonProperty("object")
  public String object;
  
  @JsonProperty("fullname")
  public String fullname;
  
  @JsonProperty("times")
  public ArrayList<TimeDBModel> times;

}
