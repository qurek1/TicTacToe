package com.kodilla.tictactoe;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreBoard extends Pane {

    static ScoreBoard INSTANCE = new ScoreBoard();
    private int you = 0;
    private int me = 0;
    final Label scoreDisplay = new Label();

    private ScoreBoard() {
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

    void addCompWon(){
        you++;
        updateScore();
    }

    void addPlayerWon(){
        me++;
        updateScore();
    }

    void updateScore(){
        String text = String.format("Current result (you : me) - %s : %s", you, me);
        Label currLabl = (Label) getChildren().stream().findAny().get();
        currLabl.setText(text);
    }
}
