package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Evidence{

    public Evidence() {
    }

    public Evidence(Integer claimId, Integer evidenceIdx, Boolean evRelationship) {
	this.mp_claim_id = claimId;
	this.evidence_index = evidenceIdx;
	this.evRelationship = evRelationship;
    }

    @JsonProperty("mp_claim_id")
    public int mp_claim_id;

    @JsonProperty("evidence_index")
    public int evidence_index;  

    @JsonProperty("evRelationship")
    public Boolean evRelationship;
    
}
