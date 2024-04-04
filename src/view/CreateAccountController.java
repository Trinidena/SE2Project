/*
package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CreateAccountController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ComboBox<String> accountTypeComboBox;

	@FXML
	private Button createAccountButton;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void initialize() {
		// Populate the ComboBox with options
		accountTypeComboBox.setItems(FXCollections.observableArrayList("Creator", "Business"));
	}

<<<<<<< HEAD
    @FXML
    void createAccount(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String selectedAccountType = accountTypeComboBox.getValue();
        
        // Input validation for username
        if (!isValidUsername(username)) {
            displayError("Invalid Username", "Username must have at least 4 letters and one number.");
            return;
        }
        
        // Input validation for password
        if (!isValidPassword(password)) {
            displayError("Invalid Password", "Password must have at least 6 letters and 3 numbers.");
            return;
        }
        
        // Proceed with creating the account
        if (selectedAccountType.equals("Creator")) {
            // Navigate to ShirtCreatorView.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShirtCreatorView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (selectedAccountType.equals("Business")) {
            // Navigate to BusinessView.fxml (or any other desired window)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Business.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private boolean isValidUsername(String username) {
        // Username must have at least 4 letters and one number
        return username.length() >= 4 && username.matches(".*\\d.*");
    }

    private boolean isValidPassword(String password) {
        // Password must have at least 6 letters and 3 numbers
        return password.length() >= 6 && password.replaceAll("\\D", "").length() >= 3;
    }

    private void displayError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
*/
package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CreateAccountController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private ComboBox<String> accountTypeComboBox;

	@FXML
	private Button createAccountButton;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void initialize() {
		// Populate the ComboBox with options
		accountTypeComboBox.setItems(FXCollections.observableArrayList("Creator", "Business"));
	}

	@FXML
	void createAccount(ActionEvent event) throws IOException {
		String username = usernameField.getText();
		String password = passwordField.getText();
		String selectedAccountType = accountTypeComboBox.getValue();

		// Validate username and password
		/*
		 * if (!isValidUsername(username) || !isValidPassword(password)) { // Show an
		 * alert for invalid input Alert alert = new Alert(AlertType.ERROR);
		 * alert.setTitle("Error"); alert.setHeaderText(null); alert.
		 * setContentText("		Invalid username and/or password.\n\n Username must have a minimum of 4 letters and 1 number.\n"
		 * +
		 * " Password must have a minimum of 6 letters and 3 numbers.\n\n 				Try again."
		 * ); alert.showAndWait(); return; }
		 */

		// Load scene based on selected account type
		if (selectedAccountType.equals("Creator")) {
			loadScene("/view/ShirtCreatorView.fxml");
		} else if (selectedAccountType.equals("Business")) {
			loadScene("/view/Business.fxml");
		}
	}

	private boolean isValidUsername(String username) {
		// Check if username has at least 4 letters and one number
		return username.matches("^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9]*$");
	}

	private boolean isValidPassword(String password) {
		// Check if password has at least 6 letters and 3 numbers
		return password.matches("^(?=.*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z].*[a-zA-Z])[a-zA-Z0-9]*$")
				&& password.replaceAll("[^0-9]", "").length() >= 3;
	}

	private void loadScene(String fxmlPath) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) createAccountButton.getScene().getWindow(); // Get the stage from the button's scene
		stage.setScene(scene);
		stage.show();
	}

}
