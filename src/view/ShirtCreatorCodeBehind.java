package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.shirt.Shirt;
import model.shirt.TShirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import viewmodel.ShirtCreatorViewModel;

/**
 * The ShirtCreatorCodeBehind class manages the interaction between the user and the GUI of the Shirt Creator application.
 * It handles the loading of shirt images, addition and removal of design requests, and updates the UI components according to user input.
 */
public class ShirtCreatorCodeBehind {
    
    @FXML private ListView<Shirt> designedListView, presetsListView, requestListView;
    @FXML private ObservableList<Shirt> requests;
    @FXML private ComboBox<Size> sizeComboBox, backLengthComboBox, shoulderLengthComboBox, sleeveComboBox;
    @FXML private ComboBox<Color> colorComboBox;
    @FXML private ComboBox<Material> materialComboBox;
    @FXML private ComboBox<NeckStyle> styleCombobox;
    @FXML private ComboBox<Boolean> pocketComboBox;
    @FXML private ComboBox<Integer> quantityComboBox;
    @FXML private Button saveButton, deleteButton, editButton, requestButton, submitButton, viewButton;
    @FXML private TextArea designedTextArea, presetsTextArea;
    @FXML private TextField nameTextField, textTextField;
    @FXML private ImageView shirtImageView;
    @FXML private MenuBar shirtMenubar;
    
    private ShirtCreatorViewModel viewModel;

    public ShirtCreatorCodeBehind() {
        this.viewModel = new ShirtCreatorViewModel();
    }

    /**
     * Initializes the controller class, sets up the view model, UI components, and data bindings.
     */
    public void initialize() {
        populateComboBoxes();
        this.designedListView = new ListView<>();
        this.requestListView = new ListView<>();
        this.requests = FXCollections.observableArrayList();
        requestListView = new ListView<>(requests);
        setupSelectionHandlerForListView();
        bindToViewModel();
    }

    /**
     * Handles the event for loading a shirt image from the file system.
     * This method opens a file chooser dialog for the user to select an image file. Once selected,
     * the image is loaded and displayed in the application.
     *
     * @param event The action event triggered by the user.
     * @throws IOException If an I/O error occurs while loading the image file.
     */
    @FXML
    void handleLoadButton(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            shirtImageView.setImage(image);
        }
    }

    /**
     * Adds a new design request based on user input. This method collects the data from the form,
     * creates a new Shirt object, and adds it to the observable list of requests.
     *
     * @param event The action event triggered by the user.
     */
    @FXML
    void onRequestButtonClick(ActionEvent event) {
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Request Confirmation");
        confirmationDialog.setHeaderText("Request Shirt Design");
        confirmationDialog.setContentText("Are you sure you want to request this shirt design?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            requests.add(viewModel.addShirt());
            showAlert(AlertType.INFORMATION, "Request Successful", "Shirt design requested successfully.");
        } else {
            showAlert(AlertType.INFORMATION, "Request Cancelled", "Shirt design request cancelled.");
        }
    }

    /**
     * Shows a dialog listing all current design requests.
     * @param event The action event triggered by the user.
     */
    @FXML
    void onShowRequestsButtonClick(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("List of Requests");
        VBox layout = new VBox(10);
        requestListView.setItems(requests);

        layout.getChildren().add(requestListView);

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    /**
     * Displays an alert dialog to the user.
     *
     * @param type    The type of alert.
     * @param title   The title of the dialog.
     * @param content The content text to be displayed.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void bindToViewModel() {
        designedListView.itemsProperty().bindBidirectional(viewModel.listProperty());
        pocketComboBox.valueProperty().bindBidirectional(viewModel.pocketProperty());
        nameTextField.textProperty().bindBidirectional(viewModel.nameProperty());
        shoulderLengthComboBox.valueProperty().bindBidirectional(viewModel.backLengthProperty());
        sizeComboBox.valueProperty().bindBidirectional(viewModel.sizeProperty());
        backLengthComboBox.valueProperty().bindBidirectional(viewModel.backLengthProperty());
        colorComboBox.valueProperty().bindBidirectional(viewModel.colorProperty());
        styleCombobox.valueProperty().bindBidirectional(viewModel.neckStyleProperty());
        materialComboBox.valueProperty().bindBidirectional(viewModel.materialProperty());
    }

    private void populateComboBoxes() {
        pocketComboBox.getItems().addAll(true, false);
        sizeComboBox.getItems().addAll(Size.values());
        backLengthComboBox.getItems().addAll(Size.values());
        shoulderLengthComboBox.getItems().addAll(Size.values());
        colorComboBox.getItems().addAll(Color.values());
        styleCombobox.getItems().addAll(NeckStyle.values());
        materialComboBox.getItems().addAll(Material.values());
    }

    private void setupSelectionHandlerForListView() {
        designedListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateFormFields(newValue);
            }
        });
        
        requestListView.setOnMouseClicked(event -> {
            Shirt selectedShirt = requestListView.getSelectionModel().getSelectedItem();
            if (selectedShirt != null && event.getClickCount() == 2) { // Double-click to view details
                showShirtDetails(selectedShirt);
            }
        });
    }

    private void updateFormFields(Shirt newValue) {
    	pocketComboBox.valueProperty().setValue(newValue.hasPocket());
        nameTextField.setText(newValue.getName());
        shoulderLengthComboBox.valueProperty().setValue(newValue.getSize());
        sizeComboBox.valueProperty().setValue(newValue.getSize());
        backLengthComboBox.valueProperty().setValue(newValue.getSize());
        colorComboBox.valueProperty().setValue(newValue.getColor());
        styleCombobox.valueProperty().setValue(newValue.getNeckStyle());
        materialComboBox.valueProperty().setValue(newValue.getMaterial());
    }
    
    private void showShirtDetails(Shirt shirt) {
        // Create the root pane and set its properties
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Create and add UI components to display the shirt details
        Label pocketLabel = new Label("Pocket: " + shirt.hasPocket());
        Label nameLabel = new Label("Name: " + shirt.getName());
        Label shoulderLabel = new Label("Shoulder: " + shirt.getShoulderWidth());
        Label sizeLabel = new Label("Size: " + shirt.getSize().toString()); // Assuming getSize() returns an enum or similar
        Label backLabel = new Label("Back: " + shirt.getBackLength());
        Label styleLabel = new Label("Style: " + shirt.getNeckStyle());
        Label materialLabel = new Label("Material: " + shirt.getMaterial());
        Label colorLabel = new Label("Color: " + shirt.getColor().toString()); // Assuming getColor() returns an enum or similar
        // Add more labels or UI components as needed

        root.getChildren().addAll(pocketLabel, nameLabel, shoulderLabel, sizeLabel, backLabel, styleLabel, materialLabel, colorLabel);

        // Set the scene and stage
        Scene scene = new Scene(root, 300, 300); // Adjust size as needed
        Stage detailStage = new Stage();
        detailStage.setTitle("Shirt Details");
        detailStage.setScene(scene);

        // Set modality if you want to block input to other windows
        detailStage.initModality(Modality.APPLICATION_MODAL);

        // Show the new window
        detailStage.show();
    }

}
