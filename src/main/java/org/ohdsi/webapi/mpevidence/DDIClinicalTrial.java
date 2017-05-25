package org.ohdsi.webapi.mpevidence;

import com.fasterxml.jackson.annotation.JsonProperty;


public class DDIClinicalTrial {
    @JsonProperty("object")
    public String object;
    
    @JsonProperty("precipitant")
    public String precipitant;
    
    @JsonProperty("relationship")
    public String relationship;

    @JsonProperty("evRelationship")
    public String evRelationship;

    @JsonProperty("sourceURL")
    public String sourceURL;
    
    @JsonProperty("method")
    public String method;

    @JsonProperty("inferredMethod")
    public String inferredMethod;
    
    @JsonProperty("participants")
    public String participants;

    @JsonProperty("participantsText")
    public String participantsText;

    @JsonProperty("precipitantDose")
    public String precipitantDose;

    @JsonProperty("precipitantDoseText")
    public String precipitantDoseText;

    @JsonProperty("precipitantDoseFormulation")
    public String precipitantDoseFormulation;

    @JsonProperty("precipitantDoseDuration")
    public String precipitantDoseDuration;

    @JsonProperty("precipitantDoseRegimens")
    public String precipitantDoseRegimens;

    @JsonProperty("objectDose")
    public String objectDose;

    @JsonProperty("objectDoseText")
    public String objectDoseText;

    @JsonProperty("objectDoseFormulation")
    public String objectDoseFormulation;

    @JsonProperty("objectDoseDuration")
    public String objectDoseDuration;

    @JsonProperty("objectDoseRegimens")
    public String objectDoseRegimens;
    
    @JsonProperty("aucRatio")
    public String aucRatio;

    @JsonProperty("aucRatioText")
    public String aucRatioText;

    @JsonProperty("aucRatioType")
    public String aucRatioType;
    
    @JsonProperty("aucRatioDirection")
    public String aucRatioDirection;

    @JsonProperty("cmaxRatio")
    public String cmaxRatio;

    @JsonProperty("cmaxRatioText")
    public String cmaxRatioText;

    @JsonProperty("cmaxRatioType")
    public String cmaxRatioType;
    
    @JsonProperty("cmaxRatioDirection")
    public String cmaxRatioDirection;

    @JsonProperty("clearanceRatio")
    public String clearanceRatio;

    @JsonProperty("clearanceRatioText")
    public String clearanceRatioText;

    @JsonProperty("clearanceRatioType")
    public String clearanceRatioType;
    
    @JsonProperty("clearanceRatioDirection")
    public String clearanceRatioDirection;

    @JsonProperty("halflifeRatio")
    public String halflifeRatio;

    @JsonProperty("halflifeRatioText")
    public String halflifeRatioText;

    @JsonProperty("halflifeRatioType")
    public String halflifeRatioType;
    
    @JsonProperty("halflifeRatioDirection")
    public String halflifeRatioDirection;
}
