package ServerPackage;
import java.util.ArrayList;

/**
 * The Student class that contains the information of a student
 * @version 1.0
 * @since April 20, 2020
 */
public class Student {
	
	/**
	 * The student name
	 */
	private String studentName;
	
	/**
	 * The student ID number
	 */
	private int studentId;
	
	/**
	 * An array of the registrations of a student
	 */
	private ArrayList<Registration> studentRegList;
	
	/**
	 * The number of courses taken by a student
	 */
	private int numOfCourses;
	
	/**
	 * An object of the DBManager to collect the information of a student
	 */
	private DBManager myData;
	
	/**
	 * A constructor that creates a student and loads their information into the database
	 * @param studentName - The student name to be set to
	 * @param studentId - The student ID number to be set to
	 */
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		reset();
		myData = new DBManager();
		studentRegList = new ArrayList <Registration>();
		//studentRegList = myData.getStudentRegFromDatabase(this);
	}

	/**
	 * A function that sets the number of courses a student is registed to to 0
	 */
	private void reset() {
		setNumOfCourses(0);
	}

	/**
	 * A getter function for the student name
	 * @return - The student name
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * A setter function for the student name
	 * @param studentName - The student name to be set to
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * A getter function for the student ID number
	 * @return - The student ID number
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * A setter function for the student ID number
	 * @param studentId - The student ID number to be set to
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * A function that adds a registration to a students list of registrations
	 * @param registration - The registration to be added
	 */
	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
	
	/**
	 * A function that removes a registration from a students list of registrations
	 * @param registration - The registration to be removed
	 */
	public void removeRegistration(Registration registration) {
		studentRegList.remove(registration);
	}

	/**
	 * A getter function for the number of courses
	 * @return - The number of courses a student is taking
	 */
	public int getNumOfCourses() {
		return numOfCourses;
	}

	/**
	 * A setter function for the number of courses that a student is taking
	 * @param numOfCourses - The number of courses to be set to
	 */
	public void setNumOfCourses(int numOfCourses) {
		this.numOfCourses = numOfCourses;
	}
	
	/**
	 * A function that checks if a student is registered to a course
	 * @param name - The name of the course to be checked
	 * @param id - The course number to be checked
	 * @return - A message that tells the client if a student is registered to the course or not
	 */
	public Registration checkRegistration(String name, int id)
	{
		for (Registration r : studentRegList)
		{
			if (r.getTheOffering().getTheCourse().getCourseName().equals(name) && r.getTheOffering().getTheCourse().getCourseNum() == id)
			{ 
				return r;
			}
		}
		System.out.println(name + " " + id + " could not be found in students registration list.");
		return null;
	}
	
	/**
	 * A function that gets a specific course registration of student
	 * @param name - The name of the course
	 * @param number - The course number
	 * @param section - The section number of a course
	 * @return - The registration of a student if found, null if not found
	 */
	public Registration getRegistration(String name, int number, int section)
	{
		for (Registration r : studentRegList)
		{
			if (name.equals(r.getTheOffering().getTheCourse().getCourseName())
					&& number == r.getTheOffering().getTheCourse().getCourseNum()
					&& section == r.getTheOffering().getSecNum())
			{
				return r;
			}
		}
		return null;
	}
	
	/**
	 * A toString method that returns the contents of a students registration list
	 * @return - The contents of  a students registration list
	 */
	@Override
	public String toString () {
		
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n" + "The courses in the students registration are: " + "\n";
		for (Registration r : studentRegList)
		{
			st += r;
		}
		return st;
	}

}
