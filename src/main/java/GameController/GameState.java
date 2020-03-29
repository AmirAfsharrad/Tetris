package GameController;

import GameElements.GameBoard;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameState implements Serializable {
    private static GameState gameState;
    private GameBoard gameBoard;
    private int countRemovedRows = 0;
    private boolean gamePaused;
    private boolean gameOver;
    private int score = 0;
    private static final long serialVersionUID = 1L;
    private String path = "saved data/";
    private ArrayList<Integer> topTenScores;

    private GameState() {
        gameBoard = new GameBoard();
        countRemovedRows = 0;
        gamePaused = false;
        gameOver = false;
        topTenScores = new ArrayList<>();
        topTenScores.add(0);
    }

    public void loadGame(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path + fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            gameState = (GameState) objectInputStream.readObject();
            objectInputStream.close();
            loadTopTenScores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTopTenScores() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path + "high scores.ser");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            gameState.topTenScores = (ArrayList<Integer>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGame(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path + fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameState);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveTopTenScores() {
        try {
            FileOutputStream fileOut = new FileOutputStream(path + "high scores.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gameState.topTenScores);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Integer> getTopTenScores() {
        return topTenScores;
    }

    public void updateTopTenScores() {
        if (topTenScores.size() < 10) {
            topTenScores.add(score);
            return;
        }
        Collections.sort(topTenScores);
        if (score > topTenScores.get(0)) {
            topTenScores.set(0, score);
            Collections.sort(topTenScores);
        }
    }

    public void reset() {
        ArrayList<Integer> tempTopTenScores = topTenScores;
        gameState = new GameState();
        gameState.topTenScores = tempTopTenScores;
    }
}
