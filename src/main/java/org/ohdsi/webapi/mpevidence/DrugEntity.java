package org.ohdsi.webapi.mpevidence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrugEntity {

    @JsonProperty("CONCEPT_NAME")
    public String drugConceptName;

    @JsonProperty("CONCEPT_CODE")
    public String drugConceptCode;
		  
}
