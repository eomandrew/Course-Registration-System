package ClientPackage;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The Opening View for a user to signify an admin or a student login
 * @version 1.0
 * @since April 20, 2020
 */
public class OpeningView extends JFrame{
	
	/**
	 * The admin button
	 */
	private JButton admin = new JButton("Admin");
	
	/**
	 * The student button
	 */
	private JButton student = new JButton("Student");
	
	/**
	 * The title of the panel
	 */
	private JLabel title = new JLabel("Login is as:");
	
	/**
	 * The text area of the panel
	 */
	private JTextArea text = new JTextArea(10, 10);
	
	/**
	 * A constructor for the Opening View
	 */
	public OpeningView() {
		super("");
		setSize(500, 500);
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		JScrollPane centerPanel = new JScrollPane(text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text.setEditable(false);
		north.add(title);
		south.add(student);
		south.add(admin);
		add("North", north);
		add("South", south);
		add("Center", centerPanel);
	}
	
	/**
	 * A function that adds a listener for the admin button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addAdminListener(ActionListener listenForButton) {
		admin.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the student button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addStudentListener(ActionListener listenForButton) {
		student.addActionListener(listenForButton);
	}
}
