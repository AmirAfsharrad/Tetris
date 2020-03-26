package GameController;

import GameElements.Constants;
import UserInterface.Canvas;

import javax.swing.*;
import java.awt.*;
import java.lang.management.GarbageCollectorMXBean;

public class GameRunner {
    public static GameRunner gameRunner;
    private JFrame frame;

    GameRunner() {
    }

    public static GameRunner getGameRunner() {
        if (gameRunner == null) {
            gameRunner = new GameRunner();
        }
        return gameRunner;
    }

    public int getFrameHeight() {
        return frame.getSize().height;
    }

    public int getFrameWidth() {
        return frame.getSize().width;
    }

    public void runGame() {
        GameState gameState = GameState.getGameState();
        Canvas canvas = new Canvas();
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setSize(Constants.maxWidth, Constants.maxHeight);
        frame.setTitle("Tetris");
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
