package views;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ModelAwareController;
import model.user.User;
import server.ShirtCredentialsManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Create Account screen. Handles user input for creating a new account.
 * 
 * @author Trinidad Dena
 */
public class CreateAccountController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> accountTypeComboBox;

    @FXML
    private Button createAccountButton;
    
    @FXML
    private Button logInButton;

    private ShirtCredentialsManager manager;
    
    private List<User> users;

    /**
     * Sets the stage for this controller.
     * 
     * @param stage The primary stage of the application.
     */
    public void setStage(Stage stage) {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    void initialize() {
        this.accountTypeComboBox.setItems(FXCollections.observableArrayList("Creator", "Business"));
        this.manager = new ShirtCredentialsManager();
        this.users = manager.getUsers();
        for (User user: users) {
        	System.out.println(user.getCreatorName() + "\n" + user.getPassword() + "\n" + user.getRole());
        }
    }

    /**
     * Handles the Create Account action. Validates input and proceeds based on the selected account type.
     * 
     * @param event The ActionEvent triggered by clicking the Create Account button.
     */
    @FXML
    void createAccount(ActionEvent event) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        String selectedAccountType = this.accountTypeComboBox.getValue();

        if (!this.isValidUsername(username) || !this.isValidPassword(password)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username and/or password.\n\n"
                    + "Username must have a minimum of 4 letters and 1 number.\n"
                    + "Password must have a minimum of 6 letters and 3 numbers.\n\n"
                    + "Please try again.");
            alert.showAndWait();
            return;
        }

        try {
            if ("Creator".equals(selectedAccountType)) {
                this.loadScene("/views/ShirtCreatorView.fxml", createAccountButton);
            } else if ("Business".equals(selectedAccountType)) {
                this.loadScene("/views/Business.fxml", createAccountButton);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setHeaderText("Scene Load Failure");
            alert.setContentText("There was a problem loading the scene. Please try again or contact support.");
            alert.showAndWait();
        }
    }

    /**
     * Validates the username based on predefined rules.
     * 
     * @param username The username to validate.
     * @return true if the username meets the criteria, false otherwise.
     */
    private boolean isExistingUsername(String username) {
        boolean bool = false;
        for (User user : users) {
        	if(user.getCreatorName().equals(this.usernameField.getText())) {
        		bool = true;
        	}
        }
        return bool;
    }

    /**
     * Validates the password based on predefined rules.
     * 
     * @param password The password to validate.
     * @return true if the password meets the criteria, false otherwise.
     */
    private boolean isExistingPassword(String password) {
    	boolean bool = false;
        for (User user : users) {
        	if(user.getPassword().equals(this.passwordField.getText())) {
        		bool = true;
        	}
        }
        return bool;
    }
    
    /**
     * Handles the Create Account action. Validates input and proceeds based on the selected account type.
     * 
     * @param event The ActionEvent triggered by clicking the Create Account button.
     */
    @FXML
    void logIn(ActionEvent event) {
        String username = this.usernameField.getText();
        String password = this.passwordField.getText();
        String selectedAccountType = this.accountTypeComboBox.getValue();

        if (!this.isExistingUsername(username) || !this.isExistingPassword(password)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username and/or password does not exist.\n\n"
                    + "Assure you are putting in the correct account credentials.\n\n"
                    + "Please try again.");
            alert.showAndWait();
            return;
        }

        try {
            if ("Creator".equals(selectedAccountType)) {
                this.loadScene("/views/ShirtCreatorView.fxml", logInButton);
            } else if ("Business".equals(selectedAccountType)) {
                this.loadScene("/views/Business.fxml", logInButton);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setHeaderText("Scene Load Failure");
            alert.setContentText("There was a problem loading the scene. Please try again or contact support.");
            alert.showAndWait();
        }
    }
    
    /**
     * Validates the username based on existing accounts.
     * 
     * @param username The username to validate.
     * @return true if the username meets the criteria, false otherwise.
     */
    private boolean isValidUsername(String username) {
        return username.matches("^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9]*$");
    }

    /**
     * Validates the password based on existing accounts.
     * 
     * @param password The password to validate.
     * @return true if the password meets the criteria, false otherwise.
     */
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9]*$")
                && password.replaceAll("[^0-9]", "").length() >= 3;
    }

    /**
     * Loads a new scene based on the specified FXML file.
     * 
     * @param fxmlPath The path to the FXML file.
     * @throws IOException If the FXML file cannot be loaded.
     */
    private void loadScene(String fxmlPath, Button clickedButton) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        ModelAwareController controller = loader.getController();
        if (controller instanceof ModelAwareController) {
            controller.setModel(this.manager);
            this.manager.addUser(new User(this.usernameField.getText(), this.passwordField.getText(), this.accountTypeComboBox.getValue()));
//            controller.setUsername(this.usernameField.getText());
//            controller.setPassword(this.passwordField.getText());
//            controller.setRole(this.accountTypeComboBox.getValue());
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) clickedButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
