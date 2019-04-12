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
 *
 */
public class NewUserController {
	
	@FXML Button button_register;
	@FXML Button button_back;
	@FXML TextField textfield_username;
	
	
	private UserList user_list = new UserList();
	private String entered_username;
	
	
	@FXML
    public void initialize() {

    }
	
	@FXML
	/**
	 * Handles clicking of buttons on login screen
	 */
	private void handleButtonAction(ActionEvent e) throws IOException {
		Button b = (Button)e.getSource();
		
		// Register button pressed
		if (b == button_register) {
			entered_username = textfield_username.getText();
			// the entered username is available
			if(user_list.getUser(entered_username) == null) {
				
				// Serialize the User data and proceed to the album view screen
				if(!user_list.addNewUser(entered_username)) System.out.println("invalid username");
	
				user_list.serializeUsers();
				Stage stage = (Stage) button_back.getScene().getWindow();
	            Parent root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			
		// Back button pressed
		} else if (b == button_back) {
			
			Stage stage = (Stage) button_back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		}
	}
	
	
	public void start(Stage mainStage) {
	    // execute own shutdown procedure
	    mainStage.close();
	}
}
