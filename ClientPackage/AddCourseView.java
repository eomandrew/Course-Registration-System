package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.*;

import ServerPackage.MySQLJDBC;

/**
 * The course view of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class AddCourseView extends JFrame{
	
	/**
	 * A text field for the course name
	 */
	private JTextField courseName = new JTextField(10);
	
	/**
	 * A text field for the course ID
	 */
	private JTextField ID = new JTextField(10);
	
	/**
	 * A text field for the section number
	 */
	private JTextField sectionNum = new JTextField(10);
	
	/**
	 * The "Done" button
	 */
	private JButton done = new JButton("Done");
	
	/**
	 * A button to return to the main menu
	 */
	private JButton mainScreen = new JButton("Return to main menu");
	
	/**
	 * The title of the Course View panel
	 */
	private JLabel title = new JLabel("Enter the Course Information");
	
	/**
	 * An object of the SQL database to be accessed
	 */
	private MySQLJDBC myApp;
	
	/**
	 * A constructor for the course view 
	 */
	public AddCourseView() {
		super("Add course to student");
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		setSize(650, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		northPanel.add(title);
		centerPanel.add(new JLabel("Enter the name of the course:"));
		centerPanel.add(courseName);
		centerPanel.add(new JLabel("Enter the ID of the course:"));
		centerPanel.add(ID);
		centerPanel.add(new JLabel("Enter the Section Number of the course:"));
		centerPanel.add(sectionNum);
		southPanel.add(done);
		southPanel.add(mainScreen);
		add("Center", centerPanel);
		add("North", northPanel);
		add("South", southPanel);
		
		myApp = new MySQLJDBC();
		myApp.initializeConnection();
	}
	
	/**
	 * A getter function for the course name from the client input
	 * @return - The course name
	 */
	public String getCourseName() {
		return getStringFromTextBox(courseName);
	}
	
	/**
	 * A getter function for the course ID from the client input
	 * @return - The Course ID
	 */
	public String getCourseID() {
		return getStringFromTextBox(ID);
	}
	
	/**
	 * A getter function for the section number from the client input
	 * @return - The section number
	 */
	public String getSectionNumner() {
		return getStringFromTextBox(sectionNum);
	}
	
	/**
	 * A function that gets the client input in the input text field
	 * @param textField - The input text field
	 * @return - Data gathered from the input text field
	 */
	private String getStringFromTextBox(JTextField textField) {
		return textField.getText();
	}
	
	/**
	 * A function that adds a listener for the "Done" button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addDoneListener(ActionListener listenForButton) {
		done.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener to return to the main screen
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addMainScreenListener(ActionListener listenForButton) {
		mainScreen.addActionListener(listenForButton);
	}
}
