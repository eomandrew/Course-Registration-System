package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The Remove Course View of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class RemoveCourseView extends JFrame{
	
	/**
	 * The text field for the course name
	 */
	private JTextField courseName = new JTextField(10);
	
	/**
	 * The text field for the course ID
	 */
	private JTextField ID = new JTextField(10);
	
	/**
	 * The text field for the course section number
	 */
	private JTextField secNum = new JTextField(10);
	
	/**
	 * The remove button
	 */
	private JButton remove = new JButton("Remove");
	
	/**
	 * The return to main menu button
	 */
	private JButton mainScreen = new JButton("Return to main menu");
	
	/**
	 * The title of the Remove Course View panel
	 */
	private JLabel title = new JLabel("Enter the Course Information");
	
	/**
	 * The constructor for the Remove Course View
	 */
	public RemoveCourseView() {
		super("Remove course to student");
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
		centerPanel.add(new JLabel("Enter the Section Number of the course: "));
		centerPanel.add(secNum);
		southPanel.add(remove);
		southPanel.add(mainScreen);
		add("Center", centerPanel);
		add("North", northPanel);
		add("South", southPanel);
	}
	
	/**
	 * A function that gets the course name from the text field
	 * @return - The string in the text field
	 */
	public String getCourseName() {
		return getStringFromTextBox(courseName);
	}
	
	/**
	 * A function that gets the course ID from the text field
	 * @return - The string in the text field
	 */
	public String getCourseID() {
		return getStringFromTextBox(ID);
	}
	
	/**
	 * A function that gets the course section number from the text field
	 * @return - The string in the text field
	 */
	public String getSecNum() {
		return getStringFromTextBox(secNum);
	}
	
	/**
	 * A function that gets the string from a text field
	 * @param textField - The text field to gather the data from
	 * @return - The string in the text field
	 */
	private String getStringFromTextBox(JTextField textField) {
		return textField.getText();
	}
	
	/**
	 * A function that adds a listener for the remove button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addRemoveListener(ActionListener listenForButton) {
		remove.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the main screen button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addMainScreenListener(ActionListener listenForButton) {
		mainScreen.addActionListener(listenForButton);
	}
}
