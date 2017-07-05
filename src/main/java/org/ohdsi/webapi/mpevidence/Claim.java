package org.ohdsi.webapi.mpevidence;

// import java.util.ArrayList;
import java.util.HashMap; 
import org.ohdsi.webapi.mpevidence.Statement;
import org.ohdsi.webapi.mpevidence.Evidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Claim extends Statement{

    public Claim (Integer claimId, String subject, String object, String method, String relationship, String text) {
	this.mp_claim_id = claimId;
	this.subject = subject;
	this.object = object;
	this.method = method;
	this.relationship = relationship;
	this.claim_text = text;
    }
    
    @JsonProperty("mp_claim_id")
    public int mp_claim_id;
        
    @JsonProperty("evidence")
    public HashMap<Integer, Evidence> evidences;  

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
