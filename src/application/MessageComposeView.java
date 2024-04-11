package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MessageComposeView {
    public static void display(String patientID, String sender, String recipient) {
        // Create the layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setPadding(new Insets(25, 25, 25, 25));

        // Create components
        Label recipientLabel = new Label("Recipient:");
        TextField recipientTextField = new TextField(recipient);
        Label messageLabel = new Label("Message:");
        TextArea messageTextArea = new TextArea();
        Button returnButton = new Button("Return");
        Button sendButton = new Button("Send");

        // Add components to the layout
        pane.add(recipientLabel, 0, 0);
        pane.add(recipientTextField, 1, 0);
        pane.add(messageLabel, 0, 1);
        pane.add(messageTextArea, 1, 1);
        pane.add(returnButton, 0, 2);
        pane.add(sendButton, 1, 2);

        // Create and display the scene
        Scene scene = new Scene(pane, 700, 700);
        Stage window = new Stage();
        window.setTitle("Compose Message");
        window.setScene(scene);
        window.show();
        
        //----STYLING----
        String css = application.Main.class.getResource("styles.css").toExternalForm(); 
		scene.getStylesheets().add(css);
		
		//Panes
		pane.getStyleClass().add("background");
		
		//Labels
		recipientLabel.getStyleClass().add("input-label");
		messageLabel.getStyleClass().add("input-label");
		
		//Buttons
		sendButton.getStyleClass().add("button");
		returnButton.getStyleClass().add("small-button");
        
        //----END STYLING----

        // Handle sending the message
        sendButton.setOnAction(e -> {
            String message = messageTextArea.getText();

            // Write the message to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientID + "_Messages.txt", true))) {
                writer.write(sender + ": " + message + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Show confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Sent");
            alert.setHeaderText(null);
            alert.setContentText("Message sent successfully.");
            alert.showAndWait();

            // Clear the message text area
            messageTextArea.clear();
        });

        // Handle returning to the Message History screen
        returnButton.setOnAction(e -> {
        	Stage stage = (Stage) returnButton.getScene().getWindow();
        	stage.close();
        	MessageHistoryView.display(patientID, sender, recipient);
        });
    }
}

