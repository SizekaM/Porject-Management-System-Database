
public class Contractor {
	String contrName, contrSurname, contrTelNum, contrEmail, contrPhysAddress;

	   
	   public Contractor(String contrName, String contrTelNum, String contrEmail, String contrPhysAddress) {
	   	  this.contrName = contrName;
	      this.contrTelNum = contrTelNum;
	      this.contrEmail = contrEmail;
	      this.contrPhysAddress = contrPhysAddress;
	}


	public String toString() {
	      String output = "\n\n\n== Contractor Details ==";
	      output += "\nContractor: " + contrName;
	      output += "\nTel Number: " + contrTelNum;
	      output += "\nEmail: " + contrEmail;
	      output += "\nphysAddress: " + contrPhysAddress;
	   
	      return output;
	   }

	
}
