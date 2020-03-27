package GameController;

import GameElements.GameBoard;

import java.io.*;

public class GameState implements Serializable {
    private static GameState gameState;
    private GameBoard gameBoard;
    private int countRemovedRows = 0;
    private boolean gamePaused;
    private boolean gameOver;
    private int points = 0;
    private static final long serialVersionUID = 1L;

    private GameState() {
        gameBoard = new GameBoard();
        countRemovedRows = 0;
        gamePaused = false;
        gameOver = false;
    }

    public void load() {
        try {
            FileInputStream fileInputStream = new FileInputStream("saved data/gameState");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            gameState = (GameState) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Loaded Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
