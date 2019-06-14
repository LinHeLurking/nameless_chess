package online.ruin_of_future.nameless_chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Play extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		View view = new View(primaryStage);
		primaryStage.setOnCloseRequest((windowEvent) -> {
				view.backToStart();
				Platform.exit();
				System.exit(0);
		});
	}
}