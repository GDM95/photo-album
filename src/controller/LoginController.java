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

/**
 * 
 * @author Greg Melillo, Eric Kim
 * Handles the login screen
 */
public class LoginController {
	@FXML Button button_login;
	@FXML Button button_newUser;
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
		
		// Login button pressed
		if (b == button_login) {
			entered_username = textfield_username.getText();
			User user = searchUserList(entered_username);
			if(user != null) { // user with that name found
				
				// change to Album View scene
				Stage stage = (Stage) button_login.getScene().getWindow();
	            Parent root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}else {
				System.out.println("user not found");
				// display some error text on login screen
			}
		// New Account button pressed
		} else if (b == button_newUser) {
            Parent root = FXMLLoader.load(getClass().getResource("../view/new_user.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) button_newUser.getScene().getWindow();
			stage.hide();
			
			
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
		}catch(IOException e) {
			
		}	
	}
	
	
	public void start(Stage mainStage) {
	    // execute own shutdown procedure
	    mainStage.close();
	}
}