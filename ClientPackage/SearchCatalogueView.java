package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The class for the Search Catalogue View of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class SearchCatalogueView extends JFrame{
	
	/**
	 * The text field for the course name
	 */
	private JTextField courseName = new JTextField(10);
	
	/**
	 * The text field for the course ID
	 */
	private JTextField ID = new JTextField(10);
	
	/**
	 * The search button
	 */
	private JButton search = new JButton("Search");
	
	/**
	 * The return to main menu button
	 */
	private JButton mainScreen = new JButton("Return to main menu");
	
	/**
	 * The title of the Search Catalogue View Panel
	 */
	private JLabel title = new JLabel("Enter the Course Information");
	
	/**
	 * The constructor for the Search Catalogue View
	 */
	public SearchCatalogueView() {
		super("Search for Course");
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		setSize(400, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		northPanel.add(title);
		centerPanel.add(new JLabel("Enter the name of the course:"));
		centerPanel.add(courseName);
		centerPanel.add(new JLabel("Enter the ID of the course:"));
		centerPanel.add(ID);
		southPanel.add(search);
		southPanel.add(mainScreen);
		add("Center", centerPanel);
		add("North", northPanel);
		add("South", southPanel);
	}
	
	/**
	 * A function that gets the course name from the course name text field
	 * @return - The course name
	 */
	public String getCourseName() {
		return getStringFromTextBox(courseName);
	}
	
	/**
	 * A function that gets the course ID from the course ID text field
	 * @return - The course ID
	 */
	public String getCourseID() {
		return getStringFromTextBox(ID);
	}
	
	/**
	 * A function that gets data from a text field
	 * @param textField - The text field to gather the data from
	 * @return - The string in the text field
	 */
	private String getStringFromTextBox(JTextField textField) {
		return textField.getText();
	}
	
	/**
	 * A function that adds a listener for the search button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addSearchListener(ActionListener listenForButton) {
		search.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the return to main screen button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addMainScreenListener(ActionListener listenForButton) {
		mainScreen.addActionListener(listenForButton);
	}
}
