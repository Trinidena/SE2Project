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
import model.ModelAwareController;
import model.server.ShirtCredentialsManager;

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

	private ShirtCredentialsManager manager;
		
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void initialize() {
		accountTypeComboBox.setItems(FXCollections.observableArrayList("Creator", "Business"));
	}

	@FXML
	void createAccount(ActionEvent event) {
	    String username = usernameField.getText();
	    String password = passwordField.getText();
	    String selectedAccountType = accountTypeComboBox.getValue();
	    
	    if (!isValidUsername(username) || !isValidPassword(password)) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
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
	            loadScene("/view/ShirtCreatorView.fxml");
	        } else if ("Business".equals(selectedAccountType)) {
	            loadScene("/view/Business.fxml");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Load Error");
	        alert.setHeaderText("Scene Load Failure");
	        alert.setContentText("There was a problem loading the scene. Please try again or contact support.");
	        alert.showAndWait();
	    }
	}


	private boolean isValidUsername(String username) {
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
	    Object controller = loader.getController();

	    if (controller instanceof ModelAwareController) {
	        ((ModelAwareController) controller).setModel(manager);
	    }

	    Scene scene = new Scene(root);
	    Stage stage = (Stage) createAccountButton.getScene().getWindow(); // Get the stage from the button's scene
	    stage.setScene(scene);
	    stage.show();
	}


}
