package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Create Course View class for the admin part of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class CreateCourseView extends JFrame{
	
	/**
	 * A text field for the course name to be inputed
	 */
	private JTextField courseName = new JTextField(10);
	
	/**
	 * A text field for the course ID to be inputed
	 */
	private JTextField ID = new JTextField(10);
	
	/**
	 * The done button
	 */
	private JButton done = new JButton("Done");
	
	/**
	 * The return to main screen button
	 */
	private JButton mainScreen = new JButton("Return to main menu");
	
	/**
	 * The title of the Create Course View panel
	 */
	private JLabel title = new JLabel("Enter the Course Information");
	
	/**
	 * The constructor of the Create Couse View class
	 */
	public CreateCourseView() {
		super("Create a new course");
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		setSize(650, 175);
		northPanel.add(title);
		centerPanel.add(new JLabel("Enter the name of the course:"));
		centerPanel.add(courseName);
		centerPanel.add(new JLabel("Enter the ID of the course:"));
		centerPanel.add(ID);
		southPanel.add(done);
		southPanel.add(mainScreen);
		add("Center", centerPanel);
		add("North", northPanel);
		add("South", southPanel);
		pack();
	}
	
	/**
	 * A getter function of the course name from the text field
	 * @return - The course name
	 */
	public String getCourseName() {
		return getStringFromTextBox(courseName);
	}
	
	/**
	 * A getter fubnction of the course ID from the text field
	 * @return - The course ID
	 */
	public String getCourseID() {
		return getStringFromTextBox(ID);
	}
	
	/**
	 * A function that adds a listener for the done button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addDoneListener(ActionListener listenForButton) {
		done.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the main screen button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addMainScreenListener(ActionListener listenForButton) {
		mainScreen.addActionListener(listenForButton);
	}
	
	/**
	 * A function that gets the string from a text field
	 * @param textField - The text field to gather the data from
	 * @return - The text from the text field
	 */
	private String getStringFromTextBox(JTextField textField) {
		return textField.getText();
	}
}
