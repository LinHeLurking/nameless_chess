package online.ruin_of_future.nameless_chess;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.util.concurrent.*;


public class StartPanel extends StackPane implements EventHandler<ActionEvent> {

    private View view;

    private Future debugLoop(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        return threadPool.submit(new Callable(){
            @Override
            public Integer call() throws Exception{
                view.changeToDebug();
                return 0;
            }
        });
    }

    StartPanel(View view) {
        this.view = view;
        this.init();
    }

    private void init() {
        Canvas canvas = new Canvas(550, 550);
        this.getChildren().add(canvas);

        Button start_button = new Button("Start");
        start_button.setId("start");
        start_button.setMinWidth(100);
        start_button.setPrefHeight(50);
        start_button.setOnAction(this);

        this.getChildren().add(start_button);
        setAlignment(start_button, Pos.BOTTOM_CENTER);


        Button debug_button = new Button("Debug");
        debug_button.setId("debug");
        debug_button.setMinWidth(100);
        debug_button.setPrefHeight(50);
        debug_button.setOnAction(this);

        this.getChildren().add(debug_button);
        setAlignment(debug_button, Pos.BOTTOM_RIGHT);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (((Button) actionEvent.getSource()).getText() == "Start") {
            this.view.changeToBoard();
        }else if(((Button) actionEvent.getSource()).getText() == "Debug"){
            System.out.println("RUA!!!");
            this.debugLoop();
        }
    }
}
