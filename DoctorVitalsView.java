package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorVitalsView {
    public static void display(String patientID) {
        // Initialize text fields
        TextField weight = new TextField();
        TextField height = new TextField();
        TextField bodyTemp = new TextField();
        TextField bloodPressure = new TextField();

        //set the current vitals in this screen
        try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_Vitals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Weight: ")) {
                    weight.setText(line.substring(8).trim());
                } else if (line.startsWith("Height (feet.inches): ")) {
                    height.setText(line.substring(8).trim());
                } else if (line.startsWith("Body temperature: ")) {
                    bodyTemp.setText(line.substring(18).trim()); 
                } else if (line.startsWith("Blood pressure: ")) {
                    bloodPressure.setText(line.substring(16).trim()); 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button updateButton = new Button("Update");
        Button returnButton = new Button("Return");

        GridPane input = new GridPane();
        input.setAlignment(Pos.CENTER);
        input.setHgap(5);
        input.setVgap(5);
        input.setPadding(new Insets(25, 25, 25, 25));

        input.add(new Label("Weight: "), 0, 2);
        input.add(weight, 1, 2);
        input.add(new Label("Height: "), 0, 3);
        input.add(height, 1, 3);
        input.add(new Label("Body Temperature: "), 0, 4);
        input.add(bodyTemp, 1, 4);
        input.add(new Label("Blood Pressure: "), 0, 5);
        input.add(bloodPressure, 1, 5);
        input.add(updateButton, 1, 15);
        input.add(returnButton, 0, 15);

        Scene scene = new Scene(input, 500, 500);
        Stage window = new Stage();
        window.setTitle("Patient Vitals");
        window.setScene(scene);
        window.show();

        returnButton.setOnAction(e -> {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            DoctorPatientView.display(patientID); // relies on that page being there
        });

        updateButton.setOnAction(e -> {
            if (isAnyFieldEmpty(
                    weight.getText(),
                    height.getText(),
                    bodyTemp.getText(),
                    bloodPressure.getText()
            )) {
                showAlert("Error", "All fields must be filled out.");
                return;
            } 
            
            if(!isValidHeight(height.getText())) {
                showAlert("Error", "Height must be in the format feet.inches.");
                return;
            }
            
            //save the updated vitals
            String filename = patientID + "_Vitals.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) 
            {
            	writer.write("Username: " + patientID + "\n");
                writer.write("Weight: " + weight.getText() + "\n");
                writer.write("Height: " + height.getText() + "\n");
                writer.write("Body temperature: " + bodyTemp.getText() + "\n");
                writer.write("Blood pressure: " + bloodPressure.getText() + "\n");
                showAlert("Updated Successfully", "Vitals updated successfully.");
                    
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.close();
                DoctorPatientView.display(patientID);
            } 
            catch (IOException ex) 
            {
            	showAlert("Error", "Error updating vitals.");
                ex.printStackTrace();
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
	//function to check the height format (feet.inches)
	private static boolean isValidHeight(String height) {
		return height.matches("\\d+\\.\\d+");
	}
	 
	//method to display an alert with a title and content
	private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
