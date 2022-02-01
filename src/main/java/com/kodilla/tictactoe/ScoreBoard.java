package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ScoreBoard extends Pane {

    static ScoreBoard INSTANCE = new ScoreBoard();
    private int playerScore = 0;
    private int machineScore = 0;
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

    public void calculateScores(int result) {
        machineScore += result <= 0 ? 1 : 0;
        playerScore += result >= 0 ? 1 : 0;
        updateScoreBoard();
    }

    public void updateScoreBoard(){
        String text = String.format("Current result (you : me) - %s : %s", playerScore, machineScore);
        Label currLabel = (Label) getChildren().stream().findAny().get();
        currLabel.setText(text);
    }

    public void clearScoreBoard() {
        playerScore = 0;
        machineScore = 0;
        updateScoreBoard();
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getMachineScore() {
        return machineScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setMachineScore(int machineScore) {
        this.machineScore = machineScore;
    }
}
