package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML Button button_login;
	@FXML Button button_newAccount;
	@FXML TextField textfield_username;
	@FXML TextField textfield_password;
	
	
	public void start(Stage mainStage) {
	       // execute own shutdown procedure
	       mainStage.close();
	}
}
