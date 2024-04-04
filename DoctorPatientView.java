package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorPatientView {
	public static void display() {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25));
		
		Label title = new Label("Patient: Firstname Lastname");
	    Button historyButton = new Button("Patient History");
	    Button vitalsButton = new Button("Vitals");
	    Button physicalButton = new Button("Physical Test");
        Button logOut = new Button("Log Out");
	       
        VBox.setMargin(title, new Insets(20, 0, 80, 0));
	    VBox layout = new VBox(10);
	    layout.getChildren().addAll(title, historyButton, vitalsButton, physicalButton);
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
		window.setTitle("Doctor Patient View");
		window.setScene(scene);
		window.show();
			
		//handle the button presses
		historyButton.setOnAction(e -> { 
			Stage stage = (Stage) logOut.getScene().getWindow();
	       	 	stage.close();
			DoctorHistoryView.display();
		});
		vitalsButton.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
	       	 	stage.close();
			DoctorVitalsView.display(); 
		});
		physicalButton.setOnAction(e -> {
			Stage stage = (Stage) logOut.getScene().getWindow();
	       	 	stage.close();
			//EnterPatientIDView.display(); 
		});
		logOut.setOnAction(e -> {
			//close the current window
	        Stage stage = (Stage) logOut.getScene().getWindow();
	        stage.close();
	            
	        //open patientView
	        Login.display(); 
		});
	}
}
