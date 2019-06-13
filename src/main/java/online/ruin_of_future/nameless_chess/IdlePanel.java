package online.ruin_of_future.nameless_chess;

import javafx.scene.layout.GridPane;

public class IdlePanel {
    private View view;
    private GridPane panel;

    IdlePanel(View view) {
        this.view = view;
        this.init();
    }

    // For board.fxml loading controller
    public IdlePanel() {

    }

    public void setView(View view) {
        this.view = view;
    }

    private void init() {

    }

    GridPane getPanel() {
        return this.panel;
    }
}
