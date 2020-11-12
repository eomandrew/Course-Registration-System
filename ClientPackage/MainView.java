package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The Main View class of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class MainView extends JFrame{
	
	/**
	 * The search button
	 */
	private JButton search = new JButton("Search Course Catalogues");
	
	/**
	 * The add course button
	 */
	private JButton addCourse = new JButton("Add Course to Student Courses");
	
	/**
	 * The remove course button
	 */
	private JButton remove = new JButton("Remove Course From Student Courses");
	
	/**
	 * The view all courses in the catalogue button
	 */
	private JButton courseCatalogue = new JButton("View all Courses in the Catalogue");
	
	/**
	 * The view all courses taken by a student function
	 */
	private JButton studentCourses = new JButton("View all Courses Taken by the Student");
	
	/**
	 * The title of the Main View panel
	 */
	private JLabel title = new JLabel("Please Choose from one of the Following Options");
	
	/**
	 * The text area of the panel
	 */
	private JTextArea text = new JTextArea(10, 10);

	/**
	 * The constructor for the Main View
	 */
	public MainView() {
		super("Main Menu");
		//setSize(500, 1000);
		text.setEditable(false);
		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		JScrollPane centerPanel = new JScrollPane(text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS)); 
		southPanel.add(search);
		southPanel.add(addCourse);
		southPanel.add(remove);
		southPanel.add(courseCatalogue);
		southPanel.add(studentCourses);
		northPanel.add(title);
		add("North", northPanel);
		add("South", southPanel);
		add("Center", centerPanel);
		pack();
	}
	
	/**
	 * A function that adds a listener for the search button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addSearchListener(ActionListener listenForButton) {
		search.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the add course button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addCourseListener(ActionListener listenForButton) {
		addCourse.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the remove course button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addRemoveListener(ActionListener listenForButton) {
		remove.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the view course catalogue button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addCourseCatalogueListener(ActionListener listenForButton) {
		courseCatalogue.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds alistener for the view student courses button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addStudentCourseListener(ActionListener listenForButton) {
		studentCourses.addActionListener(listenForButton);
	}
	
	/**
	 * A function that displays a message to the client in a panel
	 * @param message - The message to be displayed
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * A function that adds text to the text area
	 * @param data - The data to set the text to
	 */
	public void addText(String data) {
		text.setText(data);
	}
}
