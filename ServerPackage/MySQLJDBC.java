package ServerPackage;

import java.sql.*;

/**
 * This Class connects to the database and initializes the database to the set values in the main method.
 *
 */
public class MySQLJDBC implements IDBCredentials {

	/**
	 * The connection to the SQL
	 */
	private Connection conn;
	/**
	 * The ResultSet for the student information for login
	 */
	private ResultSet studentSet;
	/**
	 * The ResultSet for the course information in the database a student can take
	 */
	private ResultSet courseSet;
	/**
	 * This method starts the connection to the driver and SQL
	 */
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}

	}
	/**
	 * closes the connection
	 */
	public void close() {
		try {
			// rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * inserts a student into database
	 * @param id the ID number
	 * @param name their full name
	 * @param password their password
	 */
	public void insertStudentUserPreparedStatement(int id, String name, String password) {
		try {
			String query = "INSERT INTO STUDENT (ID, name , password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			int rowCount = pStat.executeUpdate();
			System.out.println("student row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting user");
			e.printStackTrace();
		}
	}
	/**
	 * This creates a table for the student database
	 */
	public void createStudentTable() {
		String sql = "CREATE TABLE IF NOT EXISTS STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				+ " password VARCHAR(255), "+" PRIMARY KEY ( id ))";
//		String sql = "CREATE TABLE IF NOT EXISTS STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
//				+ " password VARCHAR(255), " + "course1 VARCHAR(255), " + "course2 VARCHAR(255), " 
//				+ "course3 VARCHAR(255), " +"course4 VARCHAR(255), " +"course5 VARCHAR(255), " 
//				+ "course6 VARCHAR(255), " +" PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			studentSet = stmt.executeQuery("SELECT * FROM COURSE");
			
			
//			Statement stmt = conn.createStatement(); // construct a statement
//			stmt.executeUpdate(sql); // execute my query (i.e. sql)
//			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created student table in given database...");
	}
	/**
	 * insert a admin user into the database
	 * @param id their id
	 * @param name their name
	 * @param password their password
	 */
	public void insertAdminUserPreparedStatement(int id, String name, String password) {
		try {
			String query = "INSERT INTO ADMIN (ID, name , password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query,
				    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, password);
			int rowCount = pStat.executeUpdate();
			System.out.println("student row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			System.out.println("problem inserting admin user");
			e.printStackTrace();
		}
	}
	/**
	 * creates a table for the admin users
	 */
	public void createAdminTable() {
		String sql = "CREATE TABLE IF NOT EXISTS ADMIN " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				+ " password VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created admin table in given database...");
	}
	/**
	 * creates a course table
	 */
	public void createCourseTable() {
		// format (course name, course num, section number)
		String sql = "CREATE TABLE IF NOT EXISTS COURSE " + "(course_name VARCHAR(4), " + " course_num INTEGER not NULL, "
				+ " section_num INTEGER not NULL , " + "section_cap INTEGER not NULL )";

		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			courseSet = stmt.executeQuery("SELECT * FROM COURSE");
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created course table in given database...");
	}
	/**
	 * inserts a course into the database
	 * @param courseName the name of the course (ex. "ENSF")
	 * @param courseNum the number of the course (ex. 409)
	 * @param sectionNum the section number of the created course
	 * @param secCap the section cap for max number of students
	 */
	public void insertCourseUserPreparedStatement(String courseName, int courseNum, int sectionNum, int secCap) {
		try {
			String query = "INSERT INTO COURSE (course_name , course_num , section_num, section_cap) values(?,?,?,?)";
			PreparedStatement courseStmt = conn.prepareStatement(query);
			courseStmt.setString(1, courseName);
			courseStmt.setInt(2, courseNum);
			courseStmt.setInt(3, sectionNum);
			courseStmt.setInt(4, secCap);
			int rowCount = courseStmt.executeUpdate();
			//System.out.println("row Count = " + rowCount);
			courseStmt.close();
		} catch (SQLException e) {
			System.out.println("problem inserting course");
			e.printStackTrace();
		}
	}
	/**
	 * The main method to create and add information to the database. Please comment out the lines 194-244 after the program has run
	 * once to create the database
	 * 
	 */
	public static void main(String[] args0) {
		MySQLJDBC myApp = new MySQLJDBC();
		myApp.initializeConnection();
		myApp.createStudentTable();  	//only call this function once then comment out.
		myApp.createCourseTable();		//only call this function once then comment out.
		myApp.createAdminTable();		//only call this function once then comment out.

		myApp.insertStudentUserPreparedStatement(1, "Andy Eom", "123");
		myApp.insertStudentUserPreparedStatement(2, "Steven Ha", "123");
		myApp.insertStudentUserPreparedStatement(3, "Baylee Cheung", "123");
		myApp.insertStudentUserPreparedStatement(4, "Tessa", "123");
		myApp.insertStudentUserPreparedStatement(5, "Yassin Bayoumy", "123");
		myApp.insertStudentUserPreparedStatement(6, "Hugh", "123");
		myApp.insertStudentUserPreparedStatement(7, "Joanna", "123");
		myApp.insertStudentUserPreparedStatement(8, "Barry", "123");
		myApp.insertStudentUserPreparedStatement(9, "Ben", "123");
		myApp.insertStudentUserPreparedStatement(10, "Drew", "123");
		myApp.insertStudentUserPreparedStatement(11, "Dixie", "123");
		
		myApp.insertCourseUserPreparedStatement("ENGG", 233, 1, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 233, 2, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 233, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("ENGG", 202, 1, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 202, 2, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 202, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("ENGG", 201, 1, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 201, 2, 200);
		myApp.insertCourseUserPreparedStatement("ENGG", 201, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("PHYS", 259, 1, 200);
		myApp.insertCourseUserPreparedStatement("PHYS", 259, 2, 200);
		myApp.insertCourseUserPreparedStatement("PHYS", 259, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("PHYS", 369, 1, 200);
		myApp.insertCourseUserPreparedStatement("PHYS", 369, 2, 200);
		myApp.insertCourseUserPreparedStatement("PHYS", 369, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("MATH", 211, 1, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 211, 2, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 211, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("MATH", 275, 1, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 275, 2, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 275, 3, 200);
		
		myApp.insertCourseUserPreparedStatement("MATH", 375, 1, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 375, 2, 200);
		myApp.insertCourseUserPreparedStatement("MATH", 375, 3, 200);

		myApp.insertAdminUserPreparedStatement(1, "Moshirpour", "123");
		myApp.close();
	}
	
	public Connection getConn () {
		return this.conn;
	}

}