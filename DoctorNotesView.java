package application;

public class DoctorNotesView {
	static public void display() {
		TextField notesFromPhysical = new TextField();
		TextField medicationName = new TextField();
		TextField Reason = new TextField();
		
		Button enterButton = new Button("Enter"); 
		Button returnButton = new Button("Return");
		
		notesFromPhysical.setPrefWidth(200);
		
		
		GridPane input = new GridPane();
        input.setVgap(10);
        input.setHgap(10);
        
        input.addRow(0, new Label("Notes:"), noteFromPhysical);
        input.addRow(3, new Label("Medication to send:"), medicationName);
        input.addRow(5, new Label("Reason:"), Reason);
        input.add(enterButton, 2, 6);
        input.add(returnButton, 2, 7);  
        
//        StackPane stackPane = new StackPane();
//        StackPane.setAlignment(enterButton, Pos.BOTTOM_RIGHT);
//        StackPane.setAlignment(returnButton, Pos.BOTTOM_LEFT);
        
//        stackPane.getChildren().addAll(input, enterButton, returnButton);
//        stackPane.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(stackPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Physical");
		window.setScene(scene);
		window.show();
        
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            DoctorPatientView.display(); //relies on that page being there
        });
        
        enterButton.setOnAction(e -> {
        	if(isAnyFieldEmpty(
        	notesFromPhysical.getText(),
        	mediactionName.getText(),
        	Reason.getText()
        	)) {
        		showAlert("All fields must be filled out.");
        	}
        	else {
        		System.out.println("hello");
        		//implement save to file funcitonality
        	}
        });
	}
        
        
        
        
		
		
		
		
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
