package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelAwareController;
import model.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt_attribute.Color;
import model.shirt_attribute.Material;
import model.shirt_attribute.NeckStyle;
import model.shirt_attribute.Size;
import viewmodel.ShirtCreatorViewModel;

/**
 * Controls the interaction between the user and the GUI for the Shirt Creator application.
 * Handles operations such as loading shirt images, managing design requests, and updating
 * UI components based on user input.
 * 
 * @author Trinidad Dena
 */
public class ShirtCreatorCodeBehind implements ModelAwareController {

    @FXML
    private ListView<Shirt> designedListView, requestListView;
    @FXML
    private ObservableList<Shirt> requests;
    @FXML
    private ComboBox<Size> sizeComboBox, backLengthComboBox, shoulderLengthComboBox, sleeveComboBox;
    @FXML
    private ComboBox<Color> colorComboBox;
    @FXML
    private ComboBox<Material> materialComboBox;
    @FXML
    private ComboBox<Boolean> pocketComboBox;
    @FXML
    private ComboBox<NeckStyle> collarComboBox;
    @FXML
    private TextField nameTextField, textTextField;
    @FXML
    private ImageView shirtImageView;

    private ShirtCreatorViewModel viewModel;

    /**
     * Constructs an instance of the ShirtCreatorCodeBehind. Initializes the view model
     * associated with the shirt creator functionality.
     */
    public ShirtCreatorCodeBehind() {
        this.viewModel = new ShirtCreatorViewModel();
        this.requestListView = new ListView<>();
        this.designedListView = new ListView<>();
    }

    /**
     * Initializes the controller. Sets up the view model, UI components, and data bindings.
     */
    public void initialize() {
        this.populateComboBoxes();
        this.addPresets();
        this.requests = FXCollections.observableArrayList();
        this.requestListView.setItems(this.requests);
        this.setupSelectionHandlerForListView();
        this.bindToViewModel();
    }

    private void addPresets() {
    	this.viewModel.pocketProperty().set(true);
		this.viewModel.nameProperty().set("Preset 2");
		this.viewModel.shoulderProperty().set(Size.XXL);
		this.viewModel.sizeProperty().set(Size.XXXL);
		this.viewModel.sleeveLengthProperty().set(Size.XS);

		this.viewModel.colorProperty().set(Color.BLUE);
		this.viewModel.neckStyleProperty().set(NeckStyle.V_NECK);

		this.viewModel.materialProperty().set(Material.SILK);
		this.viewModel.backLengthProperty().set(Size.XL);
		this.viewModel.textProperty().set("Big Boss");

		this.viewModel.addShirtToListView();
		this.viewModel.nameProperty().set("Preset 1");
		this.viewModel.shoulderProperty().set(Size.XS);
		this.viewModel.sizeProperty().set(Size.S);
		this.viewModel.sleeveLengthProperty().set(Size.XXXL);

		this.viewModel.colorProperty().set(Color.RED);
		this.viewModel.neckStyleProperty().set(NeckStyle.SCOOP_NECK);

		this.viewModel.materialProperty().set(Material.PREMIUM_COTTON);
		this.viewModel.backLengthProperty().set(Size.M);
		this.viewModel.textProperty().set("Small Boss");

		this.viewModel.addShirtToListView();
	}

	@FXML
	void handleAdd(ActionEvent event) {
		Alert newAlert = new Alert(AlertType.ERROR);
		try {
			if (!this.viewModel.addShirtToListView()) {
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
	 * Adds a new design request to the observable list of requests.
	 * 
	 * @param requestedShirt The ShirtAttributes object detailing the requested
	 *                       shirt.
	 */
	public void addRequest(Shirt requestedShirt) {
		this.requests.add(requestedShirt);
	}

	/**
	 * Clears all design requests from the observable list.
	 */
	public void clearRequests() {
		this.requests.clear();
	}
	
    /**
     * Handles the action of requesting a new shirt design based on user input.
     *
     * @param event The action event triggered by the request button.
     */
    @FXML
    void onRequestButtonClick(ActionEvent event) {
        this.requests.add(this.viewModel.addShirt());
        this.showAlert(Alert.AlertType.INFORMATION, "Request Successful", "Shirt design requested successfully.");
    }

    /**
     * Shows a dialog with the list of current design requests.
     *
     * @param event The action event triggered by the show requests button.
     */
    @FXML
    void onShowRequestsButtonClick(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("List of Requests");
        VBox layout = new VBox(10);
        layout.getChildren().add(new ListView<>(this.requests));
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Binds UI components to the view model properties.
     */
    private void bindToViewModel() {
		this.designedListView.itemsProperty().bindBidirectional(this.viewModel.listProperty());
		this.pocketComboBox.valueProperty().bindBidirectional(this.viewModel.pocketProperty());
		this.nameTextField.textProperty().bindBidirectional(this.viewModel.nameProperty());
		this.shoulderLengthComboBox.valueProperty().bindBidirectional(this.viewModel.shoulderProperty());
		this.sizeComboBox.valueProperty().bindBidirectional(this.viewModel.sizeProperty());
		this.sleeveComboBox.valueProperty().bindBidirectional(this.viewModel.sleeveLengthProperty());
		this.colorComboBox.valueProperty().bindBidirectional(this.viewModel.colorProperty());
		this.collarComboBox.valueProperty().bindBidirectional(this.viewModel.neckStyleProperty());
		this.materialComboBox.valueProperty().bindBidirectional(this.viewModel.materialProperty());
		this.backLengthComboBox.valueProperty().bindBidirectional(this.viewModel.backLengthProperty());
		this.textTextField.textProperty().bindBidirectional(this.viewModel.textProperty());
	}

    /**
     * Populates combo boxes with their respective enum values.
     */
    private void populateComboBoxes() {
        this.pocketComboBox.setItems(FXCollections.observableArrayList(true, false));
        this.sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        this.backLengthComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        this.shoulderLengthComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        this.sleeveComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        this.colorComboBox.setItems(FXCollections.observableArrayList(Color.values()));
        this.materialComboBox.setItems(FXCollections.observableArrayList(Material.values()));
        this.collarComboBox.setItems(FXCollections.observableArrayList(NeckStyle.values()));
    }

    /**
     * Sets up the selection handler for the designed list view.
     */
    private void setupSelectionHandlerForListView() {
		this.designedListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.nameTextField.setText(newValue.getName());
						this.sizeComboBox.valueProperty().setValue(newValue.getSize());
						this.materialComboBox.valueProperty().setValue(newValue.getMaterial());
						this.colorComboBox.valueProperty().setValue(newValue.getColor());
						this.sleeveComboBox.valueProperty().setValue(newValue.getSleeveLength());
						this.shoulderLengthComboBox.valueProperty().setValue(newValue.getShoulderWidth());
						this.backLengthComboBox.valueProperty().setValue(newValue.getBackLength());
						this.collarComboBox.valueProperty().setValue(newValue.getNeckStyle());
						this.pocketComboBox.valueProperty().setValue(newValue.hasPocket());
						this.textTextField.textProperty().setValue(newValue.getShirtText());
					}
				});
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

	@Override
	public void setModel(ShirtCredentialsManager manager) {
		
	}
}
