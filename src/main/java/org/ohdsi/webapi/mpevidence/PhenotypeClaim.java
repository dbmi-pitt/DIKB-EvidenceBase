package org.ohdsi.webapi.mpevidence;

import java.util.HashMap; 
import org.ohdsi.webapi.mpevidence.Claim;
import org.ohdsi.webapi.mpevidence.PhenotypeEvidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhenotypeClaim extends Claim{

    public PhenotypeClaim (Integer claimId, String subject, String object, String method, String relationship, String text) {
	super(claimId, subject, object, method, relationship, text);
    }

    @JsonProperty("evidence")
    public HashMap<Integer, PhenotypeEvidence> evidences;
}
