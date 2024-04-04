package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.shirt.Shirt;

public class BusinessCodeBehind {

    @FXML
    private ListView<Shirt> availableRequestsListView;

    @FXML
    private ListView<Shirt> acceptedRequestsListView;

    private ObservableList<Shirt> availableRequests;
    private ObservableList<Shirt> acceptedRequests;

    public void initialize() {
        availableRequests = FXCollections.observableArrayList();
        acceptedRequests = FXCollections.observableArrayList();
        availableRequestsListView.setItems(availableRequests);
        acceptedRequestsListView.setItems(acceptedRequests);
    }

    public void addRequest(Shirt requestedShirt) {
        availableRequests.add(requestedShirt);
    }

    @FXML
    void onRequestAccepted(ActionEvent event) {
        Shirt selectedRequest = availableRequestsListView.getSelectionModel().getSelectedItem();
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
}
