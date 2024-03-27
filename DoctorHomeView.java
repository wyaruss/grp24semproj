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

public class DoctorHomeView {
	public static void display() {		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setPadding(new Insets(25,25,25,25)); //top, right, bottom, left

		TextField usernameText = new TextField();
		TextField passwordText = new TextField();

		usernameText.setPrefColumnCount(14);
		passwordText.setPrefColumnCount(14);

		Label title = new Label("Patient List");
		Label lnLabel = new Label("Last Name, ");
		Label fnLabel = new Label("First Name, ");
		Label dobLabel = new Label("DOB");
		 
		pane.add(lnLabel, 0, 1);
		pane.add(fnLabel, 1, 1);
		pane.add(dobLabel, 2, 1);

	    Button messages = new Button("Messages");
		messages.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(messages, 1, 22);
		
		Button logout = new Button("Log out");
		logout.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(logout, 2, 22);

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
		
		messages.setOnAction(e -> {
			Stage stage = (Stage) messages.getScene().getWindow();
			stage.close();
			MessagesView.display(); 
		});
		
		logout.setOnAction(e -> {
			Stage stage = (Stage) logout.getScene().getWindow();
			stage.close();
			MainView.display(); 
		});
	}
}
