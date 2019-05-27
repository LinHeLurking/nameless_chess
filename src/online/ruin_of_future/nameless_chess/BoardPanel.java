package online.ruin_of_future.nameless_chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class BoardPanel extends StackPane implements EventHandler<ActionEvent> {
	
	private View view;
	
	BoardPanel(View view) {
		this.view = view;
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
	
	}
}
