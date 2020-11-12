package ClientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Communication Controller class of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class ComController {
	
	/**
	 * The socket used to collect the input and output streams to the server
	 */
	private Socket aSocket;
	
	/**
	 * An output socket to be sent to the server
	 */
	private PrintWriter socketOut;
	
	/**
	 * An input socket to be obtained from the server
	 */
	private BufferedReader socketIn;
	
	/**
	 * A constructor the the Communication Controller class
	 * @param serverName - The name of the server
	 * @param portNumber - The port number used by the server
	 */
	public ComController(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		}catch(IOException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * A function that sends the login information of a student/admin
	 * @param name - The name of a student/admin
	 * @param password - The password of a student/admin
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String login(String name, String password, int caseNum) {
		socketOut.println(name + "," + password + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends the search information from the client to the server
	 * @param name - The name of a course to be searched
	 * @param ID - The course ID to be searched
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String search(String name, int ID, int caseNum) {
		socketOut.println(name + "," + ID + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends the information of a course to be added to the server
	 * @param name - The name of a course
	 * @param ID - The course ID
	 * @param secNum - The section number of a course
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String add(String name, int ID, int secNum, int caseNum) {
		socketOut.println(name + "," + ID + "," + secNum + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends information of a course to be removed to the server
	 * @param name - The name of a course
	 * @param ID - The course ID
	 * @param secNum - The section number of a course
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String remove(String name, int ID, int secNum, int caseNum) {
		socketOut.println(name + "," + ID + "," + secNum + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends the case number of a specific function to the server
	 * @param caseNum - The case number of a specific function to be sent
	 * @return - A string from the client to the server
	 */
	public String view(int caseNum) {
		socketOut.println(caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends information of a course to be created to the server
	 * @param name - The name of a course
	 * @param ID - The course ID
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String createCourse(String name, int ID, int caseNum) {
		socketOut.println(name + "," + ID + "," + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that sends information of a student to the server
	 * @param name - The name of a student
	 * @param ID - The student ID
	 * @param caseNum - The case number of this function to be sent to the server
	 * @return - A string from the client to the server
	 */
	public String sendStudent(String name, int ID, int caseNum) {
		socketOut.println(name + "," + ID + "," + caseNum);
		return communicate();
	}
	
	/**
	 * A function that signifies communication between the client and the server
	 * @return
	 */
	public String communicate() {
		String response = "";
		while(true) {
			try {
				response += socketIn.readLine() + "\n";
				if(response.contains("\0")) {
					response = response.replace("\0", "\n");
					return response;
				}
			}catch(IOException e) {
				e.printStackTrace();
				socketOut.flush();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		ComController myClient = new ComController("localhost", 9898); 
		OpeningView open = new OpeningView();
		open.setVisible(true);
		OpeningController opening = new OpeningController(open, myClient);
	}
}
