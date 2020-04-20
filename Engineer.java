
public class Engineer {
	String engName, engTelNum, engEmail;

	   
	   public Engineer(String engName, String engTelNum, String engEmail) {
	   	  this.engName = engName;
	      this.engTelNum = engTelNum;
	      this.engEmail = engEmail;
	}


	public String toString() {
	      String output = "\n\n\n== Engineer Details ==";
	      output += "\nEngineer: " + engName;
	      output += "\nTel Number: " + engTelNum;
	      output += "\nEmail: " + engEmail;
	   
	      return output;
	   }


}
