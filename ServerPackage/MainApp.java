package ServerPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The runnable class of the server that waits for the user to 
 * press a button and splits the options into separate cases
 * @version 1.0
 * @since April 20, 2020
 */
public class MainApp implements Runnable{
	
	/**
	 * An object of the model class to execute the options
	 */
	private Model model;
	
	/**
	 * A String to read in the user name and password of students and admins
	 */
	private String[] data;
	
	/**
	 * A socket to be sent to the client
	 */
	private PrintWriter socketOut;
	
	/**
	 * A socket to read the input from the client
	 */
	private BufferedReader socketIn;
	
	/**
	 * A socket to initialize the input and output streams to the client
	 */
	private Socket aSocket;
	
	/**
	 * An object of the student that logs into the program
	 */
	private Student student;
	
	/**
	 * An object of the course catalogue to keep a record of existing courses
	 */
	private CourseCatalogue cc;
	
	/**
	 * The constructor of the main class
	 * @param s - The socket to be accepted by the server from the client
	 */
	public MainApp(Socket s) {
		aSocket = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		}catch(IOException e) {
			e.printStackTrace();
		}
		cc = new CourseCatalogue();
		//this.student = student;

	}
	
	/**
	 * A function that executes functions based off of the buttons presed by the client
	 */
	public void Options()
	{
		
		while (true)
		{
			
			try {
				data = socketIn.readLine().split(",");
				model = new Model(data, student, cc);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch(Integer.parseInt(data[data.length-1]))
			{
			case 1:
				socketOut.println(model.searchCourseCatalogues() + "\0");
				break;
			case 2:
				socketOut.println(model.addCourse()+ "\0");
				break;
			case 3:
				socketOut.println(model.removeCourse()+ "\0");
				break;
			case 4:
				socketOut.println(model.viewCatalogue()+ "\0");
				break;
			case 5:
				socketOut.println(model.viewStudentCourses()+ "\0");
				break;
			case 6:
				socketOut.println("hi\0"); // login for admin
				break;
			case 7:
				socketOut.println("help\0"); // login for admin
				break;
			case 8:
				socketOut.println(model.addCoursetoCatalogue() + "\0");
				break;
			case 9:
				String studentName = data[0];
				int studentID = Integer.parseInt(data[1]);
				student = new Student(studentName, studentID);
				model = new Model(data, student, cc);
				socketOut.println("\0");
				break;
			default:
				socketOut.println("\nInvalid input. Please try again!"+ "\0");
				break;
				
			}
		}
	}
	/**
	 * The runnable function that calls the Options function
	 */
	@Override
	public void run() {
		Options();
	}
}
