package ServerPackage;

import java.sql.*;

/**
 * The registration class of the server that adds and completes 
 * the registration of a course to a student
 * @version 1.0
 * @since April 20, 2020
 */
public class Registration {

	/**
	 * The student that is logged into the server
	 */
	private Student theStudent;
	
	/**
	 * The course offering to be added or removed from a students registration
	 */
	private CourseOffering theOffering;
	
	/**
	 * The letter grade of the student in the course
	 */
	private char grade;
	
	/**
	 * An object of the database to be accessed for courses
	 */
	private MySQLJDBC database;
	
	/**
	 * The object used for executing SQL statements
	 */
	private Statement myStmt;
	
	/**
	 * The table of data in the SQL database that contains the contents of each course
	 */
	private ResultSet ss;

	/**
	 * A constructor for the registration class that initializes the database
	 */
	public Registration() {
		initializeDatabase();
	}

	/**
	 * A function that initializes the database so that the server can access 
	 * all course information
	 */
	public void initializeDatabase() {
		database = new MySQLJDBC();
		database.initializeConnection();
		try {
			myStmt = database.getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ss = myStmt.executeQuery("SELECT * from lib.STUDENT");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function that completes the registration of a student
	 * @param st - The student to add the registration to
	 * @param of - The course offering to be added to the students list of registrations
	 * @return - A message that tells the client if the registration was successful or not
	 */
	public String completeRegistration(Student st, CourseOffering of) {
		if (st.getNumOfCourses() < 6) {
			theStudent = st;
			theOffering = of;
			addRegistration();
			st.setNumOfCourses(st.getNumOfCourses() + 1);
			return (theOffering.getTheCourse().getCourseName() + " " + theOffering.getTheCourse().getCourseNum()
					+ " was succesfully added to " + theStudent.getStudentName() + "s course list.");
		} else
			return ("Error, student has too many classes already.");
	}
	
	/**
	 * A function that adds the registration to the student
	 */
	private void addRegistration() {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}


	/**
	 * A function that removes a registration from the student that is logged into the server
	 * @param st - The student with a registration to be removed
	 * @param of - The course offering to be removed from the students registration list
	 * @return - A message that tells the client if a registration has been successfully removed or not
	 */
	public String deleteRegistration(Student st, CourseOffering of) {
		if (st.getNumOfCourses() < 1)
			return ("The student has no classes to remove.");
		else {
			theStudent = st;
			theOffering = of;
			removeRegistration();
			st.setNumOfCourses(st.getNumOfCourses() - 1);

			return (of.getTheCourse().getCourseName() + " " + of.getTheCourse().getCourseNum()
					+ " was succesfully removed.");
		}
	}

	/**
	 * A function that removes the registration from a student in the database
	 */

	/**
	 * A function that removes a registration from a student
	 */
	private void removeRegistration() {
		theStudent.removeRegistration(this);
		theOffering.removeRegistration(this);
	}

	/**
	 * A getter function for the student
	 * @return - The student
	 */
	public Student getTheStudent() {
		return theStudent;
	}

	/**
	 * A setter function for the student
	 * @param theStudent - The student to be set to
	 */
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}

	/**
	 * A getter function for the course offering
	 * @return - The course offering
	 */
	public CourseOffering getTheOffering() {
		return theOffering;
	}

	/**
	 * A setter function for the course offering
	 * @param theOffering - The course offering to be set to
	 */
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}

	/**
	 * A getter function for the letter grade
	 * @return - The letter grade of a student
	 */
	public char getGrade() {
		return grade;
	}

	/**
	 * A setter function for the grade
	 * @param grade - The grade of a student to be set to
	 */
	public void setGrade(char grade) {
		this.grade = grade;
	}

	/**
	 * A toString function that returns the contents of the registrations of a student
	 */
	@Override
	public String toString() {
		String st = "\n";
		st += "The Offering: " + theOffering.getTheCourse().getCourseName() + " "
				+ theOffering.getTheCourse().getCourseNum() + "\n";
		st += "Section Number: " + theOffering.getSecNum();
		st += "\n-----------\n";
		return st;

	}

}
