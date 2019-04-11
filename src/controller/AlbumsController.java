package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

public class AlbumsController {

	@FXML
	ListView<Album> albumsView;
	
	@FXML Button createBtn;
	@FXML Button deleteBtn;
	@FXML Button renameBtn;
	@FXML Button openBtn;
	@FXML Button searchBtn;
	@FXML Button logoutBtn;
	
	@FXML TextField albumName;
	@FXML TextField numPhotos;
	@FXML TextField dateRange;
	
	private ObservableList<Album> obsList;
	
	private User user;
	
	public void start(Stage primaryStage) {
		
	}
}
