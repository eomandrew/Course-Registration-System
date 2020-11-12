package ServerPackage;
import java.util.ArrayList;

/**
 * The Course Offering class of the server that contains the information
 * of a course in the course catalogue
 * @version 1.0
 * @since April 20, 2020
 */
public class CourseOffering {
	
	/**
	 * The section number of a course
	 */
	private int secNum;
	
	/**
	 * The section capacity of a course
	 */
	private int secCap;
	
	/**
	 * An object of the course
	 */
	private Course theCourse;
	
	/**
	 * An Array List of the course registrations of a student that is logged in 
	 */
	private ArrayList <Registration> offeringRegList;
	
	/**
	 * The constructor that creates the course offering
	 * @param secNum - The section number of the course to be set
	 * @param secCap - The section capacity of the course to be set
	 */
	public CourseOffering (int secNum, int secCap) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	/**
	 * A getter function of the section number
	 * @return - The section number
	 */
	public int getSecNum() {
		return secNum;
	}
	
	/**
	 * A setter function for the section number
	 * @param secNum - The section number to be set to
	 */
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	/**
	 * A getter function for the section capacity
	 * @return - The section capacity to be set to
	 */
	public int getSecCap() {
		return secCap;
	}
	
	/**
	 * A setter function for the section capacity
	 * @param secCap - The section capacity to be set to
	 */
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/**
	 * A getter function of the course
	 * @return - The course
	 */
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * A setter function for the course
	 * @param theCourse - the course to be set to
	 */
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	/**
	 * A function that adds a registration to a student
	 * @param registration - The registration to be added
	 */
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
	}
	
	/**
	 * A function that removes a registration from a student
	 * @param registration - The registration to be removed
	 */
	public void removeRegistration(Registration registration) {
		offeringRegList.remove(registration);
	}
	
	/**
	 * A toString method that prints the contents of a specific course
	 */
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section Num: " + getSecNum() + ", section cap: "+ getSecCap() +"\n";
		//We also want to print the names of all students in the section
		return st;
	}

}
