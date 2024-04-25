package views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

import java.io.IOException;

public class StatusCodeBehind() {
	
    @FXML
    private TextArea textArea;

    @FXML
    private Button backButton;
	
	/**
     * Sets the stage for this controller.
     * 
     * @param stage The primary stage of the application.
     */
    public void setStage(Stage stage) {
    }

	void initialize() {
	}
}