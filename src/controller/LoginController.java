package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML Button button_login;
	@FXML Button button_newAccount;
	@FXML TextField textfield_username;
	
	private String entered_username;
	
	
	
	@FXML
	private void handleButtonAction(ActionEvent e) {
		Button b = (Button)e.getSource();
		if (b == button_login) {
			entered_username = textfield_username.getText();
			// add code
		} else if (b == button_newAccount) {
			// add code
		}
	}
	
	
	
	public void start(Stage mainStage) {
	       // execute own shutdown procedure
	       mainStage.close();
	}
}
