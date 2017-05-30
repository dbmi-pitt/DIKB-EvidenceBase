package org.ohdsi.webapi.mpevidence;

// import java.util.ArrayList;
import java.util.HashMap; 
import org.ohdsi.webapi.mpevidence.Statement;
import org.ohdsi.webapi.mpevidence.CTEvidence;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CTClaim extends Statement{

  @JsonProperty("evidence")
  public HashMap<Integer, CTEvidence> evidences;
}
