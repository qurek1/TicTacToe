package com.kodilla.tictactoe;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Buttons extends Pane {

    final Button newGame = new Button("Start new game");
    final Button continueLastGame = new Button("Continue last game");
    final Button exit = new Button("Exit game");

    public Buttons() {

        setPrefSize(600, 30);

        newGame.setMaxSize(180, 40);
        newGame.setMinSize(180, 40);
        newGame.setLayoutX(10);
        newGame.setLayoutY(10);
        newGame.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.startNewGame();
        });

        continueLastGame.setMaxSize(180, 40);
        continueLastGame.setMinSize(180, 40);
        continueLastGame.setLayoutX(210);
        continueLastGame.setLayoutY(10);
        continueLastGame.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.readGameState();
        });

        exit.setMinSize(180, 40);
        exit.setMaxSize(180, 40);
        exit.setLayoutX(410);
        exit.setLayoutY(10);
        exit.setOnAction(actionEvent -> {
            GameEngine.INSTANCE.storeGameState();
            Platform.exit();
            System.exit(0);
        });

        getChildren().add(newGame);
        getChildren().add(exit);
        getChildren().add(continueLastGame);
    }
}
