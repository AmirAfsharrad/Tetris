import GameElements.Constants;
import GameElements.GameState;
import UserInterface.Canvas;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameState gameState = GameState.getGameState();
        Canvas canvas = new Canvas();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        frame.setSize(Constants.maxWidth, Constants.maxHeight);
        frame.setTitle("Tetris");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
