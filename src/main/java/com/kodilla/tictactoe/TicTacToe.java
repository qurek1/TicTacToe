package com.kodilla.tictactoe;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(Board.createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
