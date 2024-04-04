package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorVitalsView {
	public static void display() {
		
		Button enterButton = new Button("Enter");
		Button returnButton = new Button("Return");
        
        //elements to be saved
        TextField age = new TextField();
        TextField weight = new TextField();
        TextField height = new TextField();
        TextField bodyTemp = new TextField();
        TextField bloodPressure = new TextField();
        
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
        StackPane.setAlignment(enterButton, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(returnButton, Pos.BOTTOM_LEFT);
        
        stackPane.getChildren().addAll(input, enterButton, returnButton);
        stackPane.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(stackPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient Vitals");
		window.setScene(scene);
		window.show();
        
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            DoctorPatientView.display(); //relies on that page being there
        });
        
        enterButton.setOnAction(e -> {
        	if(isAnyFieldEmpty(
        	age.getText(),
        	weight.getText(),
        	height.getText(),
        	bodyTemp.getText(),
        	bloodPressure.getText()
        	)) {
        		showAlert("All fields must be filled out.");
        	}
        	else {
        		System.out.println("hello");
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
	 
	 private static void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}
