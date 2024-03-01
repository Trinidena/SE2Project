package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Color;
import model.Material;
import model.NeckStyle;
import model.Size;
import model.TShirt;
import viewmodel.ShirtCreatorViewModel;

public class ShirtCreatorCodeBehind {

	@FXML
	private ComboBox<Integer> quantityComboBox;

	@FXML
	private ComboBox<Color> colorComboBox;

	@FXML
	private ComboBox<Size> sizeComboBox;

	@FXML
	private ComboBox<Boolean> pocketComboBox;

	@FXML
	private ComboBox<Size> sleeveComboBox;

	@FXML
	private ComboBox<NeckStyle> collarCombobox;

	@FXML
	private ComboBox<Material> materialComboBox;

	@FXML
	private Button deleteButton;

	@FXML
	private Button requestButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button editButton;

	@FXML
	private Button submitButton;

	@FXML
	private Button viewButton;

	@FXML
	private ListView<TShirt> designedListView;

	@FXML
	private ListView<TShirt> presetsListView;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField textTextField;

	@FXML
	private ImageView shirtImageView;

	@FXML
	private MenuBar shirtMenubar;

	private ShirtCreatorViewModel viewModel;

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
