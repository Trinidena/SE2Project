package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import model.TShirt;
import viewmodel.ShirtCreatorViewModel;
import java.util.Optional;
import java.util.Random;

/**
 * The ShirtCreatorCodeBehind class manages the interaction between the user and
 * the GUI of the Shirt Creator application. It handles the loading of shirt
 * images, addition and removal of design requests, and updates the UI
 * components according to user input.
 */
public class ShirtCreatorCodeBehind {

	private ShirtAttributes shirtAttributes;
	private Random random = new Random();
	private GraphicsContext graphicsContext;
	private BufferedImage canvas;
	private ListView<ShirtAttributes> listView;
	private ShirtCreatorViewModel viewModel;
	private ShirtCreatorViewModel presetsViewModel;

	@FXML
	private ObservableList<ShirtAttributes> requests;
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
	private ComboBox<Boolean> pocketComboBox;
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
	private ComboBox<NeckStyle> styleComboBox;
	@FXML
	private Button submitButton;
	@FXML
	private Button viewButton;
	@FXML
	private ComboBox<Integer> quantityComboBox;
	@FXML
	private ComboBox<Size> sleeveComboBox;
	@FXML
	private ComboBox<NeckStyle> collarComboBox;
	@FXML
	private ListView<TShirt> designedListView;
	@FXML
	private ListView<TShirt> presetsListView;
	@FXML
	private TextField textTextField;

	@FXML
	private Button addButton;

	@FXML
	private MenuItem loadChoice;

	public ShirtCreatorCodeBehind() {
		this.viewModel = new ShirtCreatorViewModel();
		this.presetsViewModel = new ShirtCreatorViewModel();
	}

	/**
	 * Initializes the controller class, sets up the view model, UI components, and
	 * data bindings.
	 */
	public void initialize() {

		this.bindToViewModel();
		this.populateComboBoxes();
		this.addPresets();

		this.setupSelectionHandlerForListView();
		this.setupSelectionHandlerForPresetsListView();
		this.quantityComboBox.valueProperty().set(1);
	}

	@FXML
	void handleSave(ActionEvent event) {

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
	void handleDeleteShirt(ActionEvent event) {
		this.viewModel.deleteShirt();
	}

	@FXML
	void handleEdit(ActionEvent event) {
		this.viewModel.editShirt();
	}

	/**
	 * Handles loading a shirt image from the filesystem.
	 * 
	 * @param event The action event triggered by the user.
	 */
	@FXML
	void handleLoadButton(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			Image image = new Image(selectedFile.toURI().toString());
			graphicsContext.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
		}
	}

	/**
	 * Adds a new design request to the observable list of requests.
	 * 
	 * @param requestedShirt The ShirtAttributes object detailing the requested
	 *                       shirt.
	 */
	public void addRequest(ShirtAttributes requestedShirt) {
		requests.add(requestedShirt);
	}

	/**
	 * Clears all design requests from the observable list.
	 */
	public void clearRequests() {
		requests.clear();
	}

	/**
	 * Shows a dialog listing all current design requests.
	 * 
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
	 * Submits a request for a new shirt design after user confirmation.
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
			addRequest(new ShirtAttributes(random.nextInt(), shirtAttributes.getSize(), shirtAttributes.getMaterial(),
					shirtAttributes.getColor(), shirtAttributes.getBackLength(), shirtAttributes.getShoulderWidth(),
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
	 * Binds UI components to the view model properties. This method ensures that
	 * the UI components are updated automatically when the model changes.
	 */
	private void bindToViewModel() {
		this.designedListView.itemsProperty().bind(this.viewModel.listProperty());
		this.presetsListView.itemsProperty().bind(this.presetsViewModel.listProperty());
		this.pocketComboBox.valueProperty().bindBidirectional(this.viewModel.pocketProperty());
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.quantityComboBox.valueProperty().bindBidirectional(this.viewModel.quantityProperty());
		this.sizeComboBox.valueProperty().bindBidirectional(this.viewModel.sizeProperty());
		this.sleeveComboBox.valueProperty().bindBidirectional(this.viewModel.sleeveLengthProperty());
		this.colorComboBox.valueProperty().bindBidirectional(this.viewModel.colorProperty());
		this.collarComboBox.valueProperty().bindBidirectional(this.viewModel.neckStyleProperty());
		this.materialComboBox.valueProperty().bindBidirectional(this.viewModel.materialProperty());
		this.textTextField.textProperty().bindBidirectional(this.viewModel.textProperty());
	}

	/**
	 * Populates combo boxes with values. This method initializes combo boxes with
	 * predefined values for size, color, material, etc.
	 */
	private void populateComboBoxes() {
		this.pocketComboBox.getItems().addAll(true, false);
		this.quantityComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
		this.sizeComboBox.getItems().addAll(Size.values());
		this.sleeveComboBox.getItems().addAll(Size.values());
		this.colorComboBox.getItems().addAll(Color.values());
		this.collarComboBox.getItems().addAll(NeckStyle.values());
		this.materialComboBox.getItems().addAll(Material.values());
	}

	/**
	 * Sets up selection handlers for the list views. This method ensures that
	 * selecting an item from a list view updates the relevant UI components.
	 */
	private void setupSelectionHandlerForListView() {
		this.designedListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.nameTextField.setText(newValue.getName());
						this.pocketComboBox.valueProperty().setValue(newValue.hasPocket());
						this.quantityComboBox.valueProperty().setValue(newValue.getQuantity());
						this.sizeComboBox.valueProperty().setValue(newValue.getSize());
						this.sleeveComboBox.valueProperty().setValue(newValue.getSize());
						this.colorComboBox.valueProperty().setValue(newValue.getColor());
						this.collarComboBox.valueProperty().setValue(newValue.getNeckStyle());
						this.materialComboBox.valueProperty().setValue(newValue.getMaterial());
						System.out.println(newValue.getShirtText());
						this.textTextField.textProperty().setValue(newValue.getShirtText());
					}
				});
	}

	private void setupSelectionHandlerForPresetsListView() {
		this.presetsListView.getSelectionModel().selectedItemProperty().addListener(

				(observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.nameTextField.setText(newValue.getName());

						this.pocketComboBox.valueProperty().setValue(newValue.hasPocket());
						this.quantityComboBox.valueProperty().setValue(newValue.getQuantity());
						this.sizeComboBox.valueProperty().setValue(newValue.getSize());
						this.sleeveComboBox.valueProperty().setValue(newValue.getSize());
						this.colorComboBox.valueProperty().setValue(newValue.getColor());
						this.collarComboBox.valueProperty().setValue(newValue.getNeckStyle());
						this.materialComboBox.valueProperty().setValue(newValue.getMaterial());
						System.out.println(newValue.getShirtText());
						this.textTextField.textProperty().setValue(newValue.getShirtText());

					}
				});
	}

	private void addPresets() {
		this.presetsViewModel.pocketProperty().set(true);
		this.presetsViewModel.nameProperty().set("Preset 1");
		this.presetsViewModel.quantityProperty().set(6);
		this.presetsViewModel.colorProperty().set(Color.BLUE);
		this.presetsViewModel.sizeProperty().set(Size.XXXL);
		this.presetsViewModel.sleeveLengthProperty().set(Size.XS);
		this.presetsViewModel.neckStyleProperty().set(NeckStyle.V_NECK);
		this.presetsViewModel.materialProperty().set(Material.SILK);
		this.presetsViewModel.textProperty().set("Big Boss");

		this.presetsViewModel.addShirt();

	}
}
