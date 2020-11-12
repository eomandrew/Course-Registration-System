package ClientPackage;

import java.awt.event.ActionEvent;

/**
 * The Controller for the class to signify login for an admin or a student
 * @version 1.0
 * @since April 20, 2020
 */
public class OpeningController {
	
	/**
	 * An object of the Opening View
	 */
	private OpeningView open;
	
	/**
	 * An object of the Communication Controller
	 */
	private ComController coms;
	
	/**
	 * The constructor for the Opening controller that adds action listeners to 
	 * create an admin or a student
	 * @param open - An object of the Opening View to be set to
	 * @param client - An object of the Communication Controller to be set to
	 */
	public OpeningController(OpeningView open, ComController client) {
		this.open = open;
		coms = client;
		open.addAdminListener((ActionEvent ae) -> {
			createAdmin();
		});
		open.addStudentListener((ActionEvent ae) -> {
			createStudent();
		});
	}
	
	/**
	 * A function that opens the Login View for an admin
	 */
	public void createAdmin() {
		open.dispose();
		LoginView login = new LoginView("Admin");
		login.setVisible(true);
		LoginController LController = new LoginController(login, coms);
	}
	
	/**
	 * A funciton that opens the Login View for a student
	 */
	public void createStudent() {
		open.dispose();
		LoginView login = new LoginView("Student");
		login.setVisible(true);
		LoginController LController = new LoginController(login, coms);
	}
}
