package ClientPackage;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class to display the Login View panel
 * @version 1.0
 * @since April 20, 2020
 */
public class LoginView extends JFrame{
	
	/**
	 * The enter button
	 */
	private JButton enter = new JButton("Enter");
	
	/**
	 * The cancel button
	 */
	private JButton cancel = new JButton("Cancel");
	
	/**
	 * The text field for the user name
	 */
	private JTextField username = new JTextField(10);
	
	/**
	 * The text field for the password
	 */
	private JTextField password = new JTextField(10);
	
	/**
	 * The title of the Login View panel
	 */
	private JLabel title = new JLabel("Enter your Credentials");
	
	/**
	 * A string to signify an admin or a student logging in
	 */
	private String user;
	
	/**
	 * The contstructor for the Login View
	 * @param user
	 */
	public LoginView(String user) {
		super("Login Screen");
		this.user = user;
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		center.add(new JLabel("Enter your ID Number: "));
		center.add(username);
		center.add(new JLabel("Enter your Password: "));
		center.add(password);
		north.add(title);
		south.add(enter);
		south.add(cancel);
		
		add("Center", center);
		add("North", north);
		add("South", south);
		pack();
	}
	
	/**
	 * A getter function for the user name
	 * @return - The string from the user name text field
	 */
	public String getUsername() {
		return getStringFromTextBox(username);
	}
	
	/**
	 * A getter function for the password
	 * @return - The string from the password text field
	 */
	public String getPassword() {
		return getStringFromTextBox(password);
	}
	
	/**
	 * A function that adds a listener for the enter button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addEnterListener(ActionListener listenForButton) {
		enter.addActionListener(listenForButton);
	}
	
	/**
	 * A function that adds a listener for the cancel button
	 * @param listenForButton - An action listener to wait for the button to be pressed
	 */
	public void addCancelListener(ActionListener listenForButton) {
		cancel.addActionListener(listenForButton);
	}
	
	/**
	 * A function that gets a string from a text field
	 * @param textField - The textfield to get the data from
	 * @return - The string in a text field
	 */
	private String getStringFromTextBox(JTextField textField) {
		return textField.getText();
	}
	
	/**
	 * A getter function for the type of user (admin or student)
	 * @return - The user name
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * A function that displays a message to the client
	 * @param message - The message to be displayed
	 */
	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
