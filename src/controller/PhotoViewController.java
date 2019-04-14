package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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
		
		for(int i = 0; i < UserList.getCurrentAlbum().getAlbumSize(); i++) {
			ImageView imageView = new ImageView(UserList.getCurrentAlbum().getPhoto(i).getImage());
			imageView.setFitHeight(122.0);
			imageView.setFitWidth(89.0);
			photogrid.add(imageView, i % 2, i / 3);
		}
		
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
	private void addPhoto(ActionEvent e) throws IOException {
		FileChooser fChooser = new FileChooser();
		
		FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);
        fChooser.setTitle("Add Photo");
        
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		File file = fChooser.showOpenDialog(app_stage);
		
		if(file == null) return;
		
		BufferedImage buffImage = ImageIO.read(file);
		Image newImage = SwingFXUtils.toFXImage(buffImage, null);
		
		PhotoData pData = new PhotoData();
		pData.setPixelsFromImage(newImage);
		//check if photo exists in the album
		for(Photo photo : UserList.getCurrentAlbum().getPhotoList()) {
			if(pData.equals(photo.getPhotoData())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Add Photo Failed");
				alert.setHeaderText(null);
				alert.setContentText("That photo already exists in the album!");
				alert.showAndWait();
				return;
			}
		}
		
		//check if photo exists in other albums, add that photo object if one exists
		for(Album album : UserList.getCurrentUser().getAlbumList()) {
			for(Photo photo : album.getPhotoList()) {
				if(pData.equals(photo.getPhotoData())) {
					UserList.getCurrentAlbum().addPhoto(photo);
					UserList.serializeUsers();
					ImageView imgView = new ImageView(photo.getImage());
					photogrid.add(imgView, 0, 0);
					return;
				}
			}
		}
		
		//add new photo
		Photo temp = new Photo(pData.getImageFromPixels());
		UserList.getCurrentAlbum().addPhoto(temp);
		UserList.serializeUsers();
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
