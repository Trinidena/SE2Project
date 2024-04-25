package views;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.ModelAwareController;
import model.shirt.TShirt;
import server.ShirtCredentialsManager;

public class StatusCodeBehind implements ModelAwareController {
	
    @FXML
    private TextArea textArea;

    @FXML
    private Button backButton;

	private ShirtCredentialsManager manager;
	
	private List<TShirt> shirts;
	
	/**
     * Sets the stage for this controller.
     * 
     * @param stage The primary stage of the application.
     */
    public void setStage(Stage stage) {
    	
    }

	@Override
	public void setModel(model.ShirtCredentialsManager manager) {
		this.manager = (ShirtCredentialsManager) manager;
		this.shirts = this.manager.getShirts();
		StringBuilder builder = new StringBuilder();
		for (TShirt shirt : shirts) {
			String str = shirt.getCreatorName() + shirt.getStatus() + shirt.getBusiness() + "\n";
			builder.append(str);
		}
		this.textArea.setText(builder.toString());
		
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPassword(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRole(String value) {
		// TODO Auto-generated method stub
		
	}
}