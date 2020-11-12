package ServerPackage;

/**
 * The model class of the server that executes an option selected
 * by a student or an admin
 * @version 1.0
 * @since April 20, 2020
 */
public class Model {
	
	/**
	 * An object of the course catalogue to update the database
	 * when performing different tasks
	 */
	private CourseCatalogue cc;
	
	/**
	 * A string of data used from the client to make updates to the database
	 */
	private String[] data;
	
	/**
	 * An object of the student that is logged in to the server
	 */
	private Student student;
	
	/**
	 * A constructor for the server model
	 * @param data - The string of data from the client
	 * @param student - An object of the student that is logged in
	 * @param cc - The course catalogue to be updated by changes made by the student/admin
	 */
	public Model(String[] data, Student student, CourseCatalogue cc) {
		this.cc = cc;
		this.data = data;
		this.student = student;
	}
	
	/**
	 * A function that adds a course to the course catalogue by the admin
	 * @return - A string to tell the client that a course has been added
	 */
	public String addCoursetoCatalogue() {
		String courseName = data[0];
		int courseID = Integer.parseInt(data[1]);
		String result = cc.addCourse(courseName, courseID);
		return result;
	}
	
	/**
	 * A function that searches the course catalogue for a specific course
	 * @return - A message that tells the client whether a course has been found or not
	 */
	public String searchCourseCatalogues() {
		String courseName = data[0];
		int courseID = Integer.parseInt(data[1]);
		Course course = cc.searchCat(courseName, courseID);
		if(course == null) {
			return "Course Could Not Be Found!";
		}
		else {
			return course.toString();
		}
	}

	/**
	 * A function that adds a course to the student that is logged in
	 * @return - A message that tells the client whether the course was added
	 * to their registration or not
	 */
	public String addCourse() {
		String courseName = data[0];
		int courseID = Integer.parseInt(data[1]);
		int secNum = Integer.parseInt(data[2]);
		if (cc.searchCat(courseName, courseID) != null)
		{
			Registration reg = new Registration();
			return reg.completeRegistration(student, cc.getCourseOffering(courseName, courseID, secNum));
		}
		else
		{
			return "The course could not be found and was not added to the students registration.";
		}
	}

	/**
	 * A function that removes a course from a students registered courses
	 * @return - A message that tells the client whether the course was removed or not
	 */
	public String removeCourse() {
		String courseName = data[0];
		int courseID = Integer.parseInt(data[1]);
		int secNum = Integer.parseInt(data[2]);
		Registration reg = student.getRegistration(courseName, courseID, secNum);
		if (student.checkRegistration(courseName, courseID) != null)
		{
			return reg.deleteRegistration(student, cc.getCourseOffering(courseName, courseID, secNum));
		}
		else {
			return "The course could not be found and was not removed from the students registration.";
		}
	}

	/**
	 * A function that displays the course catalogue to the client
	 * @return - The course catalogue
	 */
	public String viewCatalogue() {
		return cc.toString();
	}

	/**
	 * A function that displays the courses taken by the student that is logged in
	 * @return
	 */
	public String viewStudentCourses() {
		return student.toString();
	}
	
}
