package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Summary {
    public static void display(String patientID) {
        String notes = "";
        String medication = "";

        //read from patientID_Physical.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(patientID + "_Physical.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Notes from the physical: ")) {
                    notes = line.substring(25).trim();
                } else if (line.startsWith("Medication to send: ")) {
                    medication = line.substring(23).trim();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //summary window
        GridPane summaryGrid = new GridPane();
        summaryGrid.setAlignment(Pos.CENTER);
        summaryGrid.setPadding(new Insets(20));
        summaryGrid.setVgap(10);

        //notes from the physical textarea
        TextArea notesTextArea = new TextArea();
        notesTextArea.setText(notes.equals("n/a") ? "No notes" : notes);
        notesTextArea.setEditable(false);
        notesTextArea.setPrefSize(400, 200);

        //medication textarea
        TextArea medicationTextArea = new TextArea();
        medicationTextArea.setText(medication.isEmpty() ? "No medications" : medication);
        medicationTextArea.setEditable(false);
        medicationTextArea.setPrefSize(400, 200);

        summaryGrid.add(new Label("Summary"), 0, 0);
        summaryGrid.add(notesTextArea, 0, 1);
        summaryGrid.add(new Label("Medications"), 0, 2);
        summaryGrid.add(medicationTextArea, 0, 3);

        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> {
            Stage stage = (Stage) returnButton.getScene().getWindow();
            stage.close();
            PatientHome.display(patientID);
        });
        summaryGrid.add(returnButton, 0, 4);

        Scene scene = new Scene(summaryGrid, 500, 500);
        Stage summaryStage = new Stage();
        summaryStage.setTitle("Summary");
        summaryStage.setScene(scene);
        summaryStage.show();
    }
}
