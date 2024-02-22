package tshirtgenerator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class is the entry point into the Name source application
 * 
 * @author Chris
 * @version Spring 2023
 */
public class Main extends Application {

	private static final String WINDOW_TITLE = "Project 1: Christian Turner";
	private static final String GUI_FXML = "/view/ShirtCreatorView.fxml";

	/**
	 * Constructs a new Application object for the Name Source program.
	 * 
	 * @precondition none
	 * @postcondition the object is ready to execute
	 */
	public Main() {
		super();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane thePane = this.loadGui();
			Scene theScene = new Scene(thePane);
			primaryStage.setScene(theScene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
		} catch (IllegalStateException | IOException anException) {
			anException.printStackTrace();
		}
	}

	private Pane loadGui() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(GUI_FXML));
		return (Pane) loader.load();
	}

	/**
	 * Launches the application.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param args - not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}