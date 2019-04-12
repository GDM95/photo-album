package controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Album;
import model.User;
import model.UserList;

public class AdminController {
	
	@FXML Button button_addUser;
	@FXML Button button_deleteUser;
	@FXML Button button_logout;
	
	
	private UserList user_list = new UserList();
	private ObservableList<Album> obsList;
	
	
	@FXML
    public void initialize() {
		// populate user list
		user_list.deserializeUsers();
    }
	
	@FXML
	/**
	 * Handles clicking of buttons on admin screen
	 */
	private void handleButtonAction(ActionEvent e) throws IOException {
		Button b = (Button)e.getSource();
		
		// Login button pressed
		if (b == button_addUser) {
			
			/*
			Stage stage = (Stage) button_back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			*/
			
		} else if (b == button_deleteUser) {
			
		} else if (b == button_logout) {
			Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) button_deleteUser.getScene().getWindow();
			stage.hide();
			
			
			stage.setScene(scene);
			stage.show();
		}
	}
}
