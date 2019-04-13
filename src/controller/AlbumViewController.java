package controller;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
		List<Album> albums = UserList.getCurrentUser().getAlbumList();
		obsList = FXCollections.observableArrayList(albums);
		albumsView.setItems(obsList);
		
		albumsView.getSelectionModel().selectedItemProperty().addListener( (obs, oldVal, newVal) -> showAlbum() );
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
			System.out.println(UserList.getCurrentUser());
			Album album = new Album(result.get());
			UserList.getCurrentUser().addNewAlbum(album);
			obsList.add(album);
			
			UserList.serializeUsers();
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
	private void logout() {
		
	}
	
 }
