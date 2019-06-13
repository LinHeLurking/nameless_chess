package online.ruin_of_future.nameless_chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

class IdlePanel extends StackPane implements EventHandler<ActionEvent>{
	
	private View view;
	
	IdlePanel(View view) {
		this.view = view;
		this.init();
	}
	
	private void init() {
		Canvas canvas = new Canvas(550, 550);
		this.getChildren().add(canvas);
		this.setId("idle");
		Button start_button = new Button("Home");
		start_button.setId("home");
		start_button.setMinWidth(100);
		start_button.setPrefHeight(50);
		start_button.setOnAction(this);
		
		this.getChildren().add(start_button);
		setAlignment(start_button, Pos.BOTTOM_CENTER);
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
		this.view.backToStart();
	}
}
