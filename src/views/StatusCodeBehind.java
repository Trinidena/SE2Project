package views;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

	@Override
	public void setModel(model.ShirtCredentialsManager manager) {
		this.manager = (ShirtCredentialsManager) manager;
		this.shirts = this.manager.getShirts();
		this.setTextArea();
	}

	private void setTextArea() {
		StringBuilder builder = new StringBuilder();
		for (TShirt shirt : this.shirts) {
			System.out.println(shirt);
			String str = "Creator: " + shirt.getCreatorName() + "\nStatus: " 
					+ shirt.getStatus() + "\nBusiness: " + shirt.getBusiness() + "\n\n";
			builder.append(str);
			System.out.println(builder.toString());
		}
		this.textArea.setText(builder.toString());
	}
}