package ClientPackage;

import java.awt.event.ActionEvent;
import java.sql.*;

import javax.swing.JOptionPane;

import ServerPackage.Course;
import ServerPackage.MySQLJDBC;
import ServerPackage.Student;

/**
 * The Login Controller class that gathers the login 
 * information of a student or an admin
 * @version 1.0
 * @since April 20, 2020
 */
public class LoginController {
	
	/**
	 * An object of the Login View
	 */
	private LoginView login;
	
	/**
	 * An object of the Communication Controller
	 */
	private ComController coms;
	
	/**
	 * The database to be accessed for the user name and password
	 */
	private MySQLJDBC database;
	
	/**
	 * The table from the SQL database of the students login information
	 */
	private ResultSet studentRs;
	
	/**
	 * The table from the SQL database of the admins login information
	 */
	private ResultSet adminRs;

	/**
	 * The constructor for the Login Controller that adds listeners 
	 * to the login and quit buttons
	 * @param login - An object of the LoginView to be set to
	 * @param client - An object of the Communication Controller to be set to
	 */
	public LoginController(LoginView login, ComController client) {
		this.login = login;
		coms = client;
		login.addEnterListener((ActionEvent ae) -> {
			login();
		});
		login.addCancelListener((ActionEvent ae) -> {
			System.exit(0);
		});
	}

	/**
	 * A function that initializes and connects the SQL database to the Login View
	 */
	public void initializeDatabase() {
		database = new MySQLJDBC();
		database.initializeConnection();
		try {
			Statement myStmt = database.getConn().createStatement();
			studentRs = myStmt.executeQuery("SELECT * from lib.STUDENT");
			adminRs = myStmt.executeQuery("SELECT * from lib.ADMIN");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function that checks if a student ID and passwords match to initialize login
	 * @param ID - The student ID
	 * @param password - the password of a student's account
	 * @return - The name of the student if login was successful, null if not successful
	 */
	public String checkStudentLogin(String ID, String password) {
		
		try {
			Statement myStmt = database.getConn().createStatement();
			studentRs = myStmt.executeQuery("SELECT * from lib.STUDENT");
			while (studentRs.next()) {
				if (studentRs.getInt("id") == Integer.parseInt(ID)) {
					if (studentRs.getString("password").equals(password)) {
						return studentRs.getString("name");
					}
				}
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * A function that checks if an admin ID and passwords match to initialize login
	 * @param ID - The admin ID
	 * @param password - The password of the admin's account
	 * @return - True if login was successful, false if not
	 */
	public boolean checkAdminLogin(String ID, String password) {
		
		try {
			Statement myStmt = database.getConn().createStatement();
			adminRs = myStmt.executeQuery("SELECT * from lib.ADMIN");
			
			while (adminRs.next()) {
				//System.out.println(adminRs.getString("name"));
				if (adminRs.getInt("id") == Integer.parseInt(ID)) {
					//System.out.println(adminRs.getInt("id"));
					if (adminRs.getString("password").equals(password)) {
						//System.out.println(adminRs.getString("password"));
						return true;
					}
				}
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * A function that gathers the information from the Login View to
	 * confirm if they are eligible to login from the SQL Database
	 */
	public void login() {
		String username = login.getUsername();
		String password = login.getPassword();
		this.initializeDatabase();
		if (login.getUser().equals("Student")) {
			String name = checkStudentLogin(username, password);
			if (name != null) {
				login.dispose();
				MainView main = new MainView();
				main.setVisible(true);
				coms.sendStudent(name, Integer.parseInt(username), 9);
				GUIController controller = new GUIController(main, coms);
			} else {
				login.displayMessage("The ID and password do not match. Please try again.");
			}
			
		} else {
			if (checkAdminLogin(username, password)) {
				login.dispose();
				AdminView admin = new AdminView();
				admin.setVisible(true);
				CreateCourseController adminCon = new CreateCourseController(admin, coms);
			} else {
				login.displayMessage("The ID and password do not match. Please try again.");
			}

		}

	}
}
