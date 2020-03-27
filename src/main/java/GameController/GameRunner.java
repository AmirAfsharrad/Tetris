package GameController;

import GameElements.Constants;

import javax.swing.*;

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
        try {
            GameState.getGameState().loadGame("auto saved.ser");
            GameState.getGameState().reset();
            Canvas canvas = new Canvas();
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(canvas);
            frame.setSize(Constants.maxWidth, Constants.maxHeight);
            frame.setTitle("Tetris");
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setVisible(true);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    GameState.getGameState().saveGame("auto saved.ser");
                }
            }, "Shutdown-thread"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
