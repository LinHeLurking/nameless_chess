package online.ruin_of_future.nameless_chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.*;
import javafx.util.Pair;

import java.util.ArrayList;


public class BoardPanel extends GridPane implements EventHandler<ActionEvent> {
	
	private View view;
	private ArrayList<Pair<Integer, Integer>> board = new ArrayList<>();
	private ArrayList<Pair<Integer, Integer>> origin;
	
	BoardPanel(View view) {
		this.view = view;
		this.setBoard();
		this.setId("board");
		this.init();
	}
	
	private void setBoard() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if ((i == 0 || i == 10) && (j >= 2 && j <= 8 && j % 2 == 0)) board.add(new Pair<>(i, j));
				if ((i == 1 || i == 9) && (j == 4 || j == 5 || j == 6)) board.add(new Pair<>(i, j));
				if ((i == 2 || i == 8) && (j == 0 || j == 10)) board.add(new Pair<>(i, j));
				if ((i == 4 || i == 6) && (j <= 1 || j >= 9)) board.add(new Pair<>(i, j));
				if (i == 5 && (j == 1 || j == 9)) board.add(new Pair<>(i, j));
			}
		}
		for (int i = 2; i <= 8; i++) {
			for (int j = 2; j <= 8; j++) {
				board.add(new Pair<>(i, j));
			}
		}
	}
	
	private void setOrigin() {
	
	}
	
	private void init() {
		
		
		this.setVgap(0);
		this.setHgap(0);
		
		// 0 2  0 4  1 4  1 5  1 6  0 6  0 8
		Button[][] button = new Button[11][11];
		int col = 1 , row = 1;
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				Pair<Integer, Integer> p = new Pair<>(i, j);
				button[i][j] = new Button("" + i + " " + j);
				button[i][j].setMinWidth(45);
				button[i][j].setMinHeight(45);
				if (!board.contains(p)) {
					button[i][j].setStyle("-fx-background-color: transparent");
				}
				
				
				
				
				//button[i][j].setOnAction(new ChessBoardPanelEventHandler(this.view, board, turn_label));
				/*
				if(count%2 == 0) {
					button[i][j].setStyle(colour_white);
				}else {
					button[i][j].setStyle(colour_gray);
				}*/
				this.add(button[i][j], col, row);
				col++;
			}
			col = 1;
			row++;
		}
	}
	
	@Override
	public void handle(ActionEvent actionEvent) {
	
	}
}
