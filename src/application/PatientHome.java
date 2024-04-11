package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientHome {
	public static void display(String patientID) {
		//get the patients first name and last name to display it on the screen
		String firstName = "";
		String lastName = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_Info.txt"))) 
		{
			String line;
		    while ((line = reader.readLine()) != null) 
		    {
		    	if(line.startsWith("First name: "))
		    		firstName = line.substring(12);
		    	if(line.startsWith("Last name: "))
		    		lastName = line.substring(11);
		    }
		} catch (IOException e) {
			e.printStackTrace();
			}		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25));
		
		Label title = new Label("Patient: " + firstName + " " + lastName);
	    Button visitButton = new Button("Recent Visit");
	    Button infoButton = new Button("Contact Information");
        Button logOut = new Button("Log Out");
        Button sendMessage = new Button("Send Message");
	       
        VBox.setMargin(title, new Insets(20, 0, 80, 0));
	    VBox layout = new VBox(10);
	    layout.getChildren().addAll(title, visitButton, infoButton, sendMessage);
	    layout.setAlignment(Pos.CENTER);
	        
        VBox logout = new VBox(10);
        logout.getChildren().addAll(logOut);
	    logout.setAlignment(Pos.BOTTOM_CENTER);
	    
	    BorderPane borderPane = new BorderPane();
	    borderPane.setPadding(new Insets(25,25,25,25));
	    borderPane.setCenter(layout);
	    borderPane.setBottom(logout);

	    Scene scene = new Scene(borderPane, 500, 500);
	    Stage window = new Stage();
		window.setTitle("Patient Home");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = "C:\\Users\\sachi\\eclipse-workspace\\groupsemproject\\bin\\styles.css"; 
		scene.getStylesheets().add(css);
		
		//Panes
		logout.getStyleClass().add("background");
		pane.getStyleClass().add("background");
		borderPane.getStyleClass().add("background");
		
		//Labels
		title.getStyleClass().add("title");
		
		//Buttons
		visitButton.getStyleClass().add("button");
		infoButton.getStyleClass().add("button");
		sendMessage.getStyleClass().add("button");
		logOut.getStyleClass().add("small-button");
		
		//----END STYLING----
		
		//handle button presses
		visitButton.setOnAction(e -> {
			Stage stage = (Stage) visitButton.getScene().getWindow();
			stage.close(); 
			Summary.display(patientID);
		});
		infoButton.setOnAction(e -> {
			Stage stage = (Stage) infoButton.getScene().getWindow();
			stage.close(); 
			UpdatePatientInfoView.display(patientID);
		});
		sendMessage.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
			stage.close();
			MessageHistoryView.display(patientID, patientID, "Doctor");
		});
		logOut.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
			stage.close();
			Login.display(); 
		});
	}
}
