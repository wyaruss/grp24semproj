package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UpdatePatientInfoView {
    public static void display(String patientID) {
        GridPane input = new GridPane();
        input.setVgap(10);
        input.setHgap(10);
        input.setPadding(new Insets(25, 25, 25, 25));
        input.setAlignment(Pos.TOP_CENTER);

        TextField username = new TextField();
        TextField insurance = new TextField();
        TextField pAddress = new TextField();
        TextField pCity = new TextField();
        TextField pState = new TextField();
        TextField pZip = new TextField();
        TextField email = new TextField();
        TextField phone = new TextField();
        TextField phName = new TextField();
        TextField phAddress = new TextField();
        TextField phCity = new TextField();
        TextField phState = new TextField();
        TextField phZip = new TextField();

        setEditable(false, username, insurance, email, phone, pAddress, pCity, pState, pZip,
                    phName, phAddress, phCity, phState, phZip);

        Button editButton = new Button("Edit");
        Button updateButton = new Button("Update");
        Button returnButton = new Button("Return");
        
        Label usernameLabel = new Label("Username: " + patientID);
        Label insuranceLabel = new Label("Insurance ID");
        Label pAddressLabel = new Label("Address");
        Label pCityLabel = new Label("City");
        Label pStateLabel = new Label("State");
        Label pZipLabel = new Label("Zip");
        
        Label pInfoLabel = new Label ("CONTACT INFORMATION");
        Label emailLabel = new Label("Email");
        Label phoneLabel = new Label("Phone");
        
        Label phInfoLabel = new Label("PHARMACY INFORMATION");
        Label phNameLabel = new Label("Pharmacy Name");
        Label phAddressLabel = new Label("Address");
        Label phCityLabel = new Label("City");
        Label phStateLabel = new Label("State");
        Label phZipLabel = new Label("Zip");
        

        input.add(usernameLabel, 0, 1);
        input.add(insuranceLabel, 0, 2);
        input.add(insurance, 1, 2);
        input.add(pAddressLabel, 0, 4);
        input.add(pAddress, 0, 5);
        input.add(pCityLabel, 0, 6);
        input.add(pCity, 0, 7);
        input.add(pStateLabel, 1, 6);
        input.add(pState, 1, 7);
        input.add(pZipLabel, 2, 6);
        input.add(pZip, 2, 7);
        
        input.add(pInfoLabel, 0, 12);
        input.add(emailLabel, 0, 13);
        input.add(email, 0, 14);
        input.add(phoneLabel, 1, 13);
        input.add(phone, 1, 14);
        
        input.add(phInfoLabel, 0, 19);
        input.add(phNameLabel, 0, 20);
        input.add(phName, 0, 21);
        input.add(phAddressLabel, 0, 22);
        input.add(phAddress, 0, 23);
        input.add(phCityLabel, 0, 24);
        input.add(phCity, 0, 25);
        input.add(phStateLabel, 1, 24);
        input.add(phState, 1, 25);
        input.add(phZipLabel, 2, 24);
        input.add(phZip, 2, 25);
        
        input.add(returnButton, 0, 28);
        input.add(editButton, 1, 28);
        input.add(updateButton, 2, 28);

        Scene scene = new Scene(input, 700, 700);
        Stage window = new Stage();
        window.setTitle("Patient Information");
        window.setScene(scene);
        window.show();
        
        //----STYLING----
        String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		input.getStyleClass().add("background");
		
		//Labels
		usernameLabel.getStyleClass().add("input-label");
		insuranceLabel.getStyleClass().add("input-label");
		pAddressLabel.getStyleClass().add("input-label");
		pCityLabel.getStyleClass().add("input-label");
		pStateLabel.getStyleClass().add("input-label");
		pZipLabel.getStyleClass().add("input-label");
		
		pInfoLabel.getStyleClass().add("input-label");
		emailLabel.getStyleClass().add("input-label");
		phoneLabel.getStyleClass().add("input-label");
		
		phInfoLabel.getStyleClass().add("input-label");
		phNameLabel.getStyleClass().add("input-label");
		phAddressLabel.getStyleClass().add("input-label");
		phCityLabel.getStyleClass().add("input-label");
		phStateLabel.getStyleClass().add("input-label");
		phZipLabel.getStyleClass().add("input-label");
		
		//Buttons
		returnButton.getStyleClass().add("button");
		editButton.getStyleClass().add("button");
		updateButton.getStyleClass().add("button");
        
        //----END STYLING----

        // Set the current contact information on this screen
        try(BufferedReader reader = new BufferedReader(new FileReader(patientID + "_ContactInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.startsWith("Username: "))
                    username.setText(line.substring(9).trim());
                if(line.startsWith("Insurance ID: "))
                    insurance.setText(line.substring(13).trim());
                if(line.startsWith("Email: "))
                    email.setText(line.substring(7).trim());
                if(line.startsWith("Phone number: "))
                    phone.setText(line.substring(14).trim());
                if(line.startsWith("Patient Address: "))
                    pAddress.setText(line.substring(17).trim());
                if(line.startsWith("Patient City: "))
                    pCity.setText(line.substring(14).trim());
                if(line.startsWith("Patient State: "))
                    pState.setText(line.substring(15).trim());
                if(line.startsWith("Patient Zip: "))
                    pZip.setText(line.substring(13).trim());
                if(line.startsWith("Pharmacy Name: "))
                    phName.setText(line.substring(15).trim());
                if(line.startsWith("Pharmacy Address: "))
                    phAddress.setText(line.substring(18).trim());
                if(line.startsWith("Pharmacy City: "))
                    phCity.setText(line.substring(15).trim());
                if(line.startsWith("Pharmacy State: "))
                    phState.setText(line.substring(16).trim());
                if(line.startsWith("Pharmacy Zip: "))
                    phZip.setText(line.substring(14).trim());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Initially disable the Update button
        updateButton.setDisable(true);

        editButton.setOnAction(e -> {
            setEditable(true, insurance, email, phone, pAddress, pCity, pState, pZip,
                        phName, phAddress, phCity, phState, phZip);
            editButton.setDisable(true);
            updateButton.setDisable(false);
        });

        updateButton.setOnAction(e -> {
            if (!isValidPhoneNumber(phone.getText())) {
                showAlert("Please enter a valid phone number.");
                return;
            }
            if (!isValidEmail(email.getText())) {
                showAlert("Please enter a valid email address.");
                return;
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(patientID + "_ContactInfo.txt"))) {
                writer.println("Username: " + username.getText());
                writer.println("Insurance ID: " + insurance.getText());
                writer.println("Email: " + email.getText());
                writer.println("Phone number: " + phone.getText());
                writer.println("Patient Address: " + pAddress.getText());
                writer.println("Patient City: " + pCity.getText());
                writer.println("Patient State: " + pState.getText());
                writer.println("Patient Zip: " + pZip.getText());
                writer.println("Pharmacy Name: " + phName.getText());
                writer.println("Pharmacy Address: " + phAddress.getText());
                writer.println("Pharmacy City: " + phCity.getText());
                writer.println("Pharmacy State: " + phState.getText());
                writer.println("Pharmacy Zip: " + phZip.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            setEditable(false, insurance, email, phone, pAddress, pCity, pState, pZip,
                        phName, phAddress, phCity, phState, phZip);
            editButton.setDisable(false);
            updateButton.setDisable(true);
        });

        returnButton.setOnAction(e -> {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            PatientHome.display(patientID);
        });
    }

    private static void setEditable(boolean editable, TextField... fields) {
        for (TextField field : fields) {
            field.setEditable(editable);
        }
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("\\d{3}-\\d{3}-\\d{4}", phoneNumber);
    }

    private static boolean isValidEmail(String email) {
        return Pattern.matches("^(.+)@(.+)$", email);
    }
}
