package online.ruin_of_future.nameless_chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;


public class BoardPanel {
	
	private View view;
	private GridPane panel;
	private Button before = null;
	private CppSync syncer = new CppSync();
	
	BoardPanel(View view) {
		this.view = view;
		this.init();
	}
	
	// For board.fxml loading controller
	public BoardPanel() {
		if (syncer.sync_nothing() == 1) {
			System.out.println("Loading cpp library successfully.");
			System.out.println("The game looks like below in cpp's side");
			syncer.sync_drawboard_in_commandline();
		}
	}
	
	public void setView(View view) {
		this.view = view;
	}

	void debugLoop(){
		this.syncer.sync_unforgiving_game_loop();
	}
	
	private void init() {
		FXMLLoader loader = new FXMLLoader();
		URL url = getClass().getResource("board.fxml");
		loader.setLocation(url);
		try {
			this.panel = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	GridPane getPanel() {
		return this.panel;
	}
	
	@FXML
	public void onButtonClick(ActionEvent event) {
		Button button = (Button) event.getSource();
		String[] pos = button.getText().split("\\s");
		int x = Integer.parseInt(pos[0]);
		int y = Integer.parseInt(pos[1]);
		int id = Integer.parseInt(button.getId().split("-")[1]);
		if (this.before == null) {
			this.before = button;
			return;
		}
		String[] fromPos = this.before.getText().split("\\s");
		int fromX = Integer.parseInt(fromPos[0]);
		int fromY = Integer.parseInt(fromPos[1]);
		int fromId = Integer.parseInt(this.before.getId().split("-")[1]);
		
		System.out.println(String.format("coordinate a: (%d, %d) id: %d", fromX, fromY, fromId));
		System.out.println(String.format("coordinate b: (%d, %d) id: %d", x, y, id));
		
		if (syncer.sync_java_manual_move(fromX, fromY, x, y) != 2) {
			button.setId(String.format("init-%d", fromId));
			this.before.setId("init-4");
		}
		this.before = null;
	}
}
