package ServerPackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The class of the course catalogue created from the database
 * @version 1.0
 * @since April 20, 2020
 */
public class CourseCatalogue {

	/**
	 * An Array List of the courses in the course catalogue
	 */
	private ArrayList<Course> courseList;

	/**
	 * A constructor for the course catalogue that loads the information
	 * from the database
	 */
	public CourseCatalogue() {
		loadFromDataBase();
		// TODO Auto-generated catch block
	}

	/**
	 * A function that loads the information from the database
	 */
	private void loadFromDataBase(){
		DBManager db = new DBManager();
		try {
		setCourseList(db.readFromDataBase());
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function that adds a course to the course catalogue
	 * @param courseName - The name of the course
	 * @param courseNum - The course number
	 * @return - A string that tells the client if a course already exists
	 * or is successfully added to the course catalogue
	 */
	public String addCourse(String courseName, int courseNum) {
		for (Course c: courseList) {
			if (c.getCourseName().equals(courseName) && c.getCourseNum() == courseNum) {
				int numOfOffering = 0;
				for (CourseOffering cO: c.getCourseOffering()) {
					numOfOffering++;
				}
				createCourseOffering(c, numOfOffering+1, 200);
				addCourseToDatabase(courseName, courseNum, numOfOffering+1, 200);
				return "The course already exists. Another course offering was made.";
			}
		}
		Course newCourse = new Course(courseName, courseNum);
		courseList.add(newCourse);
		createCourseOffering(newCourse, 1, 200);
		addCourseToDatabase(courseName, courseNum, 1, 200);
		return "The course was successfully added!";
	}

	/**
	 * A function that creates the course offering
	 * @param c - The course to add the offering to
	 * @param secNum - The section number
	 * @param secCap - The section capacity
	 */
	public void createCourseOffering(Course c, int secNum, int secCap) {
		if (c != null) {
			CourseOffering theOffering = new CourseOffering(secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	/**
	 * this method adds the course to the database
	 * @param courseName the name of course
	 * @param courseNum the number of the course
	 * @param courseSec section number
	 * @param secCap section cap
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

	/**
	 * A function that searches the catalogue for a specific course
	 * @param courseName - The name of the course
	 * @param courseNum - The course number
	 * @return - The course that is found, null if the course is not found
	 */
	public Course searchCat(String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) && courseNum == c.getCourseNum()) {
				return c;
			}
		}
		return null;
	}

	/**
	 * A getter function for the course list
	 * @return - The course list
	 */
	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	/**
	 * A setter funciton for the course list
	 * @param courseList - The course list to be set to
	 */
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}

	/**
	 * A function that returns the course offering in the course list
	 * @param name - The name of the course
	 * @param number - The course number
	 * @param sec - The section number of a course
	 * @return - The course offering in the course list
	 */
	public CourseOffering getCourseOffering(String name, int number, int sec) {
		for (Course c : courseList) {
			if (c.getCourseName().equals(name) && c.getCourseNum() == number) {
				for (CourseOffering co : c.getCourseOffering()) {
					if (sec == co.getSecNum())
						return co;
				}
			}
		}
		return null;
	}

	/**
	 * A toString function that returns the contents of the course 
	 * in the course catalogue
	 */
	@Override
	public String toString() {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;
			st += "\n";
		}
		return st;
	}

}
