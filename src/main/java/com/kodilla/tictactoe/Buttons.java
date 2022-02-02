package com.kodilla.tictactoe;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buttons extends Pane {

    static Buttons INSTANCE = new Buttons();

    private final Button left = new Button("Start new game");
    private final Button middle = new Button("Continue last game");
    private final Button right = new Button("Exit game");

    private final static int BUTTON_WIDTH = 180;
    private final static int BUTTON_HEIGHT = 40;

    private boolean clearScoreboard = false;

    private Buttons() {

        setPrefSize(600, 30);

        setButtonSize(left);
        left.setLayoutX(10);
        left.setLayoutY(10);
        left.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.startNewGame();
            switchMiddleButtonFunction();
        });

        setButtonSize(middle);
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

        setButtonSize(right);
        right.setLayoutX(410);
        right.setLayoutY(10);
        right.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.storeGameState();
            Platform.exit();
        });

        getChildren().add(left);
        getChildren().add(right);
        getChildren().add(middle);
    }

    public void switchMiddleButtonFunction() {
        clearScoreboard = true;
        middle.setText("Clear scoreboard");
    }

    private void setButtonSize(Button button) {
        button.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
