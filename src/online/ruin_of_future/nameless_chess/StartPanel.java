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
		this.init();
	}
	
	private void init() {
		Canvas canvas = new Canvas(550, 550);
		this.getChildren().add(canvas);
		
		Button button = new Button("Start");
		button.setMinWidth(100);
		button.setPrefHeight(50);
		button.setOnAction(this);
		
		this.getChildren().add(button);
		setAlignment(button, Pos.BOTTOM_CENTER);
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		this.view.changeToBoard();
	}
}
