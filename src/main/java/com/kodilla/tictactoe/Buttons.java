package com.kodilla.tictactoe;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buttons extends Pane {

    static Buttons INSTANCE = new Buttons();

    final Button left = new Button("Start new game");
    final Button middle = new Button("Continue last game");
    final Button right = new Button("Exit game");
    boolean clearScoreboard = false;

    private Buttons() {

        setPrefSize(600, 30);

        left.setMaxSize(180, 40);
        left.setMinSize(180, 40);
        left.setLayoutX(10);
        left.setLayoutY(10);
        left.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.startNewGame();
            switchMiddleButtonFunction();
        });

        middle.setMaxSize(180, 40);
        middle.setMinSize(180, 40);
        middle.setLayoutX(210);
        middle.setLayoutY(10);
        middle.setOnAction(actionEvent -> {
            if (clearScoreboard) {
                ScoreBoard.INSTANCE.clearScoreBoard();
            } else {
                GameEngine.INSTANCE.readGameState();
                switchMiddleButtonFunction();
            }
        });

        right.setMinSize(180, 40);
        right.setMaxSize(180, 40);
        right.setLayoutX(410);
        right.setLayoutY(10);
        right.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.storeGameState();
            Platform.exit();
            System.exit(0);
        });

        getChildren().add(left);
        getChildren().add(right);
        getChildren().add(middle);
    }

    public void switchMiddleButtonFunction() {
        clearScoreboard = true;
        middle.setText("Clear scoreboard");
    }
}
