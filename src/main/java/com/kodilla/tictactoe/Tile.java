package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private Text tileDescription = new Text();
    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        Rectangle border = new Rectangle(Board.BOARD_SIZE / Board.NO_OF_CELLS, Board.BOARD_SIZE / Board.NO_OF_CELLS);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        tileDescription.setFont(Font.font(96));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, tileDescription);

        setOnMouseClicked(event -> {

            if (GameEngine.isGameOver()) {
                return;
            }

            if (event.getButton() == MouseButton.PRIMARY) {
                if (!GameEngine.isxTurn()) {
                    return;
                }

                GameEngine.setNumberOfMoves(GameEngine.getNumberOfMoves() + 1);
                tileDescription.setText("X");
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (GameEngine.isxTurn()) {
                    return;
                }

                GameEngine.setNumberOfMoves(GameEngine.getNumberOfMoves() + 1);
                tileDescription.setText("O");
            }
            GameEngine.setxTurn(!GameEngine.isxTurn());
            System.out.println("x: " + x + ",y :" + y);
            System.out.println(GameEngine.checkResult(x, y));
        });
    }

    public String getTileDescription() {
        return tileDescription.getText();
    }

}
