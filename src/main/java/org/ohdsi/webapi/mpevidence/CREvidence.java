package org.ohdsi.webapi.mpevidence;

import java.util.HashMap;
import org.ohdsi.webapi.mpevidence.Evidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CREvidence extends Evidence{

    public CREvidence(Integer claimId, Integer evidenceIdx, Boolean evRelationship) {
	super(claimId, evidenceIdx, evRelationship);
    }
    
    @JsonProperty("dose1")
    public String dose1;

    @JsonProperty("dose1Name")
    public String dose1Name;

    @JsonProperty("dose1Formulation")
    public String dose1Formulation;

    @JsonProperty("dose1Duration")
    public String dose1Duration;

    @JsonProperty("dose1Regimens")
    public String dose1Regimens;

    @JsonProperty("dose1Text")
    public String dose1Text;

    @JsonProperty("dose2")
    public String dose2;

    @JsonProperty("dose2Name")
    public String dose2Name;

    @JsonProperty("dose2Formulation")
    public String dose2Formulation;

    @JsonProperty("dose2Duration")
    public String dose2Duration;

    @JsonProperty("dose2Regimens")
    public String dose2Regimens;

    @JsonProperty("dose2Text")
    public String dose2Text;

    @JsonProperty("reviewer")
    public String reviewer;

    @JsonProperty("reviewerdate")
    public String reviewerdate;

    @JsonProperty("reviewertotal")
    public String reviewertotal;

    @JsonProperty("reviewerlackinfo")
    public String reviewerlackinfo;

    @JsonProperty("dipsquestion")
    public HashMap<String, String> dipsquestion;
}
