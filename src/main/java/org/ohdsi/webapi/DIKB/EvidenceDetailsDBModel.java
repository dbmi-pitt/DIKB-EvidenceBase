package org.ohdsi.webapi.DIKB;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EvidenceDetailsDBModel {
  @JsonProperty("label")
  public String label;

  @JsonProperty("dateAnnotated")
  public String dateAnnotated;
  
  @JsonProperty("whoAnnotated")
  public String whoAnnotated;

  @JsonProperty("evidenceRole")
  public String evidenceRole;

  @JsonProperty("evidence")
  public String evidence;
  
  @JsonProperty("evidenceStatement")
  public String evidenceStatement;
  
  @JsonProperty("objectDose")
  public int objectDose;
  
  @JsonProperty("precipDose")
  public int precipDose;
  
  @JsonProperty("evidenceSource")
  public String evidenceSource;
  
  @JsonProperty("tag")
  public String tag;
  
  @JsonProperty("numOfSubjects")
  public int numOfSubjects;
  
  @JsonProperty("evidenceVal")
  public String evidenceVal;
  
  @JsonProperty("asrt")
  public String asrt;

}
