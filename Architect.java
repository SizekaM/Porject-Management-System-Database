
public class Architect {
	String archName, archTelNum, archEmail, archPhysAddress;
	

	   public Architect (String archName, String archTelNum, String archEmail, String archPhysAddress ) {
	      this.archName = archName;
	      this.archTelNum = archTelNum;
	      this.archEmail = archEmail;
	      this.archPhysAddress = archPhysAddress;
	   }

	   
	   public String toString() {
		   String output = "\n\n\n== Architect Details ==";
		   output += "\nArchitect: " + archName;
		   output += "\nTel Number: " + archTelNum;
		   output += "\nemail: " + archEmail;
		   output += "\nphysAddress: " + archPhysAddress;
		   
		   return output;
	   }


}
