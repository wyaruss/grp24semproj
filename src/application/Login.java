package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	public static void display() {		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); //top, right, bottom, left

		TextField usernameText = new TextField();
		PasswordField passwordText = new PasswordField();

		usernameText.setPrefColumnCount(14);
		passwordText.setPrefColumnCount(14);

		Label title = new Label("Z's Clinic");
		Label usernameLabel = new Label("Username: ");
		Label passwordLabel = new Label("Password: ");
		Label accountLabel = new Label("Don't already have an account?");
		 
		pane.add(usernameLabel, 0, 1);
		pane.add(usernameText, 1, 1);
		pane.add(passwordLabel, 0, 2);
		pane.add(passwordText, 1, 2);
		pane.add(accountLabel, 1, 20);

	    Button login = new Button("Login");
		pane.add(login, 1, 5);
		
		Button create = new Button("Create Account");
		pane.add(create, 1, 22);

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(title);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(70,0,0,0)); //top, right, bottom, left
		 
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setTop(vBox);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Login");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Vbox/GridPane
		vBox.getStyleClass().add("background"); 
		pane.getStyleClass().add("background"); 
		
		
		//Labels
		title.getStyleClass().add("title");
		
		//Buttons
		login.getStyleClass().add("button");
		
		//----END STYLING----
		
		//handle when login account button is pressed
		login.setOnAction(e -> {
			String username = usernameText.getText();
            String password = passwordText.getText();

            if(username.equals("Doctor"))
            {
            	if(validateLogin(username, password)) 
            	{
            		Stage stage = (Stage) login.getScene().getWindow();
            		stage.close();
            		EnterPatientIDView.display();
            	}
            }
            else if(username.equals("Receptionist"))
            {
            	if(validateLogin(username, password)) 
                {
                	Stage stage = (Stage) login.getScene().getWindow();
                	stage.close();
                	ReceptionistView.display();
                }
            }
            
            else if(validateLogin(username, password)) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                PatientHome.display(username);
            }
			
		});
		
		//handle when create account button is pressed
		create.setOnAction(e -> {
			Stage stage = (Stage) create.getScene().getWindow();
			stage.close();
			CreateAccountView.display(); 
		});
	}
	
	//function to validate the entered username and password
    private static boolean validateLogin(String username, String password) {
    	String filename = username + "_LoginInfo.txt";
        File file = new File(filename);

        if (!file.exists()) {
            showAlert("Wrong username", "Username does not exist. Try again or create an account");
            return false;
        }
        //file exists, get the password - username should match based off filename already
        String storedPassword = "";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.startsWith("Password:"))
                    storedPassword = line.substring(9).trim(); //get password
            }
        } catch (FileNotFoundException exception) {
            showAlert("Error reading file.", "Could not read file");
            return false;
        }

        if (storedPassword.equals(password))
            return true;
        else {
            showAlert("Incorrect password.", "The password is incorrect");
            return false;
        }
    }
    
    private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
}
