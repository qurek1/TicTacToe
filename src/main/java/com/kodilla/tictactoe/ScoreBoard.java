package com.kodilla.tictactoe;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreBoard extends Pane {

    final Label scoreDisplay = new Label("Current result (you : me) - " + 0 + " : " + 0);

    public ScoreBoard() {

        setPrefSize(600, 20);

        scoreDisplay.setMinSize(560, 20);
        scoreDisplay.setMaxSize(560, 20);
        scoreDisplay.setLayoutX(20);
        scoreDisplay.setLayoutY(0);
        scoreDisplay.setFont(new Font(15));
        scoreDisplay.setAlignment(Pos.TOP_CENTER);
        scoreDisplay.setStyle("-fx-font-weight: bold");

        getChildren().add(scoreDisplay);

    }
}
