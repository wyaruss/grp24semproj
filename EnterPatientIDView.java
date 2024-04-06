package application;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EnterPatientIDView {
	public static void display() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); //top, right, bottom, left

		TextField IDText = new TextField();
		
		IDText.setPrefColumnCount(14);

		Label title = new Label("Enter in the patient ID");
		Label IDLabel = new Label("Patient ID:");
		 
		pane.add(IDLabel, 0, 2);
		pane.add(IDText, 1, 2);

	    Button submit = new Button("Submit");
		submit.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(submit, 5, 2);
		
		Button logOut = new Button("Logout");
		logOut.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(logOut, 5, 5);
		

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(title);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(70,0,0,0)); //top, right, bottom, left
		 
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setTop(vBox);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Enter Patient ID");
		window.setScene(scene);
		window.show();
		
		//handle when submit button is presses
		submit.setOnAction(e -> {
			//check if patient id is valid
            String filename = IDText.getText() + "_Vitals.txt";
			if(!new File(filename).exists()){
				showAlert("Patient ID not found", "Patient information for ID " + IDText.getText() + " not found. Please create patient record first or try again.");
			    return;
			}

            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();
            DoctorPatientView.display(IDText.getText());
        });//end set on action
		
		//close the window to go back to mainview
	    logOut.setOnAction(e -> {
             Stage stage = (Stage) logOut.getScene().getWindow();
             stage.close();
             Login.display();
        });//end set on action
	    
	    
	}//end display
	
	//method to display an alert with a title and content
	private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
}
