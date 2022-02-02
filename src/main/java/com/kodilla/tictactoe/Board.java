package com.kodilla.tictactoe;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Board extends Pane {

    private final int noOfCells;
    public static final int BOARD_SIZE = 600;

    public Board(int numberOfCells) {
        this.noOfCells = numberOfCells;

        setPrefSize(BOARD_SIZE + noOfCells, BOARD_SIZE + noOfCells * 5);
        Tile[][] board = new Tile[noOfCells][noOfCells];

        for (int y = 0; y < noOfCells; y++) {
            for (int x = 0; x < noOfCells; x++) {
                Tile tile = new Tile(x, y, noOfCells);
                tile.setLayoutX(x * BOARD_SIZE / noOfCells + 10);
                tile.setLayoutY(y * BOARD_SIZE / noOfCells + 10);

                getChildren().add(tile);

                board[x][y] = tile;
            }
        }
        GameEngine.INSTANCE.setNoOfCells(noOfCells);
        GameEngine.INSTANCE.setBoard(board);
        GameEngine.INSTANCE.initBoardMirror();
    }
}