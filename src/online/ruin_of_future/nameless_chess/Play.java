package online.ruin_of_future.nameless_chess;

import javafx.application.Application;
import javafx.stage.Stage;

public class Play extends Application {
	
	// view and controllers
	private View view;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.view = new View(primaryStage);
	}
}
