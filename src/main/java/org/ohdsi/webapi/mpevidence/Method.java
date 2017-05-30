package org.ohdsi.webapi.mpevidence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Method {
    @JsonProperty("mp_claim_id")
    public int mp_claim_id;

    @JsonProperty("evidence_index")
    public int evidence_index;  
    
    @JsonProperty("method")
    // sourceType
    public String method;
    
    @JsonProperty("inferredMethod")
    // fullname
    public String inferredMethod;
    
    @JsonProperty("value")
    public Long sourceNum;
		  
}
