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
	private Button before;
	private String c_id = null;
	//private CppSync syncer = new CppSync();
	
	
	BoardPanel(View view) {
		this.view = view;
		this.init();
	}
	
	// For board.fxml loading controller
	public BoardPanel() {
		/*
		if (syncer.sync_nothing() == 1) {
			System.out.println("Loading cpp library successfully.");
			System.out.println("The game looks like below in cpp's side");
			syncer.sync_drawboard_in_commandline();
		}*/
	}
	
	public void setView(View view) {
		this.view = view;
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
		String init = button.getId();
		int id = -1;
		String[] pos = button.getText().split("\\s");
		int x = Integer.parseInt(pos[0]);
		int y = Integer.parseInt(pos[1]);
		if (this.before == button) return;
		
		if (init != null) {
			this.c_id = init;
			id = Integer.parseInt(init.split("-")[1]);
		} else if (this.c_id != null) {
			button.setId(this.c_id);
			this.before.setId(null);
			this.c_id = null;
		}
		System.out.println(String.format("coordinate: (%d, %d) player: %d", x, y, id));
		this.before = button;
	}
}
