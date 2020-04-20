
public class ProjectManager {
	String PMName, PMTelNum, PMEmail;

	   
	   public ProjectManager(String PMName, String PMTelNum, String PMEmail) {
	   	  this.PMName = PMName;
	      this.PMTelNum = PMTelNum;
	      this.PMEmail = PMEmail;
	}


	public String toString() {
	      String output = "\n\n\n== Project Manager Details ==";
	      output += "\nProject Manager: " + PMName;
	      output += "\nTel Number: " + PMTelNum;
	      output += "\nEmail: " + PMEmail;
	   
	      return output;
	   }

}
