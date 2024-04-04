package view;

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
import model.server.ShirtCredentialsManager;

import java.io.IOException;

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

    private ShirtCredentialsManager manager;

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
                this.loadScene("/view/ShirtCreatorView.fxml");
            } else if ("Business".equals(selectedAccountType)) {
                this.loadScene("/view/Business.fxml");
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
    private boolean isValidUsername(String username) {
        return username.matches("^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9]*$");
    }

    /**
     * Validates the password based on predefined rules.
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
    private void loadScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        ModelAwareController controller = loader.getController();
        if (controller instanceof ModelAwareController) {
            controller.setModel(this.manager);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.createAccountButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
