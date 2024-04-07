package application;

import java.io.FileWriter;
import java.io.IOException;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class DoctorHistoryView {
	public static void display(String patientID) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25));

		TextField allergyText = new TextField();
		TextField concernsText = new TextField();
		TextField issuesText = new TextField();
		TextField medicationText = new TextField();
		TextField immunizationText = new TextField();

		allergyText.setPrefColumnCount(14);
		concernsText.setPrefColumnCount(14);
		issuesText.setPrefColumnCount(14);
		medicationText.setPrefColumnCount(14);
		immunizationText.setPrefColumnCount(14);

		Label warning = new Label("Put n/a if not applicable");
		Label allergyLabel = new Label("Known Allergies ");
		Label concernsLabel = new Label("Health Concerns ");
		Label issuesLabel = new Label("Previous Health Issues ");
		Label medicationLabel = new Label("Previously Prescribed Medications");
		Label immunizationLabel = new Label("History of Immunization");
		 
		pane.add(warning, 0, 0);
		pane.add(allergyLabel, 0, 2);
		pane.add(allergyText, 1, 2);
		pane.add(concernsLabel, 0, 3);
		pane.add(concernsText, 1, 3);
		pane.add(issuesLabel, 0, 4);
		pane.add(issuesText, 1, 4);
		pane.add(medicationLabel, 0, 5);
		pane.add(medicationText, 1, 5);
		pane.add(immunizationLabel, 0, 6);
		pane.add(immunizationText, 1, 6);

		Button enter = new Button("Save");
		pane.add(enter, 1, 9);
		
		Button returnButton = new Button("Return");
		pane.add(returnButton, 0, 9);
	    
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient History");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//GridPane
		pane.getStyleClass().add("background");
		
		
		//Labels
		allergyLabel.getStyleClass().add("input-label");
		concernsLabel.getStyleClass().add("input-label");
		issuesLabel.getStyleClass().add("input-label");
		medicationLabel.getStyleClass().add("input-label");
		immunizationLabel.getStyleClass().add("input-label");
		
		//Buttons
		enter.getStyleClass().add("button");
		returnButton.getStyleClass().add("button");
		
		//----END STYLING----
		
		//handle button presses
		enter.setOnAction(e -> { 
			if(allergyText.getText().isEmpty() || concernsText.getText().isEmpty() || issuesText.getText().isEmpty()
			  || medicationText.getText().isEmpty())
			{
				showAlert("Empty Fields", "Please fill in all the fields. Put n/a if not applicable");
				return;
			}
			
			String filename = patientID + "_History.txt"; 
			
			//write patient login info to the file
			try {
				FileWriter writer = new FileWriter(filename);
                writer.write("Allergies: " + allergyText.getText() + "\n");
                writer.write("Health concerns: " + concernsText.getText() + "\n");
                writer.write("Previos health issues: " + issuesText.getText() + "\n");
                writer.write("Previously prescribed medications: " + medicationText.getText() + "\n");
                writer.write("History of immunization: " + immunizationText.getText() + "\n");
                writer.close();
                
                showAlert("Patient history", "Patient history saved successfully");
                
                Stage stage = (Stage) enter.getScene().getWindow();
    			stage.close();
    			Login.display();
                
			} catch (IOException exception) {
				showAlert("Error", "Error writing to file.");
				exception.printStackTrace();
			}
			
			Stage stage = (Stage) enter.getScene().getWindow();
	        stage.close();
			DoctorPatientView.display(patientID);
		});
		
		returnButton.setOnAction(e -> {
			Stage stage = (Stage) returnButton.getScene().getWindow();
	        stage.close();
			DoctorPatientView.display(patientID); 
		});
	}
	
	private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
}
