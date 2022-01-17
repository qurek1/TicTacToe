package com.kodilla.tictactoe;

import static com.kodilla.tictactoe.Constans.NO_OF_CELLS;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameEngine {

    public static final GameEngine INSTANCE = new GameEngine();

    private boolean xTurn = true;
    private boolean gameOver = false;
    private int numberOfMoves = 0;
    private Tile[][] board = new Tile[NO_OF_CELLS][NO_OF_CELLS];

    private GameEngine() {
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public boolean isxTurn() {
        return xTurn;
    }

    public void setxTurn(boolean xTurn) {
        this.xTurn = xTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int checkResult(int x, int y) {
        int sumOfCells = 0;

        for (int i = 0; i < NO_OF_CELLS; i++) {
            if (getTileValue(x, i).equals("X")) {
                sumOfCells += 1;
            } else if (getTileValue(x, i).equals("O")) {
                sumOfCells -= 1;
            }
        }
        if (sumOfCells == NO_OF_CELLS) {
            gameOver = true;
            return 1;
        } else if (sumOfCells == -NO_OF_CELLS) {
            gameOver = true;
            return -1;
        }

        sumOfCells = 0;
        for (int i = 0; i < NO_OF_CELLS; i++) {
            if (getTileValue(i, y).equals("X")) {
                sumOfCells += 1;
            } else if (getTileValue(i, y).equals("O")) {
                sumOfCells -= 1;
            }
        }
        if (sumOfCells == NO_OF_CELLS) {
            gameOver = true;
            return 1;
        } else if (sumOfCells == -NO_OF_CELLS) {
            gameOver = true;
            return -1;
        }

        if (x == y) {
            sumOfCells = 0;
            for (int i = 0; i < NO_OF_CELLS; i++) {
                if (getTileValue(i, i).equals("X")) {
                    sumOfCells += 1;
                } else if (getTileValue(i, i).equals("O")) {
                    sumOfCells -= 1;
                }
            }
            if (sumOfCells == NO_OF_CELLS) {
                gameOver = true;
                return 1;
            } else if (sumOfCells == -NO_OF_CELLS) {
                gameOver = true;
                return -1;
            }

            sumOfCells = 0;
            for (int i = 0; i < NO_OF_CELLS; i++) {
                if (getTileValue(i, NO_OF_CELLS - i - 1).equals("X")) {
                    sumOfCells += 1;
                } else if (getTileValue(i, NO_OF_CELLS - i - 1).equals("O")) {
                    sumOfCells -= 1;
                }
            }
            if (sumOfCells == NO_OF_CELLS) {
                gameOver = true;
                return 1;
            } else if (sumOfCells == -NO_OF_CELLS) {
                gameOver = true;
                return -1;
            }
        }
        if (numberOfMoves == NO_OF_CELLS * NO_OF_CELLS) {
            gameOver = true;
        }

        return 0;
    }

    private String getTileValue(int x, int y) {
        return board[x][y].getTileDescription();
    }

    public void click(MouseEvent event, Tile tile) {

        int x = tile.getX();
        int y = tile.getY();
        System.out.println("Clicked x=%s y=%s");

        if (isGameOver()) {
            return;
        }

        if (event.getButton() == MouseButton.PRIMARY) {
            if (!isxTurn()) {
                return;
            }

            setNumberOfMoves(getNumberOfMoves() + 1);
            tile.setTileDescription("X");
        } else if (event.getButton() == MouseButton.SECONDARY) {
            if (isxTurn()) {
                return;
            }

            setNumberOfMoves(getNumberOfMoves() + 1);
            tile.setTileDescription("O");
        }
        setxTurn(!isxTurn());
        System.out.println("x: " + x + ",y :" + y);
        System.out.println(checkResult(x, y));
    }
}
