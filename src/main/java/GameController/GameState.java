package GameController;

import GameElements.GameBoard;

public class GameState {
    private static GameState gameState;
    private GameBoard gameBoard;
    private int countRemovedRows = 0;
    private boolean gamePaused;
    private boolean gameOver;

    private GameState(){
        gameBoard = new GameBoard();
        countRemovedRows = 0;
        gamePaused = false;
        gameOver = false;
    }

    public static GameState getGameState() {
        if (gameState == null) gameState = new GameState();
        return gameState;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getCountRemovedRows() {
        return countRemovedRows;
    }

    public void setCountRemovedRows(int countRemovedRows) {
        this.countRemovedRows = countRemovedRows;
    }
}
