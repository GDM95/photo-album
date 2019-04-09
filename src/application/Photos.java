/*
 *	[CS213] Assignment 1
 *	by Greg Melillo and Eric Kim
*/

package application;

import java.io.IOException;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Photos extends Application {
	@Override
	public void start(Stage primaryStage) 
	throws IOException {
		
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("../view/login.fxml"));
		
		
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginController loginController = loader.getController();
		loginController.start(primaryStage);

		Scene scene = new Scene(root, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	


	public static void main(String[] args) {
		launch(args);
	}

}
