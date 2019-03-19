package online.ruin_of_future.nameless_chess;

import javafx.event.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;

public class StartPanel extends StackPane implements EventHandler<ActionEvent> {
	
	private Canvas canvas;
	private View view;
	
	StartPanel(View view) {
		this.view = view;
		this.canvas = new Canvas(600, 600);
		this.getChildren().add(this.canvas);
		this.setStyle("-fx-background-color: white");
		
		Button button = new Button("Start");
		button.setMinWidth(100);
		button.setPrefHeight(50);
	}
	
	@Override
	public void handle(ActionEvent event) {
		this.view.changeToBoard();
	}
}
