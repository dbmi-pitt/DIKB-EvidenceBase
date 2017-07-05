package org.ohdsi.webapi.mpevidence;

import java.util.HashMap; 
import org.ohdsi.webapi.mpevidence.Claim;
import org.ohdsi.webapi.mpevidence.CREvidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CRClaim extends Claim{

    public CRClaim (Integer claimId, String subject, String object, String method, String relationship, String text) {
	super(claimId, subject, object, method, relationship, text);
    }
    

    @JsonProperty("evidence")
    public HashMap<Integer, CREvidence> evidences;
}
