package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReceptionistView {
	public static void display() {
		
		Button enterButton = new Button("Enter");
		Button returnButton = new Button("Return");
        
        //elements to be saved
		TextField fN = new TextField();
		TextField lN = new TextField();
		TextField dob = new TextField();
        TextField age = new TextField();
        TextField weight = new TextField();
        TextField height = new TextField();
        TextField bodyTemp = new TextField();
        TextField bloodPressure = new TextField();
        
        GridPane input = new GridPane();
        input.setVgap(10);
        input.setHgap(10);
        input.setAlignment(Pos.CENTER);
		input.setPadding(new Insets(25,25,25,25));
		
		Label fNLabel = new Label("First Name: "); 
		Label lNLabel = new Label("Last Name: "); 
		Label dobLabel = new Label("DOB (MM/DD/YYYY): ");
		Label ageLabel = new Label("Age: ");
		Label weightLabel = new Label("Weight: ");
		Label heightLabel = new Label("Height (feet.inches): ");
		Label bodyTempLabel = new Label("Body Temperature: ");
		Label bloodPressureLabel = new Label("Blood Pressure: ");
        
		input.addRow(0, fNLabel, fN);
		input.addRow(1, lNLabel, lN);
		input.addRow(2, dobLabel, dob);
        input.addRow(3, ageLabel, age);
        input.addRow(4, weightLabel, weight);
        input.addRow(5, heightLabel, height);
        input.addRow(6, bodyTempLabel, bodyTemp);
        input.addRow(7, bloodPressureLabel, bloodPressure);
        input.add(enterButton, 1, 5);
        input.add(returnButton, 2, 6);    
        
        StackPane stackPane = new StackPane();
        StackPane.setAlignment(enterButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(returnButton, Pos.BOTTOM_LEFT);
        
        stackPane.getChildren().addAll(input, enterButton, returnButton);
        stackPane.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(stackPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Receptionist Screen");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		stackPane.getStyleClass().add("background");
		input.getStyleClass().add("background");
		
		//Labels
		fNLabel.getStyleClass().add("input-label");
		lNLabel.getStyleClass().add("input-label");
		dobLabel.getStyleClass().add("input-label");
		ageLabel.getStyleClass().add("input-label");
		weightLabel.getStyleClass().add("input-label");
		heightLabel.getStyleClass().add("input-label");
		bodyTempLabel.getStyleClass().add("input-label");
		bloodPressureLabel.getStyleClass().add("input-label");
		
		//Buttons
		enterButton.getStyleClass().add("button");
		returnButton.getStyleClass().add("small-button");
		
		//----END STYLING----
        
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            Login.display();
        });
        
        enterButton.setOnAction(e -> {
        	if(isAnyFieldEmpty(fN.getText(),
        						lN.getText(),
        						dob.getText(),
        						age.getText(),
        						weight.getText(),
        						height.getText(),
        						bodyTemp.getText(),
        						bloodPressure.getText())) 
        	{
        		showAlert("All fields must be filled out.");
        		return;
        	}
        	
        	if(!isValidDate(dob.getText()))
        	{
                showAlert("Date of Birth must be in the format MM/DD/YYYY.");
                return;
        	}
        	
        	if(!isValidHeight(height.getText())) {
                showAlert("Height must be in the format feet.inches.");
                return;
            }
        	
        	//get the year from DOB
		    String[] dobParts = dob.getText().split("/");
		    String birthYear = dobParts[2];
		    
		    String patientID = lN.getText() + fN.getText() + birthYear;
		    String infoFilename = patientID + "_Info.txt";
		    String vitalsFilename = patientID + "_Vitals.txt";

		    // Write patient info to the info file
		    try {
		        FileWriter infoWriter = new FileWriter(infoFilename);
		        infoWriter.write("Username: " + patientID + "\n");
		        infoWriter.write("First name: " + fN.getText() + "\n");
		        infoWriter.write("Last name: " + lN.getText() + "\n");
		        infoWriter.write("Date of birth (MM/DD/YYYY): " + dob.getText() + "\n");
		        infoWriter.write("Age: " + age.getText() + "\n");
		        infoWriter.close();
		    } catch (IOException exception) {
		        showAlert("Error writing to file.");
		        exception.printStackTrace();
		    }

		    // Write patient vitals to the vitals file
		    try {
		        FileWriter vitalsWriter = new FileWriter(vitalsFilename);
		        vitalsWriter.write("Username: " + patientID + "\n");
		        vitalsWriter.write("Weight: " + weight.getText() + "\n");
		        vitalsWriter.write("Height: " + height.getText() + "\n");
		        vitalsWriter.write("Body temperature: " + bodyTemp.getText() + "\n");
		        vitalsWriter.write("Blood pressure: " + bloodPressure.getText() + "\n");
		        vitalsWriter.close();
		    } catch (IOException exception) {
		        showAlert("Error writing to file.");
		        exception.printStackTrace();
		    }

		    showAlert("Account created successfully.\nThe Patient's ID is: " + patientID);

		    Stage stage = (Stage) enterButton.getScene().getWindow();
		    stage.close();
		    ReceptionistView2.display(patientID);
		});
	}
	
	public static boolean isAnyFieldEmpty(String... fields) {
	        for (String field : fields) {
	            if (field.isEmpty()) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
	 private static void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	 
	//function to check the date format
	private static boolean isValidDate(String date) {
	     return date.matches("\\d{2}/\\d{2}/\\d{4}");
	 }
	
	//function to check the height format (feet.inches)
	private static boolean isValidHeight(String height) {
	    return height.matches("\\d+\\.\\d+");
	}
}
