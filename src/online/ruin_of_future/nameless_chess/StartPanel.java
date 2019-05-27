package online.ruin_of_future.nameless_chess;

import javafx.event.*;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class StartPanel extends StackPane implements EventHandler<ActionEvent> {
	
	private View view;
	
	StartPanel(View view) {
		this.view = view;
		
		Canvas canvas = new Canvas(500, 500);
		this.getChildren().add(canvas);
		this.setStyle("-fx-background-color: pink");
		
		Button button = new Button("Start");
		button.setMinWidth(100);
		button.setPrefHeight(50);
		button.setOnAction(this);
		
		this.getChildren().add(button);
		setAlignment(button, Pos.BOTTOM_CENTER);
		
		Label label = new Label();
		label.setMinWidth(200);
		label.setPrefHeight(100);
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		this.view.changeToBoard();
	}
}
