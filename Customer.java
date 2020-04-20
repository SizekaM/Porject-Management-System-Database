
public class Customer {
	String custName, custSurname, custCellNum, custEmail, custPhysAddress;
	

	   public Customer (String custName, String custCellNum, String custEmail, String custPhysAddress ) {
	      this.custName = custName;
	      this.custCellNum = custCellNum;
	      this.custEmail = custEmail;
	      this.custPhysAddress = custPhysAddress;
	   }

	   
	   public String toString() {
	      String output = "\n\n\n== Customer Details ==";
	      output += "\nClient: " + custName;
	      output += "\nTel Number: " + custCellNum;
	      output += "\nemail: " + custEmail;
	      output += "\nphysAddress: " + custPhysAddress;
	   
	      return output;
	   }

}
