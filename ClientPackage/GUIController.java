package ClientPackage;
import java.awt.Window;
import java.awt.event.ActionEvent;

/**
 * The class that handles socket in and out and writes to GUI
 * @version 1.0
 * @since April 20, 2020
 */
public class GUIController {
	
	/**
	 * An object of the main view
	 */
	private MainView main;
	
	/**
	 * An object of the communication controller
	 */
	private ComController coms;
	
	/**
	 * A constructor for the GUI Controller
	 * @param main - The main view to be set to
	 * @param com - The Communication Controller to be set to
	 */
	public GUIController(MainView main, ComController com) {
		this.main = main;
		coms = com;
		main.addSearchListener((ActionEvent ae) -> {
			search();
		});
		main.addCourseListener((ActionEvent ae) -> {
			addCourse();
		});
		main.addRemoveListener((ActionEvent ae) -> {
			remove();
		});
		main.addCourseCatalogueListener((ActionEvent ae) -> {
			viewCourseCatalogue();
		});
		main.addStudentCourseListener((ActionEvent ae) -> {
			viewStudentCourses();
		});
	}
	
	/**
	 * A function that displays the search catalogue view
	 */
	public void search() {
		SearchCatalogueView searchView = new SearchCatalogueView();
		searchView.setVisible(true);
		addSearchListeners(searchView);
	}
	
	/**
	 * A function that displays the add course view
	 */
	public void addCourse() {
		AddCourseView addView = new AddCourseView();
		addView.setVisible(true);
		addCourseListeners(addView);
	}
	
	/**
	 * A function that displays the remove course view
	 */
	public void remove() {
		RemoveCourseView removeView = new RemoveCourseView();
		removeView.setVisible(true);
		addRemoveListeners(removeView);
	}
	
	/**
	 * A function that displays the course catalogue to the GUI
	 */
	public void viewCourseCatalogue() {
		String message = coms.view(4);
		if(message != null) {
			main.addText(message);
		}
	}
	
	/**
	 * A function that displays the student courses to the GUI
	 */
	public void viewStudentCourses() {
		String message = coms.view(5);
		if(message != null) {
			main.addText(message);
		}
	}
	
	/**
	 * A function that adds listeners for the remove data, and return 
	 * to main screen functions
	 * @param removeView - The Remove Course View object to add action 
	 * listeners to wait for the buttons to be pressed
	 */
	public void addRemoveListeners(RemoveCourseView removeView) {
		removeView.addRemoveListener((ActionEvent ae)-> {
			removeData(removeView);
		});
		removeView.addMainScreenListener((ActionEvent ae) -> {
			returnToMainScreen(removeView);
		});
	}
	
	/**
	 * A function that adds listeners for the add course, and return
	 * to main screen functions
	 * @param addView - The Add Course View object to add action 
	 * listeners to wait for the buttons to be pressed
	 */
	public void addCourseListeners(AddCourseView addView) {
		addView.addDoneListener((ActionEvent ae) -> {
			addData(addView);
		});
		addView.addMainScreenListener((ActionEvent ae) -> {
			returnToMainScreen(addView);
		});
	}
	
	/**
	 * A function that adds listeners for the search data, and return
	 * to main screen functions
	 * @param searchView - The Search Catalogue View object to add action 
	 * listeners to wait for the buttons to be pressed
	 */
	public void addSearchListeners(SearchCatalogueView searchView) {
		searchView.addSearchListener((ActionEvent ae) -> {
			searchData(searchView);
		});
		searchView.addMainScreenListener((ActionEvent ae) -> {
			returnToMainScreen(searchView);
		});
	}
	
	/**
	 * A function that sends data from the search view to the 
	 * Communication Controller
	 * @param searchView - The Search Catalogue View object to gather 
	 * the requireddata
	 */
	public void searchData(SearchCatalogueView searchView) {
		String name = searchView.getCourseName();
		int ID = Integer.parseInt(searchView.getCourseID());
		String message = coms.search(name, ID, 1);
		if(message != null) {
			main.displayMessage(message);
		}
		searchView.dispose();
	}
	
	/**
	 * A function that sends data from the Add Course View to the 
	 * Communication Controller
	 * @param addView - The Add Course View object to gather 
	 * the required data
	 */
	public void addData(AddCourseView addView) {
		String name = addView.getCourseName();
		int ID = Integer.parseInt(addView.getCourseID());
		int secNum = Integer.parseInt(addView.getSectionNumner());
		String message = coms.add(name, ID, secNum, 2);
		if(message != null) {
			main.displayMessage(message);
		}
		addView.dispose();
	}
	
	/**
	 * A function that sends data from the the Remove Course View to the
	 * Communication Controller
	 * @param removeView - The Remove Course View object to gather
	 * the required date
	 */
	public void removeData(RemoveCourseView removeView) {
		String name = removeView.getCourseName();
		int ID = Integer.parseInt(removeView.getCourseID());
		int secNum = Integer.parseInt(removeView.getSecNum());
		String message = coms.remove(name, ID, secNum, 3);
		if(message != null) {
			main.displayMessage(message);
		}
		removeView.dispose();
	}
	
	/**
	 * A function that returns to the main screen
	 * @param searchView - The Search View Object to be disposed
	 */
	public void returnToMainScreen(Object searchView) {
		((Window) searchView).dispose();
	}
}
