package org.ohdsi.webapi.DIKB;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OverviewDBModel {
  @JsonProperty("OverviewTag")
  public String OverviewTag;

  @JsonProperty("OverviewDrug")
  public String OverviewDrug;

  @JsonProperty("OverviewAttribute")
  public String OverviewAttribute;


}
