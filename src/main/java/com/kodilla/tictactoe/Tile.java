package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    private final int x, y;
    private Text tileDescription = new Text();

    public Tile(int x, int y, int noOfCells) {
        this.x = x;
        this.y = y;
        Rectangle border = new Rectangle((Board.BOARD_SIZE - 15 * (noOfCells + 1)) / noOfCells,
                (Board.BOARD_SIZE - 15 * (noOfCells + 1)) / noOfCells);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        tileDescription.setFont(Font.font(96));
        setAlignment(Pos.CENTER);
        getChildren().addAll(border, tileDescription);

        setOnMouseClicked(event -> GameEngine.INSTANCE.click(event, this));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTileDescription(String text) {
        this.tileDescription.setText(text);
    }
}