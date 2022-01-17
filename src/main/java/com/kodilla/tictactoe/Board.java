package com.kodilla.tictactoe;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Board {

    public static final int NO_OF_CELLS = 3;
    public static final int BOARD_SIZE = 600;
    private static Tile[][] board = new Tile[NO_OF_CELLS][NO_OF_CELLS];

    public static Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(BOARD_SIZE, BOARD_SIZE);

        for (int y = 0; y < NO_OF_CELLS; y++) {
            for (int x = 0; x < NO_OF_CELLS; x++) {
                Tile tile = new Tile(x, y);
                tile.setTranslateX(x * BOARD_SIZE / NO_OF_CELLS);
                tile.setTranslateY(y * BOARD_SIZE / NO_OF_CELLS);

                root.getChildren().add(tile);

                board[x][y] = tile;
            }
        }

        return root;
    }

    public static String getTileValue(int x, int y) {
        return board[x][y].getTileDescription();
    }
}
