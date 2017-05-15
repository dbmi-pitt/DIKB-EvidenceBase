package org.ohdsi.webapi.mpevidence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Method {

    @JsonProperty("method")
    // sourceType
    public String method;
    
    @JsonProperty("inferredMethod")
    // fullname
    public String inferredMethod;
    
    @JsonProperty("value")
    public Long sourceNum;
		  
}
