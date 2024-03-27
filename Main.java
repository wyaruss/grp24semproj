package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MainView.display();
		//CreateAccountView.display();
		//DoctorHomeView.display();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

