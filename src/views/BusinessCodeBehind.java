package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelAwareController;
import model.shirt.Shirt;
import model.shirt.TShirt;
import model.user.User;
import server.ShirtCredentialsManager;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Business view. Handles interactions with the UI for business users.
 * 
 * @author Trinidad Dena
 */

public class BusinessCodeBehind implements ModelAwareController {

	@FXML
	private Button statusButton;
	@FXML
	private ListView<TShirt> availableRequestsListView;
	@FXML
	private ListView<TShirt> acceptedRequestsListView;

	private ObservableList<TShirt> availableRequests;
	private ObservableList<TShirt> acceptedRequests;

    private ShirtCredentialsManager manager;
    private List<User> users;

    /**
     * Initializes the controller. This method is called after the FXML fields have been injected.
     */
    @FXML
    void initialize() {
        this.manager = new ShirtCredentialsManager();
        this.users = this.manager.getUsers();
        this.availableRequests = FXCollections.observableArrayList();
        this.acceptedRequests = FXCollections.observableArrayList();
        this.getShirtsFromServer();
        this.availableRequestsListView.setItems(this.availableRequests);
        this.acceptedRequestsListView.setItems(this.acceptedRequests);

        this.availableRequestsListView.setOnMouseClicked(event -> {
            Shirt selectedShirt = this.availableRequestsListView.getSelectionModel().getSelectedItem();
            if (selectedShirt != null && event.getClickCount() == 2) {
                this.showShirtDetails(selectedShirt);
            }
        });
    }

    
    @FXML
    void handleStatusButton(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Status.fxml"));
        Parent root = loader.load();
        ModelAwareController controller = loader.getController();
        controller.setModel(this.manager);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Fetches available shirt requests from the server and populates the list.
     */
    public void getShirtsFromServer() {
        this.availableRequests.setAll(this.manager.getShirts());
    }

    /**
     * Adds a new shirt request to the list of available requests.
     * 
     * @param requestedShirt The shirt request to add.
     */
    public void addRequest(TShirt requestedShirt) {
        this.availableRequests.add(requestedShirt);
    }

    /**
     * Handles acceptance of a shirt request. Moves the shirt from available to accepted requests.
     * 
     * @param event The ActionEvent triggered by clicking the accept button.
     */
//    @FXML
//    void onRequestAccepted(ActionEvent event) {
//        TShirt selectedRequest = this.availableRequestsListView.getSelectionModel().getSelectedItem();
//        if (selectedRequest != null) {
//            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
//            confirmationDialog.setTitle("Request Confirmation");
//            confirmationDialog.setHeaderText("Accept Shirt Design");
//            confirmationDialog.setContentText("Are you sure you want to accept this shirt design?");
//            Optional<ButtonType> result = confirmationDialog.showAndWait();
//            if (result.isPresent() && result.get() == ButtonType.OK) {
//                this.availableRequests.remove(selectedRequest);
//                this.acceptedRequests.add(selectedRequest);
//                this.showAlert(Alert.AlertType.INFORMATION, "Request Accepted", "Shirt design accepted successfully.");
//            }
//        } else {
//            this.showAlert(Alert.AlertType.ERROR, "Error", "Please select a request to accept.");
//        }
//    }

    /**
     * Shows the details of a selected shirt request in a new window.
     * 
     * @param selectedShirt The shirt to display details for.
     */
    private void showShirtDetails(Shirt selectedShirt) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        Label pocketLabel = new Label("Pocket: " + selectedShirt.hasPocket());
        Label nameLabel = new Label("Name: " + selectedShirt.getName());
        Label shoulderLabel = new Label("Shoulder: " + selectedShirt.getShoulderWidth());
        Label sizeLabel = new Label("Size: " + selectedShirt.getSize().toString());
        Label backLabel = new Label("Back: " + selectedShirt.getBackLength());
        Label styleLabel = new Label("Style: " + selectedShirt.getNeckStyle());
        Label materialLabel = new Label("Material: " + selectedShirt.getMaterial());
        Label colorLabel = new Label("Color: " + selectedShirt.getColor().toString());
        Button button = new Button("Accept");

        root.getChildren().addAll(pocketLabel, nameLabel, shoulderLabel, sizeLabel, backLabel, styleLabel,
                materialLabel, colorLabel, button);

        Scene scene = new Scene(root, 300, 300);
        Stage detailStage = new Stage();
        detailStage.setTitle("Shirt Details");
        detailStage.setScene(scene);
        detailStage.initModality(Modality.APPLICATION_MODAL);
        detailStage.show();

        button.setOnAction(event -> {
            this.availableRequests.remove(selectedShirt);
            this.acceptedRequests.add((TShirt) selectedShirt);
            this.manager.updateShirt(selectedShirt.getName(), "Accepted", this.users.get(this.users.size() - 1).getCreatorName());
            detailStage.close();
        });
    }
    
    @Override
    public void setModel(model.ShirtCredentialsManager manager) {
        this.manager = (ShirtCredentialsManager) manager;
    }
}
