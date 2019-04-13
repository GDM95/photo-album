package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.*;

public class AlbumViewController {

	@FXML
	ListView<Album> albumsView;
	
	@FXML TextField albumName;
	@FXML TextField numPhotos;
	@FXML TextField dateRange;
	
	private ObservableList<Album> obsList;
	
	@FXML
	public void initialize() {
		UserList.deserializeUsers();
	}
	
	public void start(Stage primaryStage) {
		obsList = FXCollections.observableArrayList(UserList.getCurrentUser().getAlbumList());
		albumsView.setItems(obsList);
		
		albumsView.getSelectionModel().selectedItemProperty().addListener( (obs, oldVal, newVal) -> showAlbum() );
		
		// select the first item
        albumsView.getSelectionModel().select(0);
	}
	
	private void showAlbum() {
		Album album = albumsView.getSelectionModel().getSelectedItem();
		if(album == null) {
			albumName.clear();
			numPhotos.clear();
			dateRange.clear();
			return;
		}
		albumName.setText(album.getAlbumTitle());
		numPhotos.setText(album.getAlbumSize() +"");
		dateRange.setText("");
	}
	
	@FXML
	private void createAlbum() {
		User user = UserList.getCurrentUser();
		for(Album album : user.getAlbumList()) {
			System.out.println(album.getAlbumTitle());
		}
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create Album");
		dialog.setHeaderText("Please enter your desired album title.");
		dialog.setContentText("Album title:");

		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			String s = result.get();
			UserList.getCurrentUser().addNewAlbum(new Album(s));
			try {
				obsList.add(UserList.getCurrentUser().getAlbum(s));
			} catch(NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void deleteAlbum() {
		//delete selected album. confirmation popup
	}
	
	@FXML
	private void renameAlbum() {
		//rename selected album. confirmation popup with text input for album title
	}
	
	@FXML
	private void openAlbum() {
		//open selected album. goes 
	}
	
	@FXML
	private void search() {
		//searches all available albums for photos and copies the results to a new album
	}
	
	@FXML
	private void logout(ActionEvent e) {
		UserList.serializeUsers();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
			stage.hide();
			
			
			stage.setScene(scene);
			stage.show();
		} catch(IOException error) {
			error.printStackTrace();
		}
	}
	
 }
