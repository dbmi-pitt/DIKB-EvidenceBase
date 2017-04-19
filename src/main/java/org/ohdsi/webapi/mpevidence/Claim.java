package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Claim {
  @JsonProperty("mp_claim_id")
  public int mp_claim_id;

  @JsonProperty("mp_data_index")
  public String mp_data_index;

  @JsonProperty("label")
  public String label;

  @JsonProperty("claim_text")
  public String claim_text;

  @JsonProperty("method")
  public String method;

  @JsonProperty("precipitant")
  public String precipitant;
  
  @JsonProperty("p_concept_code")
  public String p_concept_code;
  
  @JsonProperty("p_role_concept_code")
  public int p_role_concept_code;
  
  @JsonProperty("object")
  public String object;
    
  @JsonProperty("o_concept_code")
  public String o_concept_code;
  
  @JsonProperty("o_role_concept_code")
  public int o_role_concept_code;
    
}
