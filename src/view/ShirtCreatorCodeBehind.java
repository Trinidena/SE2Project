package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Color;
import model.Material;
import model.NeckStyle;
import model.ShirtAttributes;
import model.Size;

public class ShirtCreatorCodeBehind {

	private ObservableList<String> items = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<Double> backLengthComboBox;

	@FXML
	private ComboBox<Color> colorComboBox;

	@FXML
	private Button deleteButton;

	@FXML
	private TextArea designedTextArea;

	@FXML
	private Button editButton;

	@FXML
	private ComboBox<Material> materialComboBox;

	@FXML
	private TextField nameTextField;

	@FXML
	private ComboBox<String> pocketComboBox;

	@FXML
	private TextArea presetsTextArea;

	@FXML
	private Button requestButton;

	@FXML
	private Button saveButton;

	@FXML
	private ImageView shirtImageView;

	@FXML
	private MenuBar shirtMenubar;

	@FXML
	private ComboBox<Double> shoulderLengthComboBox;

	@FXML
	private ComboBox<Size> sizeComboBox;

	@FXML
	private ComboBox<?> styleCombobox;

	@FXML
	private Button submitButton;

	@FXML
	private Button viewButton;

	private GraphicsContext graphicsContext;

	private BufferedImage canvas;
	
	@FXML
	void handleLoadButton(ActionEvent event) throws IOException {
	   FileChooser fc = new FileChooser();
	      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	   File selectedFile = fc.showOpenDialog(stage);
	   BufferedImage img = ImageIO.read(selectedFile);
	   
    Image image = new Image(selectedFile.toURI().toString());
    graphicsContext.drawImage(image, 0, 0, (canvas.getWidth()), (canvas.getHeight()));
	}
	
	public void addRequest(String request) {
	    items.add(request);
	}
	
	public void clearRequests() {
	    items.clear();
	}
	
	@FXML
	void onShowRequestsButtonClick(ActionEvent event) {
	    Stage stage = new Stage(); // Create a new stage (window)
	    stage.setTitle("List of Requests");
	    VBox layout = new VBox(10);
	    ListView<String> listView = new ListView<>();
	    listView.setItems(items); // Use the items field

	    layout.getChildren().add(listView);

	    Scene scene = new Scene(layout, 300, 250);
	    stage.setScene(scene);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.show();
	    }

	@FXML
	void onRequestButtonClick(ActionEvent event) {
	    Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
	    confirmationDialog.setTitle("Request Confirmation");
	    confirmationDialog.setHeaderText("Request Shirt Design");
	    confirmationDialog.setContentText("Are you sure you want to request this shirt design?");

	    Optional<ButtonType> result = confirmationDialog.showAndWait();
	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        // User chose OK
	        // Place your request logic here
	    	//addRequest();
	        Alert requestConfirmationDialog = new Alert(Alert.AlertType.INFORMATION); // Use INFORMATION Alert for feedback
	        requestConfirmationDialog.setTitle("Request Successful");
	        requestConfirmationDialog.setHeaderText(null); // No header text
	        requestConfirmationDialog.setContentText("Shirt design requested successfully.");
	        requestConfirmationDialog.showAndWait(); // Show the dialog and wait
	    } else {
	        // User chose Cancel or closed the dialog

	        Alert noConfirmationDialog = new Alert(Alert.AlertType.INFORMATION); // Use INFORMATION Alert for feedback
	        noConfirmationDialog.setTitle("Request Cancelled");
	        noConfirmationDialog.setHeaderText(null); // No header text
	        noConfirmationDialog.setContentText("Shirt design request cancelled.");
	        noConfirmationDialog.showAndWait(); // Show the dialog and wait
	    }
	}

	
	private void updateUIBasedOnSize(Size size) {
        ShirtAttributes shirtAttributes = ShirtAttributes.createPresetForSize(size);

        // Now update other UI components based on the preset
        materialComboBox.setValue(shirtAttributes.getMaterial());
        colorComboBox.setValue(shirtAttributes.getColor());
        backLengthComboBox.setValue(shirtAttributes.getBackLength());
        shoulderLengthComboBox.setValue(shirtAttributes.getShoulderWidth());
        pocketComboBox.setValue(shirtAttributes.hasPocket() ? "Yes" : "No");
        // Update other fields as necessary, such as text fields for measurements
    }
	
	public void initialize() {
	    sizeComboBox.getItems().setAll(Size.values());
	    materialComboBox.getItems().setAll(Material.values());
	    //neckStyleComboBox.getItems().addAll(NeckStyle.values());
	    colorComboBox.getItems().setAll(Color.values());
	    pocketComboBox.getItems().addAll("Yes", "No");
	    sizeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateUIBasedOnSize(newSelection);
            }
	});

}
	}
	

