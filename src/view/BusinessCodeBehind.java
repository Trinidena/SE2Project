package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelAwareController;
import model.server.ShirtCredentialsManager;
import model.shirt.Shirt;
import model.shirt.TShirt;

public class BusinessCodeBehind implements ModelAwareController {

	@FXML
	private ListView<TShirt> availableRequestsListView;
	@FXML
	private ListView<TShirt> acceptedRequestsListView;

	private ObservableList<TShirt> availableRequests;
	private ObservableList<TShirt> acceptedRequests;

	private ShirtCredentialsManager manager;

	@FXML
	void initialize() {
		manager = new ShirtCredentialsManager();
		this.availableRequests = FXCollections.observableArrayList();
		this.acceptedRequests = FXCollections.observableArrayList();
		this.getShirtsFromServer();
		this.availableRequestsListView.setItems(availableRequests);
		this.acceptedRequestsListView.setItems(acceptedRequests);

		availableRequestsListView.setOnMouseClicked(event -> {
			Shirt selectedShirt = availableRequestsListView.getSelectionModel().getSelectedItem();
			if (selectedShirt != null && event.getClickCount() == 2) { // Double-click to view details
				showShirtDetails(selectedShirt);
			}
		});
	}

	public void getShirtsFromServer() {
		availableRequests.setAll(this.manager.getShirts());
	}

	public void addRequest(TShirt requestedShirt) {
		availableRequests.add(requestedShirt);
	}

	@FXML
	void onRequestAccepted(ActionEvent event) {
		TShirt selectedRequest = availableRequestsListView.getSelectionModel().getSelectedItem();
		if (selectedRequest != null) {
			Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationDialog.setTitle("Request Confirmation");
			confirmationDialog.setHeaderText("Accept Shirt Design");
			confirmationDialog.setContentText("Are you sure you want to accept this shirt design?");
			Optional<ButtonType> result = confirmationDialog.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				availableRequests.remove(selectedRequest);
				acceptedRequests.add(selectedRequest);
				showAlert(Alert.AlertType.INFORMATION, "Request Accepted", "Shirt design accepted successfully.");
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Please select a request to accept.");
		}
	}

	private void showAlert(Alert.AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@FXML
	void onRequestClicked() {
		Shirt selectedRequest = availableRequestsListView.getSelectionModel().getSelectedItem();
		if (selectedRequest != null) {
			showAlert(Alert.AlertType.INFORMATION, "Request Details", selectedRequest.toString());
		}
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
		availableRequestsListView.setItems(availableRequests);

		layout.getChildren().add(availableRequestsListView);

		Scene scene = new Scene(layout, 300, 250);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

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
			availableRequests.remove(selectedShirt);
			acceptedRequests.add((TShirt) selectedShirt);
			detailStage.close();
		});
	}

	@Override
	public void setModel(model.ShirtCredentialsManager manager) {
		this.manager = (ShirtCredentialsManager) manager;

	}
}
