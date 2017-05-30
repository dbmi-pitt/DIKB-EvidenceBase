package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statement {
    @JsonProperty("mp_claim_id")
    public int mp_claim_id;
    
    @JsonProperty("label")
    public String label;
    
    @JsonProperty("claim_text")
    public String claim_text;
    
    @JsonProperty("method")
    public String method;
    
    @JsonProperty("subject")
    public String subject;
  
    @JsonProperty("s_concept_code")
    public String s_concept_code;
    
    @JsonProperty("s_role_concept_code")
    public int s_role_concept_code;
    
    @JsonProperty("object")
    public String object;
    
    @JsonProperty("o_concept_code")
    public String o_concept_code;
    
    @JsonProperty("o_role_concept_code")
    public int o_role_concept_code;
    
    @JsonProperty("relationship")
    public String relationship;
}
