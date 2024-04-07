package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CreateAccountView {
	public static void display() {		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); //top, right, bottom, left

		TextField fnText = new TextField();
		TextField lnText = new TextField();
		TextField dobText = new TextField();
		PasswordField passwordText = new PasswordField();

		fnText.setPrefColumnCount(14);
		lnText.setPrefColumnCount(14);
		dobText.setPrefColumnCount(14);
		passwordText.setPrefColumnCount(14);

		Label title = new Label("Z's Clinic");
		Label fnLabel = new Label("First name: ");
		Label lnLabel = new Label("Last name: ");
		Label dobLabel = new Label("Date of Birth (MM/DD/YYYY): ");
		Label passwordLabel = new Label("Password: ");
		Label accountLabel = new Label("Already have an account?");
		 
		pane.add(fnLabel, 0, 1);
		pane.add(fnText, 1, 1);
		pane.add(lnLabel, 0, 2);
		pane.add(lnText, 1, 2);
		pane.add(dobLabel, 0, 3);
		pane.add(dobText, 1, 3);
		pane.add(passwordLabel, 0, 4);
		pane.add(passwordText, 1, 4);
		pane.add(accountLabel, 1, 20);
		
		Button create = new Button("Create Account");
		pane.add(create, 1, 7);
		
		Button goBack = new Button("Go Back");
		pane.add(goBack, 1, 22);

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(title);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(70,0,0,0)); //top, right, bottom, left
		 
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setTop(vBox);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient View");
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
		create.getStyleClass().add("button");
		
		//----END STYLING ----

		//handle the button presses
		goBack.setOnAction(e -> {
			Stage stage = (Stage) goBack.getScene().getWindow();
			stage.close();
			Login.display();
		});
		
		create.setOnAction(e -> {
			if(fnText.getText().isEmpty() || lnText.getText().isEmpty() ||
			   dobText.getText().isEmpty() || passwordText.getText().isEmpty())
			{
				showAlert("Empty fields.\nPlease fill in all the fields before saving.");
				return; 
			}
			
			if(!isValidDate(dobText.getText()))
        	{
                showAlert("Date of Birth must be in the format MM/DD/YYYY.");
                return;
        	}
			//get the year from DOB
		    String[] dobParts = dobText.getText().split("/");
		    String birthYear = dobParts[2];
		    
			String patientID = lnText.getText() + fnText.getText() + birthYear;
			String filename = patientID + "_LoginInfo.txt";
		
			//check if patientID_PatientVitals.txt file exists
		    String vitalsFilename = patientID + "_Vitals.txt";
		    File vitalsFile = new File(vitalsFilename);
		    if (!vitalsFile.exists()) {
		        showAlert("You must see a receptionist before creating an account.");
		        return;
		    }
		    
		    //check if LoginInfo.txt file already exists
		    File loginInfoFile = new File(filename);
		    if (loginInfoFile.exists()) {
		        showAlert("Account already exists for this patient.");
		        return;
		    }
			
			//write patient login info to the file
			try {
				FileWriter writer = new FileWriter(filename);
                writer.write("Username: " + patientID + "\n");
                writer.write("Password: " + passwordText.getText() + "\n");
                writer.write("First name: " + fnText.getText() + "\n");
                writer.write("Last name: " + lnText.getText() + "\n");
                writer.write("Date of birth (MM/DD/YYYY): " + dobText.getText() + "\n");
                writer.close();
                showAlert("Account created successfully.\nYour patient ID is " + patientID);
                
                Stage stage = (Stage) goBack.getScene().getWindow();
    			stage.close();
    			PatientHome.display(patientID);
                
			} catch (IOException exception) {
				showAlert("Error writing to file.");
				exception.printStackTrace();
			}
		});
	}
	
	//function to check the date format
	private static boolean isValidDate(String date) {
		return date.matches("\\d{2}/\\d{2}/\\d{4}");
	}
	
	private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
}
