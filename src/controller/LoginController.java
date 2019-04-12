package controller;


import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserList;

/**
 * 
 * @author Greg Melillo, Eric Kim
 * Handles the login screen
 */
public class LoginController {
	@FXML Button button_login;
	@FXML Button button_newUser;
	@FXML TextField textfield_username;
	
	
	private UserList users_list;
	private String entered_username;
	
	
	@FXML
    public void initialize() {	
		// populate user list
		users_list = new UserList();
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
			User user = users_list.getUser(entered_username);
			if(user != null) { // user with that name found
				
				// change to Album View scene
				Stage stage = (Stage) button_login.getScene().getWindow();
				Parent root;
				if(entered_username.equals("admin")) {
		            root = FXMLLoader.load(getClass().getResource("../view/admin.fxml"));
				}else{
					root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));
				}
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}else {
				// display some error text on login screen
			}
		}
	}
	
	
	public void start(Stage mainStage) {
	    // execute own shutdown procedure
	    mainStage.close();
	}
}
