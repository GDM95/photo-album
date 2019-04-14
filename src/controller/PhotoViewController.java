package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.*;

public class PhotoViewController {
	
	@FXML TextField caption;
	@FXML TextField date;
	@FXML TextField tags;
	
	@FXML GridPane photogrid;
	
	private Album current_album;
	
	@FXML
	public void initialize() {
		UserList.deserializeUsers();
		
		caption.setEditable(false);
		date.setEditable(false);
		tags.setEditable(false);
		
		current_album = UserList.getCurrentAlbum();
	}
	
	@FXML
	private void goBack(ActionEvent e) {
		UserList.serializeUsers();
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/album_view.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
			stage.hide();
			
			stage.setScene(scene);
			stage.show();
		} catch(IOException error) {
			error.printStackTrace();
		}
	}
	
	@FXML
	private void addPhoto() {
		
	}
	
	@FXML
	private void deletePhoto() {
		
	}
	
	@FXML
	private void movePhoto() {
		
	}
	
	@FXML
	private void copyPhoto() {
		
	}
	
	@FXML
	private void addTag() {
		
	}
	
	@FXML
	private void removeTag() {
		
	}
	
	@FXML
	private void previousPhoto() {
		
	}
	
	@FXML
	private void nextPhoto() {
		
	}
	
	public void start(Stage primaryStage) {
		
	}
}
