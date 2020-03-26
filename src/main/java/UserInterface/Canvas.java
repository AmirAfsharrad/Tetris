package UserInterface;

import GameElements.Constants;
import GameController.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JComponent {
    private Drawer drawer;
    private GameState gameState;

    public Canvas() {
        gameState = GameState.getGameState();
        addKeyListener(new KeyboardListener());
        Timer timer = new Timer();
        TimerTask timerTask = new Ticker();
        timer.scheduleAtFixedRate(timerTask, 1000, Constants.tickTime);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (drawer == null) drawer = new Drawer(g2d);
        requestFocus();
        drawer.setGraphics2D(g2d);
        if (gameState.isGameOver()) {
            drawer.drawGameOver(g2d);
        } else {
            drawer.drawGameState(gameState);
        }
    }

    private void update() {
        if (gameState.getGameBoard().canGoDown()) {
            gameState.getGameBoard().getCurrentTetromino().moveDown();
        } else {
            gameState.getGameBoard().updateCurrentTetromino();
            gameState.getGameBoard().removeRowsIfPossible();
            if (!gameState.getGameBoard().canExist(gameState.getGameBoard().getCurrentTetromino())){
                gameState.setGameOver(true);
            }
        }
    }


    private class Ticker extends TimerTask {
        @Override
        public void run() {
            if (!gameState.isGameOver()) update();
            repaint();
        }
    }
}
