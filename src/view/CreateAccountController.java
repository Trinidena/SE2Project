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

		if (username.isEmpty() || password.isEmpty() || selectedAccountType == null) {
			// Show an alert or handle empty fields
			return;
		}

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
}
