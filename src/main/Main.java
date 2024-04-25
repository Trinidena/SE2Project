package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.CreateAccountController;
import javafx.scene.Parent;


/**
 * This class is the entry point into the Name source application
 * 
 * @author Nate, Trinidad, and Chris
 * @version Spring 2024
 */
public class Main extends Application {

    private static final String WINDOW_TITLE = "TeeGenius";
    private static final String CREATEACCOUNT_FXML = "/views/CreateAccount.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CREATEACCOUNT_FXML));
        Parent root = loader.load();
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setStage(primaryStage);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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