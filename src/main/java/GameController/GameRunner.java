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
            GameState.getGameState().loadTopTenScores();
            Canvas canvas = new Canvas();
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(canvas);
            frame.setSize(Constants.initialWidth, Constants.initialHeight);
            frame.setTitle("Tetris");
            frame.setLocationRelativeTo(null);
            frame.setResizable(true);
            frame.setVisible(true);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    GameState.getGameState().saveTopTenScores();
                }
            }, "Shutdown-thread"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
