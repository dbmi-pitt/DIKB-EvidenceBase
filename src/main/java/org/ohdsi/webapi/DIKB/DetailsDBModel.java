package org.ohdsi.webapi.DIKB;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DetailsDBModel {
  @JsonProperty("Object")
  public String Object;

  @JsonProperty("assertType")
  public String assertType;

  @JsonProperty("Precipitant")
  public String Precipitant;

  @JsonProperty("evidenceRole")
  public String evidenceRole;

  @JsonProperty("evidence")
  public String evidence;
  
  @JsonProperty("evidenceSource")
  public String evidenceSource;
  
  @JsonProperty("evidenceType")
  public String evidenceType;
  
  @JsonProperty("evidenceStatement")
  public String evidenceStatement;

}
