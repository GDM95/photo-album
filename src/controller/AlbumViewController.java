package controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
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
		
		obsList = FXCollections.observableArrayList(UserList.getCurrentUser().getAlbumList());
		albumsView.setItems(obsList);
		
		albumsView.setCellFactory(param -> new ListCell<Album>() {
            @Override
            protected void updateItem(Album item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getAlbumTitle() == null) {
                    setText("");
                } else {
                    setText(item.getAlbumTitle());
                }
            }
        });
		

        albumsView.getSelectionModel().select(0);
        sortList();
		albumName.setEditable(false);
		numPhotos.setEditable(false);
		dateRange.setEditable(false);
		
		albumsView.getSelectionModel().selectedItemProperty().addListener( (obs, oldVal, newVal) -> showAlbumDetails() );
	}
	
	private void showAlbumDetails() {
		Album album = albumsView.getSelectionModel().getSelectedItem();
		
		if(album == null) return;
		albumName.setText(album.getAlbumTitle());
		numPhotos.setText(album.getAlbumSize() +"");
		dateRange.setText(album.getDateRange());
	}
	
	@FXML
	private void createAlbum() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Create Album");
		dialog.setHeaderText("Please enter your desired album title.");
		dialog.setContentText("Album title:");

		try {
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()) {
				Album temp = new Album(result.get());
				if(temp == null || UserList.getCurrentUser().albumTitleExists(temp.getAlbumTitle())) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Create Album Failed");
					alert.setHeaderText(null);
					alert.setContentText("An album with that title already exists!");
					alert.showAndWait();
					return;
				}
				obsList.add(temp);
				UserList.getCurrentUser().addNewAlbum(temp);
				UserList.serializeUsers();
				sortList();
				albumsView.getSelectionModel().select(temp);
			}
		} catch(NullPointerException e) {
			//probably a TERRIBLE idea but let's just pretend this doesn't happen for now
		}
	}
	
	@FXML
	private void deleteAlbum() {
		Album temp = albumsView.getSelectionModel().getSelectedItem();
		if(temp == null) return;
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + temp.getAlbumTitle() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			obsList.remove(temp);
			UserList.getCurrentUser().removeAlbum(temp);
			UserList.serializeUsers();
		}
		if(obsList.size() == 0) {
			albumName.clear();
			numPhotos.clear();
			dateRange.clear();
		}
	}
	
	@FXML
	private void renameAlbum() {
		Album temp = albumsView.getSelectionModel().getSelectedItem();
		if(temp == null) return;
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Rename Album");
		dialog.setHeaderText("Please enter the new album title.");
		dialog.setContentText("New album title:");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			String s = result.get();
			if(s == null || s == "" || UserList.getCurrentUser().albumTitleExists(s)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Rename Failed");
				alert.setHeaderText(null);
				alert.setContentText("An album with that title already exists!");
				alert.showAndWait();
				return;
			}
			temp.setAlbumTitle(s);
			int index = albumsView.getSelectionModel().getSelectedIndex();
			obsList.set(index, temp);
			sortList();
			albumsView.getSelectionModel().select(temp);
			showAlbumDetails();
		}
	}
	
	@FXML
	private void openAlbum(ActionEvent e) {
		//start passing user and user list data to AlbumViewController
		
		Album temp = albumsView.getSelectionModel().getSelectedItem();
		if(temp == null) return;
		UserList.serializeUsers();
		UserList.setCurrentAlbum(temp);
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/photo_view.fxml"));
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
	/** handles clicking on search button in album view. Redirects to search screen
	 */
	private void search(ActionEvent e) {
		UserList.serializeUsers();

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/search.fxml"));
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
	
	private void sortList() {
		obsList.sort(Comparator.comparing(Album::getAlbumTitle));
	}

	public void start(Stage primaryStage) {		
		
	}
	
 }
