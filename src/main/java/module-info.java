module nameless.chess {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens online.ruin_of_future.nameless_chess to javafx.fxml;
	exports online.ruin_of_future.nameless_chess;
}