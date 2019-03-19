package online.ruin_of_future.nameless_chess;

import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;


public class View implements EventHandler<ActionEvent> {
	
	private Stage stage;
	private StartPanel start;
	private BoardPanel board;
	private BorderPane root;
	
	View(Stage stage) {
		this.stage = stage;
		this.start = new StartPanel(this);
		this.board = new BoardPanel(this);
		initUI(this.stage);
	}
	
	private void initUI(Stage stage) {
		this.root = new BorderPane();
		this.root.setTop(this.createMenuBar());
		this.root.setCenter(this.start);
		
		Scene scene = new Scene(this.root);
		stage.setScene(scene);
		stage.setTitle("Nameless Chess");
		stage.setResizable(true);
		
		stage.show();
	}
	
	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;
		menu = new Menu("Game");
		
		menuItem = new MenuItem("New Game");
		menuItem.setId("New Game");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Quit");
		menuItem.setId("Quit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menu.getItems().add(new SeparatorMenuItem());
		
		menuItem = new MenuItem("Exit");
		menuItem.setId("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);
		return menuBar;
	}
	
	void changeToBoard() {
		this.root.setCenter(this.board);
	}
	
	private void createNewGame() {
		this.board = new BoardPanel(this);
		this.root.setCenter(this.board);
	}
	
	private void changeToStart() {
		this.root.setCenter(this.start);
	}
	
	@Override
	public void handle(ActionEvent event) {
		String command = ((MenuItem) event.getSource()).getId();
		switch (command) {
			case "New Game":
				this.createNewGame();
				break;
			case "Quit":
				this.createNewGame();
				this.changeToStart();
				break;
			case "Exit":
				System.exit(0);
		}
	}
}
