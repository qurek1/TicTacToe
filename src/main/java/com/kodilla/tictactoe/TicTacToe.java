package com.kodilla.tictactoe;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Tic Tac Toe");
        Board board = new Board(3);
        ScoreBoard scoreBoard = ScoreBoard.INSTANCE;
        Buttons buttons = new Buttons();

        VBox vBox = new VBox(10, board, scoreBoard, buttons);
        vBox.setPrefHeight(board.getPrefHeight() + buttons.getPrefHeight() + 80);

        primaryStage.setScene(new Scene(vBox));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
