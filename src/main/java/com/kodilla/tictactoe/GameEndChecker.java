package com.kodilla.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class GameEndChecker {

/*    private Tile[] tiles;
    public static List<GameEndChecker> tileSets = new ArrayList<>();

    public GameEndChecker(Tile... tiles) {
        this.tiles = tiles;
    }

    public boolean isGameComplete() {
        if (tiles[0].getTileDescription().isEmpty()) {
            return false;
        }
        boolean result = true;
        for (int i = 1; i < Board.NO_OF_CELLS; i++) {
            result = result && tiles[0].getTileDescription().equals(tiles[i].getTileDescription());
        }
        return result;
    }

/*    public static void checkState() {
        for (GameEndChecker tileSet : tileSets) {
            if (tileSet.isGameComplete()) {
                GameEngine.setGameOver(true);
                break;
            }
        }
    }*/
}
