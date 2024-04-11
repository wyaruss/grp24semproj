package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MessageHistoryView {
    public static void display(String patientID, String sender, String recipient) {
        // Read messages from the file (recipientID_Messages.txt) and display them
        StringBuilder messages = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_Messages.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messages.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            messages.append("No messages found for ").append(recipient);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));

        // Create components
        Label titleLabel = new Label("Message History with " + recipient + ":");
        TextArea messagesTextArea = new TextArea(messages.toString());
        messagesTextArea.setEditable(false);
        Button sendMessageButton = new Button("Send Message");
        Button returnButton = new Button("Return");

        // Add components to the layout
        layout.getChildren().addAll(titleLabel, messagesTextArea, sendMessageButton, returnButton);

        // Create and display the scene
        Scene scene = new Scene(layout, 500, 500);
        Stage window = new Stage();
        window.setTitle("Message History");
        window.setScene(scene);
        window.show();

        //----STYLING----
        String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		layout.getStyleClass().add("background");
		
		//Labels
		titleLabel.getStyleClass().add("input-label");
		
		//Buttons
		sendMessageButton.getStyleClass().add("button");
		returnButton.getStyleClass().add("small-button");
		
		//----END STYLING----
        
        // Handle sending a new message
        sendMessageButton.setOnAction(e -> {
            MessageComposeView.display(patientID, sender, recipient);
            window.close();
        });

        // Handle returning to the main screen
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
        	stage.close();
        	if(sender.equals("Doctor"))
        		DoctorPatientView.display(patientID);
        	else
        		PatientHome.display(patientID);
        });
    }
}
