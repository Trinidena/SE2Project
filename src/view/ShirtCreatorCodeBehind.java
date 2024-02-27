package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Material;
import model.NeckStyle;
import model.Size;
import model.TShirt;
import viewmodel.ShirtCreatorViewModel;

public class ShirtCreatorCodeBehind {

	@FXML
	private ComboBox<Integer> quantityComboBox;

	@FXML
	private ComboBox<String> colorComboBox;

	@FXML
	private ComboBox<String> designComboBox;

	@FXML
	private ComboBox<Size> sizeComboBox;

	@FXML
	private ComboBox<NeckStyle> styleCombobox;

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
	private ImageView shirtImageView;

	@FXML
	private MenuBar shirtMenubar;

	private ShirtCreatorViewModel viewModel;

	public ShirtCreatorCodeBehind() {
		this.viewModel = new ShirtCreatorViewModel();
	}

	public void initialize() {

		this.bindToViewModel();
	}

	@FXML
	void handleAdd(ActionEvent event) {

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

		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());

		this.materialComboBox.valueProperty().bindBidirectional(this.viewModel.materialProperty());
		this.colorComboBox.valueProperty().bindBidirectional(this.viewModel.colorProperty());
		this.quantityComboBox.valueProperty().bindBidirectional(this.viewModel.quantityProperty());
		this.designComboBox.valueProperty().bindBidirectional(this.viewModel.designProperty());
		this.styleCombobox.valueProperty().bindBidirectional(this.viewModel.neckStyleProperty());
		this.sizeComboBox.valueProperty().bindBidirectional(this.viewModel.sizeProperty());

	}

}
