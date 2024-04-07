package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
//import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PhysicalView {
	static public void display(String patientID) {
		TextArea notesFromPhysical = new TextArea();
		TextField medicationName = new TextField();
		TextField Reason = new TextField();
		
		Button enterButton = new Button("Enter"); 
		Button returnButton = new Button("Return");
		
		notesFromPhysical.setPrefHeight(150);
		notesFromPhysical.setPrefWidth(300);
		
		GridPane input = new GridPane();
        input.setVgap(10);
        input.setHgap(10);
        input.setAlignment(Pos.CENTER);
		input.setPadding(new Insets(25,25,25,25));
        
		Label notesFromPhysicalLabel = new Label ("Notes from the physical: ");
		Label medicationNameLabel = new Label ("Medication to send: "); 
		Label reasonLabel = new Label ("Reason: "); 
		
        input.add(notesFromPhysicalLabel, 0,1);
        input.add(notesFromPhysical, 0, 2);
        input.addRow(3, medicationNameLabel, medicationName);
        input.addRow(5, reasonLabel, Reason);
        input.add(enterButton, 1, 7);
        input.add(returnButton, 0, 7);  
        
        Scene scene = new Scene(input, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient Physical");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		input.getStyleClass().add("background");
		
		//Labels
		notesFromPhysicalLabel.getStyleClass().add("input-label"); 
		medicationNameLabel.getStyleClass().add("input-label"); 
		reasonLabel.getStyleClass().add("input-label"); 
	
		//Buttons
		enterButton.getStyleClass().add("button");
		returnButton.getStyleClass().add("small-button"); 
		
		//----END STYLING----
        
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            DoctorPatientView.display(patientID);
        });
        
        enterButton.setOnAction(e -> {
        	if(notesFromPhysical.getText().isEmpty() && medicationName.getText().isEmpty() && Reason.getText().isEmpty()
        	) {
        		showAlert("Error", "Nothing to save.");
        		return;
        	}
       
        	//check if notes from physical IS NOT empty
            if (!notesFromPhysical.getText().isEmpty()) {
                //check if medication name and reason ARE BOTH empty
                if (medicationName.getText().isEmpty() && Reason.getText().isEmpty()) {
                    //save notes from physical to file with empty medication name and reason
                    saveToFile(patientID, notesFromPhysical.getText(), "", "");
                    Stage stage = (Stage) returnButton.getScene().getWindow();
                    stage.close();
                    DoctorPatientView.display(patientID);
                }
                //dheck if medication name is NOT empty and reason IS empty
                else if (!medicationName.getText().isEmpty() && Reason.getText().isEmpty()) {
                    showAlert("Error", "Reason is required when medication name is provided.");
                }
                //check if medication name and reason ARE NOT empty
                else if (!medicationName.getText().isEmpty() && !Reason.getText().isEmpty()) {
                    //save notesFromPhysical, medicationName, and Reason to file
                    saveToFile(patientID, notesFromPhysical.getText(), medicationName.getText(), Reason.getText());
                    Stage stage = (Stage) returnButton.getScene().getWindow();
                    stage.close();
                    DoctorPatientView.display(patientID);
                }
                //if medicationName IS empty and reason is NOT empty
                else {
                    showAlert("Error", "Medication name is required when reason is provided.");
                }
            }
            //if notes IS empty
            else {
                showAlert("Error", "Notes from the physical are required.");
            }
      
        });
	}
	
	static boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field.isEmpty()) {
                return true;
            }
        }
        return false;
    }
	
	//method to display an alert with a title and content
		private static void showAlert(String title, String content) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(content);
			alert.showAndWait();
		}
	 

	//function to save data into a file
	private static void saveToFile(String patientID, String notes, String medication, String reason) {
	    try {
	        FileWriter writer = new FileWriter(patientID + "_Physical.txt");
	        writer.write("Notes from the physical: " + notes + "\n");
	        if(medication.isEmpty()) {
	            writer.write("Medication to send: n/a\n");
	        } else {
	            writer.write("Medication to send: " + medication + "\n");
	        }
	        
	        if(reason.isEmpty()) {
	            writer.write("Reason: n/a\n");
	        } else {
	            writer.write("Reason: " + reason + "\n");
	        }
	        writer.close();
	        
	        showAlert("Saved Successfully", "Information saved successfully.");
	    } catch (IOException exception) {
	        showAlert("Error", "Error writing to file.");
	        exception.printStackTrace();
	    }
	}
}
