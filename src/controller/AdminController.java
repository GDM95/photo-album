package controller;


import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.User;
import model.UserList;


public class AdminController {
	
	@FXML Button button_addUser;
	@FXML Button button_deleteUser;
	@FXML Button button_logout;
	
	
	@FXML ListView<User> listView;  
	private ObservableList<User> items;

	
	@FXML
    public void initialize() {
		// populate user list
		UserList.deserializeUsers();
		

		items = FXCollections.observableArrayList(UserList.getUserList());
		listView.setItems(items);
	

        listView.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUsername() == null) {
                    setText("");
                } else {
                    setText(item.getUsername());
                }
            }
        });
       

	
		// select the first item
        listView.getSelectionModel().select(0);
				
				
    }
	
	@FXML
	/**
	 * Handles clicking of buttons on admin screen
	 */
	private void handleButtonAction(ActionEvent e) throws IOException {
		Button b = (Button)e.getSource();
		
		// Login button pressed
		if (b == button_addUser) {
			
			addNewUser();
			
		} else if (b == button_deleteUser) {
			
			deleteUser();
			
		} else if (b == button_logout) {
			UserList.serializeUsers();

			Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) button_deleteUser.getScene().getWindow();
			stage.hide();
			
			
			stage.setScene(scene);
			stage.show();
		}
	}
	
	/**
	 * adds a user to the userlist and the list view
	 */
	public void addNewUser() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Add User");
		dialog.setHeaderText("Please enter the new user's name.");
		dialog.setContentText("Username:");

		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			String s = result.get().trim();
			if(UserList.getUser(s) == null) {
				UserList.addNewUser(s);
				items.add(UserList.getUser(s));
			}
		}
	}
	
	/**
	 * deletes a user from the userlist and the list view
	 */
	public void deleteUser() {
		User u = listView.getSelectionModel().getSelectedItem();
		
		if(!u.getUsername().equals("admin") && !u.getUsername().equals("stock")){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + u.getUsername() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				items.remove(u);
				UserList.removeUser(u);
			}
		}
		
	}
	

	@FXML
	/**
	 * called upon clicking the main window exit button
	 */
	public void exitApplication(ActionEvent event) {
	   Platform.exit();
	}
	
	
	public void start(Stage mainStage) {}
	
}
