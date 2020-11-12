package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The Admin View of the client
 * @version 1.0
 * @since April 20, 2020
 */
public class AdminView extends JFrame{
	
	/**
	 * A button to create a new course
	 */
	private JButton createCourse = new JButton("Create new course");
	
	/**
	 * The exit button
	 */
	private JButton exit = new JButton("Exit");
	
	/**
	 * The title of the Admin View panel
	 */
	private JLabel title = new JLabel("Admin");
	
	/**
	 * The size of the panel
	 */
	private JTextArea text = new JTextArea(10, 10);
	
	/**
	 * A button to view all courses in the catalogue
	 */
	private JButton courseCatalogue = new JButton("View all Courses in the Catalogue");
	
	/**
	 * A constructor for the Admin View panel
	 */
	public AdminView() {
		super("Admin Menu");
		setSize(500, 500);
		text.setEditable(false);
		JPanel southPanel = new JPanel();
		JPanel northPanel = new JPanel();
		JScrollPane centerPanel = new JScrollPane(text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		southPanel.add(exit);
		southPanel.add(createCourse);
		northPanel.add(title);
		southPanel.add(courseCatalogue);
		add("North", northPanel);
		add("South", southPanel);
		add("Center", centerPanel);
	}
	
	/**
	 * A function that adds a listener for the exit function
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addExitListener(ActionListener listenForButton) {
		exit.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the create course button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addCreateListener(ActionListener listenForButton) {
		createCourse.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the course catalogue button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addCourseCatalogueListener(ActionListener listenForButton) {
		courseCatalogue.addActionListener(listenForButton);
	}
	
	/**
	 * A function that sets text to the panel
	 * @param data - The data to be set to the text
	 */
	public void addText(String data) {
		text.setText(data);
	}
	
	/**
	 * A function that displays a message to the client
	 * @param message - The message to be displayed
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
