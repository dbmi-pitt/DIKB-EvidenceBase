package org.ohdsi.webapi.mpevidence;

import java.util.ArrayList;
import org.ohdsi.webapi.mpevidence.Evidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CTEvidence extends Evidence{

    @JsonProperty("participants")
    public String participants;

    @JsonProperty("participantsText")
    public String participantsText;
    
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

    @JsonProperty("auc")
    public String auc;

    @JsonProperty("aucType")
    public String aucType;

    @JsonProperty("aucDirection")
    public String aucDirection;

    @JsonProperty("aucText")
    public String aucText;

    @JsonProperty("cmax")
    public String cmax;

    @JsonProperty("cmaxType")
    public String cmaxType;

    @JsonProperty("cmaxDirection")
    public String cmaxDirection;

    @JsonProperty("cmaxText")
    public String cmaxText;

    @JsonProperty("clearance")
    public String clearance;

    @JsonProperty("clearanceType")
    public String clearanceType;

    @JsonProperty("clearanceDirection")
    public String clearanceDirection;

    @JsonProperty("clearanceText")
    public String clearanceText;

    @JsonProperty("halflife")
    public String halflife;

    @JsonProperty("halflifeType")
    public String halflifeType;

    @JsonProperty("halflifeDirection")
    public String halflifeDirection;

    @JsonProperty("halflifeText")
    public String halflifeText;
    
}
