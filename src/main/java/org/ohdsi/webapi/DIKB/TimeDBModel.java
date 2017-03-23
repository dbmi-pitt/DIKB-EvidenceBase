package org.ohdsi.webapi.DIKB;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TimeDBModel {
  @JsonProperty("starting_time")
  public String starting_time;

  @JsonProperty("ending_time")
  public String ending_time;
  
  public void setTimeDBModel(String start , String end)
  {
	 starting_time = start;
	 ending_time = end;
  }
  
  public String getStartTimeDBModel()
  {
	 return starting_time;
  }
  
  public String getEndTimeDBModel()
  {
	 return ending_time;
  }

}
