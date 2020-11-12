package ServerPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The controller class of the server used to communicate with the client
 * @version 1.0
 * @since April 20, 2020
 */
public class Controller {
	
	/**
	 * A server socket to be connected to a client under the same port number
	 */
	private ServerSocket serverSocket;
	
	/**
	 * An object of a student that is logged into the server
	 */
	private Student student;
	
	/**
	 * A thread pool to support multiple clients at a time
	 */
	private ExecutorService pool;
	
	/**
	 * The constructor of the controller that initializes the server socket
	 * and creates a cached thread pool to support multiple clients
	 * @param port - The port number used to accept a client
	 */
	public Controller(int port) {
		try {
			serverSocket = new ServerSocket(port);
			pool = Executors.newCachedThreadPool();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A setter function of a student
	 * @param name - The name of the student
	 * @param id - The ID number of a student
	 */
	public void setStudent (String name, int id) {
		student = new Student(name, id);
	}
	
	/**
	 * A function that accepts inputs from the student and executes the
	 * runnable MainApp class
	 */
	public void communicate() {
		try {
			while(true) {
				MainApp main = new MainApp(serverSocket.accept());
				pool.execute(main);
			}
		}catch(IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}
	}
	
	public static void main(String [] args) throws IOException{
			Controller myServer = new Controller(9898);
			System.out.println("Server is running");
			myServer.communicate();
		}
}
