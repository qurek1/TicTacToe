package com.kodilla.tictactoe;

public class GameEngine {

    private static boolean xTurn = true;
    private static boolean gameOver = false;
    private static int numberOfMoves = 0;

    public static boolean isxTurn() {
        return xTurn;
    }

    public static void setxTurn(boolean xTurn) {
        GameEngine.xTurn = xTurn;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static int getNumberOfMoves() {
        return numberOfMoves;
    }

    public static void setNumberOfMoves(int numberOfMoves) {
        GameEngine.numberOfMoves = numberOfMoves;
    }

    public static int checkResult(int x, int y) {
        int sumOfCells = 0;

        for (int i = 0; i < Board.NO_OF_CELLS; i++) {
            if (Board.getTileValue(x, i).equals("X")) {
                sumOfCells += 1;
            } else if (Board.getTileValue(x, i).equals("O")) {
                sumOfCells -= 1;
            }
        }
        if (sumOfCells == Board.NO_OF_CELLS) {
            gameOver =true;
            return 1;
        } else if (sumOfCells == -Board.NO_OF_CELLS) {
            gameOver =true;
            return -1;
        }

        sumOfCells = 0;
        for (int i = 0; i < Board.NO_OF_CELLS; i++) {
            if (Board.getTileValue(i, y).equals("X")) {
                sumOfCells += 1;
            } else if (Board.getTileValue(i, y).equals("O")) {
                sumOfCells -= 1;
            }
        }
        if (sumOfCells == Board.NO_OF_CELLS) {
            gameOver =true;
            return 1;
        } else if (sumOfCells == -Board.NO_OF_CELLS) {
            gameOver =true;
            return -1;
        }

        if (x == y) {
            sumOfCells = 0;
            for (int i = 0; i < Board.NO_OF_CELLS; i++) {
                if (Board.getTileValue(i, i).equals("X")) {
                    sumOfCells += 1;
                } else if (Board.getTileValue(i, i).equals("O")) {
                    sumOfCells -= 1;
                }
            }
            if (sumOfCells == Board.NO_OF_CELLS) {
                gameOver =true;
                return 1;
            } else if (sumOfCells == -Board.NO_OF_CELLS) {
                gameOver =true;
                return -1;
            }

            sumOfCells = 0;
            for (int i = 0; i < Board.NO_OF_CELLS; i++) {
                if (Board.getTileValue(i, Board.NO_OF_CELLS - i - 1).equals("X")) {
                    sumOfCells += 1;
                } else if (Board.getTileValue(i, Board.NO_OF_CELLS - i - 1).equals("O")) {
                    sumOfCells -= 1;
                }
            }
            if (sumOfCells == Board.NO_OF_CELLS) {
                gameOver =true;
                return 1;
            } else if (sumOfCells == -Board.NO_OF_CELLS) {
                gameOver =true;
                return -1;
            }
        }
        if (numberOfMoves == Board.NO_OF_CELLS * Board.NO_OF_CELLS) {
            gameOver = true;
        }

        return 0;
    }
}
