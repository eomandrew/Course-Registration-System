package ServerPackage;

import java.util.ArrayList;
import java.sql.*;

/**
 * The Database Manager class of the server that contains the contents
 * of the SQL Database
 * @version 1.0
 * @since April 20, 2020
 */
public class DBManager {

	/**
	 * An list of the courses
	 */
	ArrayList<Course> courseList;
	
	/**
	 * The database to collect information from
	 */
	private MySQLJDBC database;

	/**
	 * A constructor for the DBManager
	 */
	public DBManager() {
		courseList = new ArrayList<Course>();
	}

	/**
	 * A function that reads information from the SQL Database
	 * @return - The Array List of courses in the database
	 * @throws SQLException
	 */
	public ArrayList<Course> readFromDataBase() throws SQLException {

		database = new MySQLJDBC();
		database.initializeConnection();
		Statement myStmt = database.getConn().createStatement();
		ResultSet myRs = myStmt.executeQuery("SELECT * from lib.COURSE");

		while (myRs.next()) {
			String courseName = myRs.getString("course_name");
			int courseNum = myRs.getInt("course_num");
			boolean courseExists = false;
			Course c1 = new Course(courseName, courseNum);

			for (Course c : courseList) {
				if (c.getCourseName().equals(c1.getCourseName()) && c.getCourseNum() == c1.getCourseNum()) {
					courseExists = true;
					createCourseOffering(c, myRs.getInt("section_num"), myRs.getInt("section_cap"));
				}
			}
			if (!courseExists) {
				courseList.add(c1);
				createCourseOffering(c1, myRs.getInt("section_num"), myRs.getInt("section_cap"));
			}

		}
		return courseList;
	}

	/**
	 * A function that creates a course offering
	 * @param c - The course to add the course offering to
	 * @param secNum - The section number of the course offering
	 * @param secCap - The section capacity of the course offering
	 */
	public void createCourseOffering(Course c, int secNum, int secCap) {
		if (c != null) {
			CourseOffering theOffering = new CourseOffering(secNum, secCap);
			c.addOffering(theOffering);
		}
	}

	/**
	 * A function that gets a list of student registrations from the database
	 * @param s - The student that is logged into the server
	 * @return - The list of student registrations
	 */
//	public ArrayList<Registration> getStudentRegFromDatabase(Student s) {
//
//		ArrayList<Registration> regList = new ArrayList<Registration>();
//		try {
//			database = new MySQLJDBC();
//			database.initializeConnection();
//			Statement myStmt = database.getConn().createStatement();
//			ResultSet myRs = myStmt.executeQuery("SELECT * from lib.STUDENT");
//
//			myRs.absolute(s.getStudentId());
//
//			for (int i = 1; i < 7; i++) {
//				myRs.getString("course" + i);
//
//				while (!myRs.wasNull()) {
//					myRs.absolute(s.getStudentId());
//					String[] courseInfo = myRs.getString("course" + i).split(" ");
//					Course myCourse = new Course(courseInfo[0], Integer.parseInt(courseInfo[1]));
//					CourseOffering cO = new CourseOffering(Integer.parseInt(courseInfo[2]),
//							Integer.parseInt(courseInfo[3]));
//					myCourse.addOffering(cO);
//					cO.setTheCourse(myCourse);
//					Registration r = new Registration();
//					//r.completeRegistration(s, cO);
//					regList.add(r);
//					myRs.getString("course" + i);
//				}
//				i++;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return regList;
//
//	}

}
