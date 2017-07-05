package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statement {
    
    @JsonProperty("mp_claim_id")
    public int mp_claim_id;
    
    @JsonProperty("label")
    public String label;    
}
