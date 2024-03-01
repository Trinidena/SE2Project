package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;
import java.util.Random;

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
import model.ShirtAttributes;
import model.Size;
import viewmodel.ShirtCreatorViewModel;

/**
 * Controller class for the Shirt Creator application.
 * This class handles user interactions with the GUI for creating and managing custom shirt designs.
 * It provides methods to load images, add and clear design requests, and update UI components based on user selections.
 */
public class ShirtCreatorCodeBehind {

	@FXML
	private ObservableList<ShirtAttributes> requests;
	
	ShirtAttributes shirtAttributes;
	
	Random random = new Random();
	
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
    private ListView<ShirtAttributes> listView;
    private ShirtCreatorViewModel viewModel;

	private ComboBox quantityComboBox;

	private ComboBox sleeveComboBox;

	private ComboBox collarCombobox;

	private ListView designedListView;
	
	/**
     * Handles the action of loading a shirt image from the filesystem.
     * @param event The action event triggered by the user.
     */
	@FXML
	void handleLoadButton(ActionEvent event) throws IOException {
	   FileChooser fc = new FileChooser();
	   Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	   File selectedFile = fc.showOpenDialog(stage);
	   
    Image image = new Image(selectedFile.toURI().toString());
    graphicsContext.drawImage(image, 0, 0, (canvas.getWidth()), (canvas.getHeight()));
	}
	
	/**
     * Adds a new shirt design request to the list of requests.
     * @param requestedShirt The ShirtAttributes object containing the details of the requested shirt.
     */
	public void addRequest(ShirtAttributes requestedShirt) {
	   requests.add(requestedShirt);
	}
	
	/**
     * Clears all existing shirt design requests from the list.
     */
	public void clearRequests() {
	    requests.clear();
	}
	
	/**
     * Displays a dialog with the current list of shirt design requests.
     * @param event The action event triggered by the user.
     */
	@FXML
	void onShowRequestsButtonClick(ActionEvent event) {
	    Stage stage = new Stage();
	    stage.setTitle("List of Requests");
	    VBox layout = new VBox(10);
	    listView.setItems(requests);

	    layout.getChildren().add(listView);

	    Scene scene = new Scene(layout, 300, 250);
	    stage.setScene(scene);
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.show();
	    }

	/**
     * Handles the action of submitting a request for a new shirt design.
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
	    	addRequest(new ShirtAttributes(random.nextInt(), shirtAttributes.getSize(), 
	    								   shirtAttributes.getMaterial(), shirtAttributes.getColor(), 
	    								   shirtAttributes.getBackLength(), shirtAttributes.getShoulderWidth(), 
	    								   shirtAttributes.hasPocket()));
	        Alert requestConfirmationDialog = new Alert(Alert.AlertType.INFORMATION);
	        requestConfirmationDialog.setTitle("Request Successful");
	        requestConfirmationDialog.setHeaderText(null);
	        requestConfirmationDialog.setContentText("Shirt design requested successfully.");
	        requestConfirmationDialog.showAndWait();
	    } else {

	        Alert noConfirmationDialog = new Alert(Alert.AlertType.INFORMATION);
	        noConfirmationDialog.setTitle("Request Cancelled");
	        noConfirmationDialog.setHeaderText(null);
	        noConfirmationDialog.setContentText("Shirt design request cancelled.");
	        noConfirmationDialog.showAndWait();
	    }
	}

	/**
     * Updates the UI components based on the selected shirt size.
     * @param size The Size enum representing the selected shirt size.
     */
	private void updateUIBasedOnSize(Size size) {
        shirtAttributes.createPresetForSize(size);
        materialComboBox.setValue(shirtAttributes.getMaterial());
        colorComboBox.setValue(shirtAttributes.getColor());
        backLengthComboBox.setValue(shirtAttributes.getBackLength());
        shoulderLengthComboBox.setValue(shirtAttributes.getShoulderWidth());
        pocketComboBox.setValue(shirtAttributes.hasPocket() ? "Yes" : "No");
	}

		public ShirtCreatorCodeBehind() {
			this.viewModel = new ShirtCreatorViewModel();
		}

		public void initialize() {

			this.bindToViewModel();
			this.populateComboBoxes();
			this.setupSelectionHandlerForListView();
			this.quantityComboBox.valueProperty().set(1);
		}

		@FXML
		void handleAdd(ActionEvent event) {
			Alert newAlert = new Alert(AlertType.ERROR);

			try {
				if (!this.viewModel.addShirt()) {
					newAlert.setContentText("This name already exists");
					newAlert.showAndWait();
				}
			} catch (NullPointerException nPE) {
				newAlert.setContentText(nPE.getLocalizedMessage());

				newAlert.showAndWait();
			} catch (IllegalArgumentException iAE) {
				newAlert.setContentText(iAE.getLocalizedMessage());
				newAlert.showAndWait();

			}

		}

		@FXML
		void handleDelete(ActionEvent event) {

		}

		@FXML
		void handleEdit(ActionEvent event) {

		}

		@FXML
		void handleRequest(ActionEvent event) {

		}

		@FXML
		void handleViewRequests(ActionEvent event) {

		}

		private void bindToViewModel() {
			this.designedListView.itemsProperty().bind(this.viewModel.listProperty());

			this.pocketComboBox.valueProperty().bindBidirectional(this.viewModel.pocketProperty());
			this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());

			this.quantityComboBox.valueProperty().bindBidirectional(this.viewModel.quantityProperty());

			this.sizeComboBox.valueProperty().bindBidirectional(this.viewModel.sizeProperty());

			this.sleeveComboBox.valueProperty().bindBidirectional(this.viewModel.sleeveLengthProperty());
			this.colorComboBox.valueProperty().bindBidirectional(this.viewModel.colorProperty());

			this.collarCombobox.valueProperty().bindBidirectional(this.viewModel.neckStyleProperty());
			this.materialComboBox.valueProperty().bindBidirectional(this.viewModel.materialProperty());

		}

		private void populateComboBoxes() {
			this.pocketComboBox.getItems().addAll(true, false);
			this.quantityComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
			this.sizeComboBox.getItems().addAll(Size.values());
			this.sleeveComboBox.getItems().addAll(Size.values());
			this.colorComboBox.getItems().addAll(Color.values());
			this.collarCombobox.getItems().addAll(NeckStyle.values());
			this.materialComboBox.getItems().addAll(Material.values());

		}

		private void setupSelectionHandlerForListView() {
			this.designedListView.getSelectionModel().selectedItemProperty().addListener(

					(observable, oldValue, newValue) -> {
						if (newValue != null) {
							this.nameTextField.setText(newValue.getName());

							this.pocketComboBox.valueProperty().setValue(newValue.hasPocket());
							this.quantityComboBox.valueProperty().setValue(newValue.getQuantity());
							this.sizeComboBox.valueProperty().setValue(newValue.getSize());
							this.sleeveComboBox.valueProperty().setValue(newValue.getSize());
							this.colorComboBox.valueProperty().setValue(newValue.getColor());
							this.collarCombobox.valueProperty().setValue(newValue.getNeckStyle());
							this.materialComboBox.valueProperty().setValue(newValue.getMaterial());
							this.textTextField.setText(newValue.getText());

						}
					});

}
	}
	

