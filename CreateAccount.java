package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAccountView {
	public static void display() {		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); //top, right, bottom, left

		TextField fnText = new TextField();
		TextField lnText = new TextField();
		TextField dobText = new TextField();
		TextField passwordText = new TextField();

		fnText.setPrefColumnCount(14);
		lnText.setPrefColumnCount(14);
		dobText.setPrefColumnCount(14);
		passwordText.setPrefColumnCount(14);

		Label title = new Label("Z's Clinic");
		Label fnLabel = new Label("Name: ");
		//Label lnLabel = new Label("Password: ");
		Label dobLabel = new Label("Date of Birth: ");
		Label passwordLabel = new Label("Password: ");
		Label accountLabel = new Label("Already have an account?");
		 
		pane.add(fnLabel, 0, 1);
		pane.add(fnText, 1, 1);
		pane.add(lnText, 2, 1);
		pane.add(dobLabel, 0, 2);
		pane.add(dobText, 1, 2);
		pane.add(passwordLabel, 0, 3);
		pane.add(passwordText, 1, 3);
		pane.add(accountLabel, 1, 20);
		
		Button create = new Button("Create Account");
		create.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(create, 1, 5);
		
		Button goBack = new Button("Go Back");
		goBack.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(goBack, 1, 22);

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(title);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(70,0,0,0)); //top, right, bottom, left
		 
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setTop(vBox);

		Scene scene = new Scene(borderPane, 500, 500);
		Stage window = new Stage();
		window.setTitle("Patient View");
		window.setScene(scene);
		window.show();
		
		//handle the button presses
		/*patientIntakeButton.setOnAction(e -> { 
			ReceptionistView.display(); 
			});
		techButton.setOnAction(e -> {
			TechnicianView.display(); 
	        });
		patientViewButton.setOnAction(e -> {
			EnterPatientIDView.display(); 
	        });*/
		goBack.setOnAction(e -> {
			Stage stage = (Stage) goBack.getScene().getWindow();
			stage.close();
			MainView.display();
		});
	}
}
