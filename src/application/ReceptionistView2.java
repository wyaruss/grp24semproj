package application;

import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReceptionistView2 {
	public static void display(String patientID) {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25));

		TextField insurance = new TextField();
		TextField email = new TextField();
		TextField phone = new TextField();
		TextField pAddress = new TextField();
		TextField pCity = new TextField();
		TextField pState = new TextField();
		TextField pZip = new TextField();
		TextField phName = new TextField();
		TextField phAddress = new TextField();
		TextField phCity = new TextField();
		TextField phState = new TextField();
		TextField phZip = new TextField();

		email.setPrefColumnCount(14);
		phone.setPrefColumnCount(14);
		pAddress.setPrefColumnCount(14);
		pCity.setPrefColumnCount(14);
		pState.setPrefColumnCount(14);
		pZip.setPrefColumnCount(14);
		phName.setPrefColumnCount(14);
		phAddress.setPrefColumnCount(14);
		phCity.setPrefColumnCount(14);
		phState.setPrefColumnCount(14);
		phZip.setPrefColumnCount(14);

		Label info = new Label("CONTACT INFORMATION");
		Label insuranceLabel = new Label("Insurance ID");
		Label emailLabel = new Label("Email");
		Label phoneLabel = new Label("Phone number");
		Label pAddressLabel = new Label("Patient Address");
		Label pCityLabel = new Label("City");
		Label pStateLabel = new Label("State");
		Label pZipLabel = new Label("Zip");
		Label phLabel = new Label("PHARMACY INFORMATION");
		Label phNameLabel = new Label("Pharmacy Name");
		Label phAddressLabel = new Label("Address");
		Label phCityLabel = new Label("City");
		Label phStateLabel = new Label("State");
		Label phZipLabel = new Label("Zip");
		 
		pane.add(info, 0, 0);
		pane.add(insuranceLabel, 0, 1);
		pane.add(insurance, 1, 1);
		pane.add(emailLabel, 0, 2);
		pane.add(email, 1, 2);
		pane.add(phoneLabel, 0, 3);
		pane.add(phone, 1, 3);
		
		pane.add(pAddressLabel, 0, 4);
		pane.add(pAddress, 1, 4);
		pane.add(pCityLabel, 0, 5);
		pane.add(pCity, 1, 5);
		pane.add(pStateLabel, 0, 6);
		pane.add(pState, 1, 6);
		pane.add(pZipLabel, 0, 7);
		pane.add(pZip, 1, 7);
		
		pane.add(phLabel, 0, 8);
		pane.add(phNameLabel, 0, 9);
		pane.add(phName, 1, 9);
		pane.add(phAddressLabel, 0, 10);
		pane.add(phAddress, 1, 10);
		pane.add(phCityLabel, 0, 11);
		pane.add(phCity, 1, 11);
		pane.add(phStateLabel, 0, 12);
		pane.add(phState, 1, 12);
		pane.add(phZipLabel, 0, 13);
		pane.add(phZip, 1, 13);

		Button enter = new Button("Save");
		pane.add(enter, 1, 15);
	    
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient Contact Info");
		window.setScene(scene);
		window.show();
		
		//----STYLING----
		String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		pane.getStyleClass().add("background");
		
		//Labels
		info.getStyleClass().add("input-label");
		insuranceLabel.getStyleClass().add("input-label");
		emailLabel.getStyleClass().add("input-label");
		phoneLabel.getStyleClass().add("input-label");
		pAddressLabel.getStyleClass().add("input-label");
		pCityLabel.getStyleClass().add("input-label");
		pStateLabel.getStyleClass().add("input-label");
		pZipLabel.getStyleClass().add("input-label");
		
		phLabel.getStyleClass().add("input-label");
		phNameLabel.getStyleClass().add("input-label");
		phAddressLabel.getStyleClass().add("input-label");
		phCityLabel.getStyleClass().add("input-label");
		phStateLabel.getStyleClass().add("input-label");
		phZipLabel.getStyleClass().add("input-label");
		
		//Buttons
		enter.getStyleClass().add("button");
		
		//----END STYLING----
		
		enter.setOnAction(e -> {
			if (isAnyFieldEmpty(email.getText(), phone.getText(), pAddress.getText(), pCity.getText(),
		            pState.getText(), pZip.getText(), phName.getText(), phAddress.getText(),
		            phCity.getText(), phState.getText(), phZip.getText())) {
		        showAlert("Error", "Empty fields.\nPlease fill in all the fields before saving.");
		        return;
		    }
			
			if (!isValidEmail(email.getText())) {
		        showAlert("Error", "Invalid email format.\nPlease enter a valid email address.");
		        return;
		    }

		    if (!isValidPhoneNumber(phone.getText())) {
		        showAlert("Error", "Invalid phone number format.\nPlease enter a valid phone number (e.g., 123-456-7890).");
		        return;
		    }
			
			String filename = patientID + "_ContactInfo.txt";
			
			 //write patient contact info to the file
		    try {
		        FileWriter writer = new FileWriter(filename);
		        writer.write("Username: " + patientID + "\n");
		        writer.write("Insurance ID: " + insurance.getText() + "\n");
		        writer.write("Email: " + email.getText() + "\n");
		        writer.write("Phone number: " + phone.getText() + "\n");
		        writer.write("Patient Address: " + pAddress.getText() + "\n");
		        writer.write("Patient City: " + pCity.getText() + "\n");
		        writer.write("Patient State: " + pState.getText() + "\n");
		        writer.write("Patient Zip: " + pZip.getText() + "\n");
		        writer.write("Pharmacy Name: " + phName.getText() + "\n");
		        writer.write("Pharmacy Address: " + phAddress.getText() + "\n");
		        writer.write("Pharmacy City: " + phCity.getText() + "\n");
		        writer.write("Pharmacy State: " + phState.getText() + "\n");
		        writer.write("Pharmacy Zip: " + phZip.getText() + "\n");
		        writer.close();
		        showAlert("Saved successfully", "Contact information saved successfully.");

		        Stage stage = (Stage) enter.getScene().getWindow();
		        stage.close();
		        Login.display();

		    } catch (IOException exception) {
		        showAlert("Error", "Error writing to file.");
		        exception.printStackTrace();
		    }
			
		});
	}
	
	private static void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(null);
	    alert.setContentText(content);
	    alert.showAndWait();
	}
	
	//check to see if any fields are empty
	static boolean isAnyFieldEmpty(String... fields) {
        for (String field : fields) {
            if (field.isEmpty()) {
                return true;
            }
        }
        return false;
    }
	
	private static boolean isValidEmail(String email) {
	    return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
	}

	private static boolean isValidPhoneNumber(String phone) {
	    return phone.matches("\\d{3}-\\d{3}-\\d{4}");
	}
}
