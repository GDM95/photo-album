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

public class NewUserController {
	
	@FXML Button button_register;
	@FXML Button button_back;
	@FXML TextField textfield_username;
	
	
	private ArrayList<User> users_list = new ArrayList();
	private String entered_username;
	
	
	@FXML
    public void initialize() {
		// populate user list
		deserializeUsers();
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
			if(searchUserList(entered_username) == null) {
				
				// Serialize the User data and proceed to the album view screen
				users_list.add(new User(entered_username));
				System.out.println(Arrays.toString(users_list.toArray()));
	
				serializeUsers();
				Stage stage = (Stage) button_back.getScene().getWindow();
	            Parent root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));

				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				
				
			}else {	
				// found user with that name already
				System.out.println("username: " + entered_username + ", is already taken");
				// display some error message on screen	
			}
			
		// Back button pressed
		} else if (b == button_back) {
			
			Stage stage = (Stage) button_back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	
	
	/**
	 * Searches the username list for the given key
	 * @param key the username to be looked up
	 * @return
	 */
	public User searchUserList(String key) { 
		 for (User user : users_list) {
		     if (user.getUsername().equals(key)) {
		        return user;
		     }
		  }
		 return null; 
	}

	
	/**
	 * Grabs the serialized user data from file
	 */
	private void deserializeUsers() {
		try {
			FileInputStream fileIn = new FileInputStream("data/users");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users_list = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException | ClassNotFoundException e) {
			
		}finally {
			System.out.println("Deserializing Users...");
			System.out.println(Arrays.toString(users_list.toArray()));
		}
	}
	
	
	/**
	 * Writes user data to file
	 */
	private void serializeUsers() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data/users");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users_list);
			out.close();
			fileOut.close();
			System.out.println("Serializing Users...");
		}catch(IOException e) {	}	
	}

	
	
	
	public void start(Stage mainStage) {
	    // execute own shutdown procedure
	    mainStage.close();
	}
}
