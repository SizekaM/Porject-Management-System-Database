import java.io.*;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class Poised {
	
	public static void main(String[] args) {
		Scanner ui = new Scanner (System.in);
		String projNum, projName, projDeadline, projAddress, userInput;
		String[] buildingOptions = {"House", "Apartment Block", "Store", "Other"};
		double projTotalFee, projFeePaid, projAmountDue;
		String custName, custCellNum, custEmail, custPhysAddress;
		String contrName, contrTelNum, contrEmail, contrPhysAddress;
		String archName, archTelNum, archEmail, archPhysAddress;
		String engName, engTelNum, engEmail;
		String PMName, PMTelNum, PMEmail;
		int userChoice;
		
		
		
		try {
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/PoisePMS",
					"otheruser",
					"swordfish");
			
			Statement statement = con.createStatement();
			ResultSet results;
			String SQL;
			int rowsAffected;
			
			

			
			userChoice = Integer.parseInt(JOptionPane.showInputDialog("\t\t\t== WELCOME TO POISED =="
																	+ "\n\n\t== MENU ==\n"
																	+ "\n1) Create new project."
																	+ "\n2) View & Edit In-Progress Projects."
																	+ "\n3) View Completed Projects."
																	+ "\n4) Close Application."));
			
			if(userChoice == 1) {
			
				projNum = JOptionPane.showInputDialog("== Creating New Project =="
						+ "\n\nProject Number");
				
				
				projName = JOptionPane.showInputDialog("Project Name");
				
				int projBuilding = buildingOptionCheck(Integer.parseInt(JOptionPane.showInputDialog("Project Building:\n"
						+ "1) House\n"
						+ "2) Apartment Block\n"
						+ "3) Store\n"
						+ "4) Other"))) + 1;
		
				
				projAddress = JOptionPane.showInputDialog("Project Physical Address");
				projTotalFee = Double.parseDouble(JOptionPane.showInputDialog("Project Fee"));
				projFeePaid = Double.parseDouble(JOptionPane.showInputDialog("Amount Paid"));
				projAmountDue = projTotalFee - projFeePaid;
				
				projDeadline = JOptionPane.showInputDialog("Project Deadline\n"	+ "(dd-mm-yyyy)");	
				
				engName = JOptionPane.showInputDialog("Fill in Engineer Details!\n\n" + "Engineer Name & Surname");
				engEmail = JOptionPane.showInputDialog("Engineer email");
				engTelNum = JOptionPane.showInputDialog("Engineer Cellphone number");
				
				PMName = JOptionPane.showInputDialog("Fill in Project Manager Details!\n\n" + "Project Manager Name & Surname");
				PMEmail = JOptionPane.showInputDialog("Project Manager email");
				PMTelNum = JOptionPane.showInputDialog("Project Manager Cellphone number");
				
				custName = JOptionPane.showInputDialog("Fill in Customer Details!\n" + "Customer Name & Surname");							
				custCellNum = cellCheck(JOptionPane.showInputDialog("Customer Cellphone Number"));				
				custEmail = JOptionPane.showInputDialog("Customer Email");			
				custPhysAddress = JOptionPane.showInputDialog("Customer Physical Address");
				Customer projCustomer = new Customer(custName, custCellNum, custEmail, custPhysAddress);	// Creates a customer for the project.
			
				
				
				archName = JOptionPane.showInputDialog("Fill in Architect Details\n\n" + "Architect Name & Surname ");			
				archTelNum = JOptionPane.showInputDialog("Architect Telephone Number");				
				archEmail = JOptionPane.showInputDialog("Architect Email");				
				archPhysAddress = JOptionPane.showInputDialog("Architect Physical Address");
				Architect projArchitect = new Architect(archName, archTelNum, archEmail, archPhysAddress);	// Creates an architect for the project.
			
				
		
				contrName = JOptionPane.showInputDialog("Fill in Contractor Details\n\n" + "Contractor Name & Surname");				
				contrTelNum = JOptionPane.showInputDialog("Contractor Telephone Number");
				contrEmail = JOptionPane.showInputDialog("Contractor Email");
				contrPhysAddress = JOptionPane.showInputDialog("Contractor Physical Address");
				Contractor projContractor = new Contractor(contrName, contrTelNum, contrEmail, contrPhysAddress);	// Creates a contractor for the project.
				
				
				String Invoice = ("\n\n=== INVOICE ===\n"
								+ "\n" + projCustomer.toString()
								+ "\n\nProject Total Fee: " + projTotalFee
								+ "\nProject Amount Paid: " + projFeePaid
								+ "\nProject Amount Due: " + projAmountDue);
				
				if(projName.isEmpty()) {
					projName = (buildingOptions[projBuilding] + " " + custName);
				}
				
				
				
				
				if(projTotalFee > projFeePaid){
					
					System.out.println("Project Number: " + projNum
					+ "\nProject Name: " + projName
					+ "\nBuilding of Project: " + buildingOptions[projBuilding]
					+ "\nProject Physical Address: " + projAddress
					+ "\n" + projCustomer.toString()
					+ "\n" + projArchitect.toString()
					+ "\n" + projContractor.toString()
					+ "\nProject Deadline: " + projDeadline
					+ "\n" + Invoice);
					
					//Adds Project to database
					SQL = "INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
														+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					String comp = "NO";
					String compDate = "-";
					
					
					PreparedStatement pstmt = con.prepareStatement(SQL);
					pstmt.setString(1, projNum);
					pstmt.setString(2, projName);
					pstmt.setInt(3, projBuilding);
					pstmt.setString(4, projAddress);
					pstmt.setString(5, projDeadline);
					pstmt.setDouble(6, projTotalFee);
					pstmt.setDouble(7, projFeePaid);
					pstmt.setDouble(8, projAmountDue);
					pstmt.setString(9, engName);
					pstmt.setString(10, engEmail);
					pstmt.setString(11, engTelNum);
					pstmt.setString(12, PMName);
					pstmt.setString(13, PMEmail);
					pstmt.setString(14, PMTelNum);
					pstmt.setString(15, custName);
					pstmt.setString(16, custEmail);
					pstmt.setString(17, custCellNum);
					pstmt.setString(18, custPhysAddress);
					pstmt.setString(19, archName);
					pstmt.setString(20, archEmail);
					pstmt.setString(21, archTelNum);
					pstmt.setString(22, archPhysAddress);
					pstmt.setString(23, contrName);
					pstmt.setString(24, contrEmail);
					pstmt.setString(25, contrTelNum);
					pstmt.setString(26, contrPhysAddress);
					pstmt.setString(27, comp);
					pstmt.setString(28, compDate);
					pstmt.executeUpdate();
					pstmt.close();
					
				}
				
				else {
					
					System.out.println("Project Number: " + projNum
					+ "\nProject Name: " + projName
					+ "\nBuilding of Project: " + buildingOptions[projBuilding]
					+ "\nProject Physical Address: " + projAddress
					+ "\n" + projCustomer.toString()
					+ "\n" + projArchitect.toString()
					+ "\n" + projContractor.toString());
					
					SQL = "INSERT INTO projects VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					String comp = "NO";
					String compDate = "-";
					
					
					PreparedStatement pstmt = con.prepareStatement(SQL);
					pstmt.setString(1, projNum);
					pstmt.setString(2, projName);
					pstmt.setInt(3, projBuilding);
					pstmt.setString(4, projAddress);
					pstmt.setString(5, projDeadline);
					pstmt.setDouble(6, projTotalFee);
					pstmt.setDouble(7, projFeePaid);
					pstmt.setDouble(8, projAmountDue);
					pstmt.setString(9, engName);
					pstmt.setString(10, engEmail);
					pstmt.setString(11, engTelNum);
					pstmt.setString(12, PMName);
					pstmt.setString(13, PMEmail);
					pstmt.setString(14, PMTelNum);
					pstmt.setString(15, custName);
					pstmt.setString(16, custEmail);
					pstmt.setString(17, custCellNum);
					pstmt.setString(18, custPhysAddress);
					pstmt.setString(19, archName);
					pstmt.setString(20, archEmail);
					pstmt.setString(21, archTelNum);
					pstmt.setString(22, archPhysAddress);
					pstmt.setString(23, contrName);
					pstmt.setString(24, contrEmail);
					pstmt.setString(25, contrTelNum);
					pstmt.setString(26, contrPhysAddress);
					pstmt.setString(27, comp);
					pstmt.setString(28, compDate);
					pstmt.executeUpdate();
					pstmt.close();
				}
				
			}

				
			else if(userChoice == 2) {
				
					
				
				userChoice = Integer.parseInt(JOptionPane.showInputDialog("== Edit Project =="
																		+ "\n1) YES"
																		+ "\n2) NO, End Application"));
				

				if(userChoice == 1) {
					
					projNum = JOptionPane.showInputDialog("Project Number");
					
					SQL = "SELECT * FROM projects WHERE projNum = ?";
					PreparedStatement pstmt = con.prepareStatement(SQL);
					pstmt.setString(1, projNum);
					pstmt.executeQuery();
					pstmt.close();
					
				
					//Edits specified section of project in database
					userChoice = Integer.parseInt(JOptionPane.showInputDialog("== EDIT MENU =="
																			+ "1) Change Deadline\n "
																			+ "2) Update Fee Paid\n"
																			+ "3) Change Contractor's Contact Details"
																			+ "4) Finalise Project"));
					
					if(userChoice == 1) {
						
						projDeadline = JOptionPane.showInputDialog("Change Deadline of project\n"
																	+ "(dd-mm-yyyy)");
						
						SQL = "UPDATE projects SET projDeadline = ? WHERE projNum = ?";
						
						PreparedStatement pstmt1 = con.prepareStatement(SQL);
						pstmt1.setString(1, projDeadline);
						pstmt1.setString(2, projNum);
						pstmt1.executeUpdate();
						pstmt1.close();
						
						
					}
					
					else if(userChoice == 2) {
						
						Double newProjFeePaid = Double.parseDouble(JOptionPane.showInputDialog("Updated Fee Paid"));
						
						String SQL1 = "UPDATE projects SET projAmountPaid = ? WHERE projNum = ?";
						
						PreparedStatement pstmt2 = con.prepareStatement(SQL1);
						pstmt2.setDouble(1, newProjFeePaid);
						pstmt2.setString(2, projNum);
						pstmt2.executeUpdate();
						pstmt2.close();
						
					}
					
					else if(userChoice == 3) {
						userChoice = Integer.parseInt(JOptionPane.showInputDialog("1) Update Telephone number "
																				+ "2) Update Email"
																				+ "3) Close Application"));
						if(userChoice == 1) {
							
							String newTelNum = JOptionPane.showInputDialog("New Contractor Telephone Number");
							SQL = "UPDATE projects SET contractorCell = ? WHERE projNum = ?";
							
							PreparedStatement pstmt1 = con.prepareStatement(SQL);
							pstmt1.setString(1, newTelNum);
							pstmt1.setString(2, projNum);
							pstmt1.executeUpdate();
							pstmt1.close();
							
						}
						
						else if(userChoice == 2) {
							
							String newEmail = JOptionPane.showInputDialog("New Contractor Email");
							
							SQL = "UPDATE projects SET contractorEmail = ? WHERE projNum = ?";
							
							PreparedStatement pstmt1 = con.prepareStatement(SQL);
							pstmt1.setString(1, newEmail);
							pstmt1.setString(2, projNum);
							pstmt1.executeUpdate();
							pstmt1.close();
							
							
							
						}
						
						else if(userChoice == 3) {
							System.exit(0);
						}
						
						else {
							System.out.println("Invalid selection");
						}
						
				}
					
				else if(userChoice == 4) {
					
					projNum = JOptionPane.showInputDialog("Project Number");
					String projUpdate = JOptionPane.showInputDialog("Completion Date");
					
					SQL = "UPDATE projects SET completed= 'YES' AND completionDate = ? WHERE projNum = ?";
					
					PreparedStatement pstmt1 = con.prepareStatement(SQL);
					pstmt1.setString(2, projUpdate);
					pstmt1.setString(3, projNum);
					pstmt1.executeUpdate();
					pstmt1.close();
					
					
					
				}
					
				
					
				}
				
			else if(userChoice == 2){
					System.out.println("Closing Application");
					System.exit(0);
			}
				
			else {
				System.out.println("Invalid selection");
			}
		}
			
				
			else if(userChoice == 3) {
				SQL = "SELECT * FROM projects WHERE completed = YES ";
				
				PreparedStatement pstmt1 = con.prepareStatement(SQL);
				pstmt1.executeQuery();
				pstmt1.close();
			}
			
			else if(userChoice == 4) {
				System.exit(0);
			}
			
			else {
				while(userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4) {
					userChoice = Integer.parseInt(JOptionPane.showInputDialog("Invalid selection, Try Again"));
				}
			}
			
			
			
			
		}
		
		
		catch (SQLException e){
			e.printStackTrace();
		}
		}

	
	public static void printAllFromTable(Statement statement) throws SQLException {
		ResultSet results = statement.executeQuery("SELECT id, title, author, qty FROM books");
		
		while(results.next()) {
			System.out.println(results.getString("projNum") + ", "
							+ results.getString("projName") + ", "
							+ results.getString("projBuilding") + ", "
							+ results.getString("projAddress") + ", "
							+ results.getString("projDeadline") + ", "
							+ results.getDouble("projTotalFee") + ", "
							+ results.getDouble("projAmountPaid") + ", "
							+ results.getDouble("projAmountDue") + ", "
							+ results.getString("engineer") + ", "
							+ results.getString("engineerEmail") + ", "
							+ results.getString("engineerCelll") + ", "
							+ results.getString("projectManager") + ", "
							+ results.getString("managerEmail") + ", "
							+ results.getString("managerCell") + ", "
							+ results.getString("customer") + ", "
							+ results.getString("customerEmail") + ", "
							+ results.getString("customerCell") + ", "
							+ results.getString("customerPhysAddress") + ", "
							+ results.getString("architect") + ", "
							+ results.getString("architectEmail") + ", "
							+ results.getString("architectCell") + ", "
							+ results.getString("architectPhysAddress") + ", "
							+ results.getString("contractor") + ", "
							+ results.getString("contractorEmail") + ", "
							+ results.getString("contractorCell") + ", "
							+ results.getString("contractorPhysAddress") + ", "
							+ results.getString("completed") + ", "
							+ results.getString("completionDate"));
		}
	}





	// Checks if building option exists
	public static int buildingOptionCheck (int buildingOption) throws NumberFormatException{
		
		try {
			if( buildingOption < 1 || buildingOption > 4) {
				throw new NumberFormatException("Keyed in building option does not exist!");
			}
		}
		
		catch (NumberFormatException ex) {
			System.err.println(ex.toString());
		}
		return buildingOption;
	}
	
	
	// Prevents users from inputting too many values
	public static String cellCheck (String custCellNum) throws InputMismatchException{
		try {
			if(custCellNum.length() > 10) {
					throw new InputMismatchException("Invalid Cellphone Number");
				}
			}
		catch (InputMismatchException ex){
			System.err.println(ex.toString());
		}
		return custCellNum;
	}

}
