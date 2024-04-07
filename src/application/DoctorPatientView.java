package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorPatientView {
	public static void display(String patientID) {
		
		//get the patients first name and last name to display it on the screen
		String firstName = "";
        String lastName = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_Info.txt"))) {
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
	    Button historyButton = new Button("Patient History");
	    Button vitalsButton = new Button("Vitals");
	    Button physicalButton = new Button("Physical Test");
	    Button messageButton = new Button("Send " + firstName + " a message");
        Button logOut = new Button("Log Out");
	       
        VBox.setMargin(title, new Insets(20, 0, 80, 0));
	    VBox layout = new VBox(10);
	    layout.getChildren().addAll(title, historyButton, vitalsButton, physicalButton, messageButton);
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
		window.setTitle("Doctor Home");
		window.setScene(scene);
		window.show();
			
		//handle the button presses
		historyButton.setOnAction(e -> { 
			Stage stage = (Stage) logOut.getScene().getWindow();
	        stage.close();
			DoctorHistoryView.display(patientID); 
		});
		vitalsButton.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
	        stage.close();
			DoctorVitalsView.display(patientID); 
		});
		physicalButton.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
	        stage.close();
			PhysicalView.display(patientID); 
		});
		logOut.setOnAction(e -> {
	        Stage stage = (Stage) logOut.getScene().getWindow();
	        stage.close();
	        Login.display(); 
		});
	}
}
