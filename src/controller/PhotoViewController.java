package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

public class PhotoViewController {
	
	@FXML TextField caption;
	@FXML TextField date;
	@FXML TextField tags;
	@FXML ListView<Photo> photoListView;
	
	@FXML ImageView display;
	
	private ObservableList<Photo> obsList;
	
	@FXML
	public void initialize() {
		UserList.deserializeUsers();


		obsList = FXCollections.observableArrayList(UserList.getCurrentAlbum().getPhotoList());
		photoListView.setItems(obsList);
		photoListView.getSelectionModel().select(0);
		
		photoListView.setCellFactory(param -> new ListCell<Photo>() {
			private ImageView imgView = new ImageView();
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	imgView.setImage(item.getImage());
                	imgView.setFitHeight(60);
        			imgView.setFitWidth(60);
        			imgView.setPreserveRatio(true);
                    setText(null);
                    setGraphic(imgView);
                }
            }
        });
		
		caption.setEditable(false);
		date.setEditable(false);
		tags.setEditable(false);
		photoListView.getSelectionModel().selectedItemProperty().addListener( (obs, oldVal, newVal) -> showPhotoDetails() );
	}
	
	private void showPhotoDetails() {
		Photo photo = photoListView.getSelectionModel().getSelectedItem();
		if(photo == null) return;
		display.setImage(photo.getImage());
		caption.setText(photo.getCaption());
		date.setText(photo.getDate());
		tags.setText(photo.getTagList().toString());
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
					obsList.add(photo);
					UserList.getCurrentAlbum().addPhoto(photo);
					UserList.serializeUsers();	
					photoListView.getSelectionModel().select(photo);
					return;
				}
			}
		}
		
		//add new photo
		Photo temp = new Photo(pData.getImageFromPixels());
		obsList.add(temp);
		UserList.getCurrentAlbum().addPhoto(temp);
		photoListView.getSelectionModel().select(temp);
		UserList.serializeUsers();
	}
	
	@FXML
	private void deletePhoto() {
		Photo temp = photoListView.getSelectionModel().getSelectedItem();
		if(temp == null) return;
		Alert alert = new Alert(AlertType.CONFIRMATION, "Delete this photo?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			obsList.remove(temp);
			UserList.getCurrentAlbum().removePhoto(temp);
			UserList.serializeUsers();
		}
		if(obsList.size() == 0) {
			
		}
	}
	
	@FXML
	private void movePhoto() {
		
	}
	
	@FXML
	private void copyPhoto() {
		
	}
	
	@FXML
	private void addTag() {
	    Photo currentPhoto = photoListView.getSelectionModel().getSelectedItem();

		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Add a Tag");
		dialog.setHeaderText("Add a Tag");

		ButtonType confirmButtonType = new ButtonType("Confirm", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField type = new TextField();
		type.setPromptText("e.g. \"location\"");
		TextField name = new TextField();
		name.setPromptText("e.g. \"Paris\"");
		grid.add(new Label("Type: "), 0, 0);
		grid.add(type, 1, 0);
		grid.add(new Label("Name: "), 0, 1);
		grid.add(name, 1, 1);
		dialog.getDialogPane().setContent(grid);
		
		Platform.runLater(() -> type.requestFocus());
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == confirmButtonType) {
		        return new Pair<>(type.getText(), name.getText());
		    }
		    return null;
		});

		// get the key value pair from the textfields
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(tag -> {
			currentPhoto.addTag(tag.getKey(), tag.getValue());
		});
		// update the tags textfield
		tags.setText(currentPhoto.getTagList().toString());
		UserList.serializeUsers();
	}
	
	@FXML
	private void removeTag() {
		
	}
	
	@FXML
	private void previousPhoto() {
		int currentIndex = photoListView.getSelectionModel().getSelectedIndex();
		if(currentIndex == 0) {
			photoListView.getSelectionModel().clearAndSelect(obsList.size() - 1);
		} else {
			photoListView.getSelectionModel().clearAndSelect(currentIndex - 1);
		}
	}
	
	@FXML
	private void nextPhoto() {
		int currentIndex = photoListView.getSelectionModel().getSelectedIndex();
		if(currentIndex == obsList.size() - 1) {
			photoListView.getSelectionModel().clearAndSelect(0);
		} else {
			photoListView.getSelectionModel().clearAndSelect(currentIndex + 1);
		}
	}
	
	public void start(Stage primaryStage) {
		
	}
}
