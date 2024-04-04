package application;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class DoctorVitalsView {
	public static void display() {
		
		Button enterButton = new Button("Enter");
		Button returnButton = new Button("Return");
        Label patientIntakeLabel = new Label("Patient Vitals");
        
        //elements to be saved
        textfield age = new TextField();
        textfield weight = new TextField();
        textfield height = new TextField();
        textfield bodyTemp = new TextField();
        textfield bloodPressure = new TextField();
        
        
        GridPane input = new GridPane();
        input.setVgap(10);
        input.setHgap(10);
        
        input.addRow(0, new Label("Age:"), age);
        input.addRow(1, new Label("Weight:"), weight);
        input.addRow(2, new Label("Height:"), height);
        input.addRow(3, new Label("Body Temperature:"), bodyTemp);
        input.addRow(4, new Label("Blood Pressure:"), bloodPressure);
        input.add(enterButton, 1, 5);
        input.add(returnButton, 2, 6);
        
        
        
        StackPane stackPane = new StackPane();
        StackPane.setAlignment(saveButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(returnButton, Pos.BOTTOM_LEFT);
        
        stackPane.getChildren().addAll(input, saveButton, returnButton);
        
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            doctorPatientView.display(); //relies on that page being there
        });
        
        enterButton.setOnAction(e -> {
        	if(isAnyFieldEmpty(
        	age.getText();
        	weight.getText();
        	height.getText();
        	bodyTemp.getText();
        	bloodPressure.getText();
        	)) {
        		showAlert("All fields must be filled out.");
        	}
        	else {
        		//save data function
        	}
        	
        });
        
        
        
           
        
		
	}
	 private boolean isAnyFieldEmpty(String... fields) {
	        for (String field : fields) {
	            if (field.isEmpty()) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
	 private void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	
	
	
}
