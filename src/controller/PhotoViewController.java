package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.*;

public class PhotoViewController {
	
	@FXML TextField caption;
	@FXML TextField date;
	@FXML TextField tags;
	
	@FXML GridPane photogrid;
	
	@FXML
	public void initialize() {
		UserList.deserializeUsers();
		
		caption.setEditable(false);
		date.setEditable(false);
		tags.setEditable(false);
	}
	
	public void start(Stage primaryStage) {
		
	}
}
