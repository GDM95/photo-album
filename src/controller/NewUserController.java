package controller;

import java.io.IOException;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

/**
 * Controller for the 'add new user' screen
 * @author Greg Melillo, Eric Kim
 *
 */
public class NewUserController {
	
	@FXML Button button_register;
	@FXML Button button_back;
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
		
		// Register button pressed
		if (b == button_register) {
			entered_username = textfield_username.getText();
			// the entered username is available

			if(UserList.getUser(entered_username) == null) {
				// Serialize the User data and proceed to the album view screen
				if(UserList.addNewUser(entered_username)) {
					System.out.println("added user");
				}
				System.out.println(Arrays.toString(UserList.getUserList().toArray()));
				//start passing user and user list data to AlbumViewController
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/album_view.fxml"));
	            UserList.setCurrentUser(UserList.getUser(entered_username));
	            UserList.serializeUsers();
	            //end data passing

				Stage stage = (Stage) button_back.getScene().getWindow();
	            
				stage.setScene(new Scene(loader.load()));
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
