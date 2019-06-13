package online.ruin_of_future.nameless_chess;

import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class View implements EventHandler<ActionEvent> {
	
	private BorderPane root;
	private StartPanel startPanel;
	private BoardPanel boardPanel;
	private Stage stage;
	
	View(Stage stage) {
		this.stage = stage;
		this.init(this.stage);
	}
	
	private void init(Stage stage) {
		this.startPanel = new StartPanel(this);
		this.boardPanel = new BoardPanel(this);
		
		this.root = new BorderPane();
		this.root.setTop(this.createMenuBar());
		this.root.setCenter(this.startPanel);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Nameless Chess");
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
		
		menuItem = new MenuItem("Help");
		menuItem.setId("Help");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Main Menu");
		menuItem.setId("Main Menu");
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
		System.out.println("Start");
		this.root.setCenter(this.boardPanel.getPanel());
	}

	void changeToDebug() {
		System.out.println("Start");
		this.boardPanel.debugLoop();
	}
	
	private void createNewGame() {
		System.out.println("New");
		this.boardPanel = new BoardPanel(this);
		this.root.setCenter(this.boardPanel.getPanel());
	}
	
	private void backToStart() {
		System.out.println("Home");
		this.root.setCenter(this.startPanel);
	}
	
	@Override
	public void handle(ActionEvent event) {
		String command = ((MenuItem)event.getSource()).getText();
		System.out.println(command);
		switch (command) {
			case "New Game":
				this.createNewGame();
				break;
			case "Main Menu":
				this.backToStart();
				break;
			case "Exit":
				System.exit(0);
		}
	}
}
