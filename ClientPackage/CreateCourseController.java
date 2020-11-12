package ClientPackage;

import ServerPackage.MySQLJDBC;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * The Create Course Controller class of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class CreateCourseController {
	
	/**
	 * An object for the admin view class
	 */
	private AdminView admin;
	
	/**
	 * An object of the communication controller
	 */
	private ComController coms;

	/**
	 * The contructor the Create Course Controller class
	 * @param admin - The admin view to be displayed
	 * @param com - The Communication Controller to be set to
	 */
	public CreateCourseController(AdminView admin, ComController com) {
		this.admin = admin;
		coms = com;
		admin.addExitListener((ActionEvent ae) -> {
			System.exit(0);
		});
		admin.addCreateListener((ActionEvent ae) -> {
			createCourse();
		});
		admin.addCourseCatalogueListener((ActionEvent ae) -> {
			viewCourseCatalogue();
		});

	}

	/**
	 * A function that displays all courses in the course catalogue
	 */
	public void viewCourseCatalogue() {
		String message = coms.view(4);
		if (message != null) {
			admin.addText(message);
		}
	}

	/**
	 * A function that calls the panel for the admin to create a course
	 */
	public void createCourse() {
		CreateCourseView cc = new CreateCourseView();
		cc.setVisible(true);
		addCourseListeners(cc);
	}

	/**
	 * A function that adds the listeners for the main screen button 
	 * and the done button
	 * @param cc - The Course View of the client
	 */
	public void addCourseListeners(CreateCourseView cc) {
		cc.addMainScreenListener((ActionEvent ae) -> {
			cc.dispose();
		});
		cc.addDoneListener((ActionEvent ae) -> {
			addCourseData(cc);
		});
	}

	/**
	 * A function that adds the course data to a course
	 * @param cc - The Course View of the client
	 */
	public void addCourseData(CreateCourseView cc) {
		String name = cc.getCourseName();
		int id = Integer.parseInt(cc.getCourseID());
		String message = coms.createCourse(name, id, 8);
		if (message != null) {
			admin.displayMessage(message);
		}
		cc.dispose();
	}

	/**
	 * A function that adds a course to the database
	 * @param courseName - The course name
	 * @param courseNum - The course number
	 * @param courseSec - The course section number
	 * @param secCap - The course section capacity
	 */
	public void addCourseToDatabase(String courseName, int courseNum, int courseSec, int secCap) {
		MySQLJDBC courses = new MySQLJDBC();
		courses.initializeConnection();
		try {
			Statement myStmt = courses.getConn().createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * from lib.COURSE");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		courses.insertCourseUserPreparedStatement(courseName, courseNum, courseSec, secCap);
	}

}
