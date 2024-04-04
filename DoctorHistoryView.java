package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class DoctorHistoryView {
	public static void display() {
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
		TextField pharmacyText = new TextField();

		allergyText.setPrefColumnCount(14);
		concernsText.setPrefColumnCount(14);
		issuesText.setPrefColumnCount(14);
		medicationText.setPrefColumnCount(14);
		immunizationText.setPrefColumnCount(14);
		pharmacyText.setPrefColumnCount(14);

		Label allergyLabel = new Label("Known Allergies ");
		Label concernsLabel = new Label("Health Concerns ");
		Label issuesLabel = new Label("Previous Health Issues ");
		Label medicationLabel = new Label("Previously Prescribed Medications");
		Label immunizationLabel = new Label("History of Immunization");
		Label pharmacyLabel = new Label("Pharmacy Location");
		 
		pane.add(allergyLabel, 0, 1);
		pane.add(allergyText, 1, 1);
		pane.add(concernsLabel, 0, 2);
		pane.add(concernsText, 1, 2);
		pane.add(issuesLabel, 0, 3);
		pane.add(issuesText, 1, 3);
		pane.add(medicationLabel, 0, 4);
		pane.add(medicationText, 1, 4);
		pane.add(immunizationLabel, 0, 5);
		pane.add(immunizationText, 1, 5);
		pane.add(pharmacyLabel, 0, 6);
		pane.add(pharmacyText, 1, 6);

		Button enter = new Button("Save");
		enter.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(enter, 1, 8);
		
		Button returnButton = new Button("Return");
		returnButton.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(returnButton, 0, 8);
	    
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient History");
		window.setScene(scene);
		window.show();
		
		//handle button presses
		enter.setOnAction(e -> { 
			Stage stage = (Stage) enter.getScene().getWindow();
	        stage.close();
			//DoctorHistoryView.display(); 
		});
		returnButton.setOnAction(e -> {
			Stage stage = (Stage) returnButton.getScene().getWindow();
	        stage.close();
			DoctorPatientView.display(); 
		});
	}
}
