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
  public Double objectDose;
  
  @JsonProperty("precipDose")
  public Double precipDose;
  
  @JsonProperty("evidenceSource")
  public String evidenceSource;
  
  @JsonProperty("tag")
  public String tag;
  
  @JsonProperty("numOfSubjects")
  public int numOfSubjects;
  
  @JsonProperty("evidenceVal")
  public Double evidenceVal;
  
  @JsonProperty("asrt")
  public String asrt;

}
