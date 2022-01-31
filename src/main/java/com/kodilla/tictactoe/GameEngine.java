package com.kodilla.tictactoe;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class GameEngine {

    public static final GameEngine INSTANCE = new GameEngine();
    public static final int NO_WINNER = -10;
    private int noOfCells;
    private int squareOfNoOfCells;
    private boolean gameOver = false;
    private Tile[][] board = new Tile[5][5];
    private final int[][] boardMirror = new int[5][5];
    private int bestMoveX, bestMoveY;
    private int playerNoOfWins, machineNoOfWins;
    private File savedGameState = new File("gamestate.dat");
    private Map<Integer, Integer> boardMap = new HashMap<>();


    private GameEngine() {
    }

    public void setNoOfCells(int noOfCells) {
        this.noOfCells = noOfCells;
        this.squareOfNoOfCells = noOfCells * noOfCells;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public void startNewGame() {
        for (int i = 0; i < noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++) {
                board[i][j].setTileDescription("");
                boardMirror[i][j] = 0;
            }
        }
        gameOver = false;
    }

    public void storeGameState() {

        for(int i = 0; i< noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++){
                boardMap.put(10 * i + j, gameOver ? 0 : boardMirror[i][j]);
            }
        }

        try {
            ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(savedGameState));
            outStream.writeObject(boardMap);
            outStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readGameState() {
        try {
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(savedGameState));
            Object readMap = inStream.readObject();
            if (readMap instanceof HashMap) {
                boardMap.putAll((HashMap) readMap);
            }
            inStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++) {
                boardMirror[i][j] = boardMap.get(10 * i + j);
                board[i][j].setTileDescription(boardMirror[i][j] == 1 ? "X" : boardMirror[i][j] == -1 ? "O" : "");
            }
        }
    }

    public void initBoardMirror() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardMirror[i][j] = 0;
            }
        }
    }

    private int checkResult() {
        int result = checkRowAndColumn();
        if (result == NO_WINNER) {
            result = checkDiagonals();
        }
        if ((result == NO_WINNER) && (countFreeCells() == 0)) {
            return 0;
        }
        return result;
    }

    private int checkRowAndColumn() {
        int sumOfRow;
        int sumOfColumn;

        for (int i = 0; i < noOfCells; i++) {
            sumOfRow = 0;
            sumOfColumn = 0;
            for (int j = 0; j < noOfCells; j++) {
                sumOfRow += boardMirror[i][j];
                sumOfColumn += boardMirror[j][i];
            }
            if (abs(sumOfRow) == noOfCells || abs(sumOfColumn) == noOfCells) {
                return (int) signum(sumOfRow + sumOfColumn);
            }
        }
        return NO_WINNER;
    }

    private int checkDiagonals() {
        int sumOfDiagonal1 = 0;
        int sumOfDiagonal2 = 0;

        for (int i = 0; i < noOfCells; i++) {
            sumOfDiagonal1 += boardMirror[i][i];
            sumOfDiagonal2 += boardMirror[i][noOfCells - i - 1];
        }

        if (abs(sumOfDiagonal1) == noOfCells || abs(sumOfDiagonal2) == noOfCells) {
            return (int) signum(sumOfDiagonal1 + sumOfDiagonal2);
        }
        return NO_WINNER;
    }

    private int countFreeCells() {
        int result = 0;
        for (int i = 0; i < noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++) {
                result += (boardMirror[i][j] == 0 ? 1 : 0);
            }
        }
        return result;
    }

    private void updateScoreboardResults(int result) {
        playerNoOfWins += (result == 0 || result == 1) ? 1 : 0;
        machineNoOfWins += (result == 0 || result == -1) ? 1 : 0;
    }

    public void click(MouseEvent event, Tile tile) {

        int x = tile.getX();
        int y = tile.getY();

        if (event.getButton() == MouseButton.PRIMARY && !gameOver && boardMirror[x][y] == 0) {
            tile.setTileDescription("X");
            boardMirror[x][y] = 1;
            int result = checkResult();
            updateScoreboardResults(result);
            gameOver = (result != NO_WINNER || (countFreeCells() == 0));
            if (!gameOver) makeMoveForMachine();
        }
    }

    private void makeMoveForMachine() {
        int bestScore = squareOfNoOfCells;

        for (int i = 0; i < noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++) {
                if (boardMirror[i][j] == 0) {
                    boardMirror[i][j] = -1;
                    int score = miniMax(boardMirror, true);
                    boardMirror[i][j] = 0;
                    if (score < bestScore) {
                        bestScore = score;
                        bestMoveX = i;
                        bestMoveY = j;
                    }
                }
            }
        }
        board[bestMoveX][bestMoveY].setTileDescription("O");
        boardMirror[bestMoveX][bestMoveY] = -1;
        int result = checkResult();
        updateScoreboardResults(result);
        gameOver = (result != NO_WINNER || countFreeCells() == 0);
    }

    private int miniMax(int[][] board, boolean looksForMax) {

        boolean nextMove = !looksForMax;
        int result = checkResult();
        if (result != NO_WINNER) {
            return result * (squareOfNoOfCells - countFreeCells());
        }

        int bestScore = (looksForMax ? -1 : 1) * squareOfNoOfCells;
        for (int i = 0; i < noOfCells; i++) {
            for (int j = 0; j < noOfCells; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = looksForMax ? 1 : -1;
                    int score = miniMax(board, nextMove);
                    board[i][j] = 0;
                    bestScore = looksForMax ? Math.max(score, bestScore) : Math.min(score, bestScore);
                }
            }
        }
        return bestScore;
    }

}