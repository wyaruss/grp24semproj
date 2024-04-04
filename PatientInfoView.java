package application;

public class PatientInfoView {
	public static void display() {
		TextField name = new TextField();
		TextField dob = new TextField();
		TextField sex = new TextField();
		TextField address = new TextField();
		TextField city = new TextField();
		TextField state = new TextField();
		TextField zip = new TextField();
		TextField email = new TextField();
		TextField phoneNumber = new TextField();
		TextField pharmacy = new TextField();
		TextField aOfPharmacy = new TextField();
		TextField pCity = new TextField();
		TextField pState = new TextField();
		TextField pZip = new TextField();
		
		Button enterButton = new Button("Update"); //bottom
		Button returnButton = new Button("Return");
		
		 returnButton.setOnAction(e -> {
	        	Stage stage = (Stage) returnButton.getScene().getWindow();
	            stage.close();
	            PatientHome.display(); //relies on that page being there
	        });
		 
		 
		 enterButton.setOnAction(e -> {
	        	if(isAnyFieldEmpty(
	        	name.getText();
	        	dob.getText();
	        	sex.getText();
	        	address.getText();
	        	city.getText();
	        	state.getText();
	        	zip.getText();
	        	email.getText();
	        	phoneNumber.getText();
	        	pharmacy.getText();
	        	aOfPharmacy.getText();
	        	pCity.getText();
	        	pState.getText();
	        	pZip.getText();
	        	
	        	)) {
	        		showAlert("All fields must be filled out.");
	        	}
	        	else {
	        		//save data function
	        		system.out.println("helllllo");
	        	}
	        	
	        });
		 
		 GridPane input = new GridPane();
	        input.setVgap(10);
	        input.setHgap(10);
	        
	        
	        input.addRow(0, new Label("Name:"), name);
	        input.addRow(1, new Label("Date of Birth:"), dob);
	        input.addRow(2, new Label("Sex:"), sex);
	        input.addRow(3, new Label("Address:"), address);
	        input.addRow(4, new Label("City:"), city, new Label("State:") state, new Label("Zip:") zip);
	        input.addRow(5, new Label("Email:"), email);
	        input.addRow(6, new Label("Phone Number"), phoneNumber);
	        input.addRow(7, new Label("Pharmacy Name:"), pharmacy);
	        input.addRow(8, new Label("Address:"), aOfPharmacy);
	        input.addRow(9, new Label("City:"), pCity, new Label("State:") pState, new Label("Zip:") pZip);
	        
	        
	        input.add(enterButton, 1, 10);
	        input.add(returnButton, 1, 11);
	        Scene scene = new Scene(stackPane, 500, 500);
			Stage window = new Stage();
			window.setTitle("Information Update");
			window.setScene(scene);
			window.show();
	        
	        
	    
		
		
		
		
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
