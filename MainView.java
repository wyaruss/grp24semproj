package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView {
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

		Label title = new Label("Z's Clinic");
		Label usernameLabel = new Label("Username: ");
		Label passwordLabel = new Label("Password: ");
		Label accountLabel = new Label("Don't already have an account?");
		 
		pane.add(usernameLabel, 0, 1);
		pane.add(usernameText, 1, 1);
		pane.add(passwordLabel, 0, 2);
		pane.add(passwordText, 1, 2);
		pane.add(accountLabel, 1, 20);

	    Button login = new Button("Login");
		login.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(login, 1, 5);
		
		Button create = new Button("Create Account");
		create.setStyle("-fx-background-color: #78A8FF; -fx-border-color: #669CFF; -fx-border-width: 1px; -fx-font-size: 17");
		pane.add(create, 1, 22);

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
		
		//handle when create account button is pressed
		create.setOnAction(e -> {
			Stage stage = (Stage) create.getScene().getWindow();
			stage.close();
			CreateAccountView.display(); 
		});
	}
}
