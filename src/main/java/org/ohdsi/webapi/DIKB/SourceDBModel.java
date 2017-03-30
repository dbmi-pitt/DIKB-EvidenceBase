package org.ohdsi.webapi.DIKB;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SourceDBModel {

    @JsonProperty("name")
    public String sourceType;
    
    @JsonProperty("fullname")
    public String fullname;
    
    @JsonProperty("value")
    public Long sourceNum;
		  
}
