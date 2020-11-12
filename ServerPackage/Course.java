package ServerPackage;
import java.util.ArrayList;

/**
 * The course class of the server that contains the contents of each course
 * @version 1.0
 * @since April 20, 2020
 */
public class Course {

	/**
	 * The course name
	 */
	private String courseName;
	
	/**
	 * The course number
	 */
	private int courseNum;
	
	/**
	 * The Array List of pre-requisite courses
	 */
	private ArrayList<Course> preReq;
	
	/**
	 * The Array List of course offerings
	 */
	private ArrayList<CourseOffering> offeringList;

	/**
	 * A constructor for the course
	 * @param courseName - The course name to be set to
	 * @param courseNum - The course number to be set to
	 */
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	/**
	 * A function that adds an offering to the ArrayList of courses
	 * @param offering - The offering to be added
	 */
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				return;
			}
			
			offeringList.add(offering);
		}
	}

	/**
	 * A getter function for the course name
	 * @return - The course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * A setter function for the course name
	 * @param courseName - The course name to be set to
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * A getter function for the course number
	 * @return - The course number
	 */
	public int getCourseNum() {
		return courseNum;
	}

	/**
	 * A setter function for the course number
	 * @param courseNum - The course number to be set to
	 */
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	/**
	 * A function that gets a course offering from the ArrayList
	 * @param i - The index of the course offering
	 * @return - The course offering if found, null if index is out of bounds
	 */
	public CourseOffering getCourseOfferingAt(int i) {
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}

	public ArrayList<CourseOffering> getCourseOffering() {
		return offeringList;
	}
	
	/**
	 * A toString method for the course
	 * @return - The contents of a course
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}

}
