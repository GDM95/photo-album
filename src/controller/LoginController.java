package controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

/**
 * 
 * @author Greg Melillo, Eric Kim
 * Handles the login screen
 */
public class LoginController {
	@FXML Button button_login;
	@FXML Button button_newUser;
	@FXML TextField textfield_username;
	
	
	private String entered_username;
	
	@FXML
	public void initialize() {
		UserList.deserializeUsers();
	}
	
	@FXML
	/**
	 * Handles clicking of buttons on login screen
	 */
	private void handleButtonAction(ActionEvent e) throws IOException {
		Button b = (Button)e.getSource();
		
		// Login button pressed
		if (b == button_login) {
			entered_username = textfield_username.getText();
			User user = UserList.getUser(entered_username);
			if(user != null) { // user with that name found
				
				// change to Album View scene
				
	            //start passing user and user list data to AlbumViewController
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/album_view.fxml"));
	            Stage stage = (Stage) button_login.getScene().getWindow();
	            UserList.setCurrentUser(user);
	            UserList.serializeUsers();
	            //end data passing

				stage.setScene(new Scene(loader.load()));
				stage.show();
			}else {
				System.out.println("user not found");
				// display some error text on login screen
			}
		// New Account button pressed
		} else if (b == button_newUser) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/new_user.fxml"));
            Stage stage = (Stage) button_newUser.getScene().getWindow();
			stage.setScene(new Scene(loader.load()));
			stage.show();
		}
	}
	
	
	public void start(Stage mainStage) {
	    // execute own shutdown procedure
	    mainStage.close();
	}
}
