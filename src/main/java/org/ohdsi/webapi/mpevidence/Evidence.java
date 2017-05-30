package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Evidence{

    @JsonProperty("mp_claim_id")
    public int mp_claim_id;

    @JsonProperty("evidence_index")
    public int evidence_index;  

    @JsonProperty("evRelationship")
    public boolean evRelationship;
    
}
