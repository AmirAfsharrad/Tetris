package GameController;

import GameElements.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Canvas extends JComponent {
    private Drawer drawer;

    public Canvas() {
        addKeyListener(new KeyboardListener());
        setIgnoreRepaint(true);
        Timer timer = new Timer();
        Timer timer2 = new Timer();
        TimerTask RenderTimerTask = new RenderTimer();
        TimerTask UpdateTimerTask = new UpdateTimer();
        timer.scheduleAtFixedRate(RenderTimerTask, 0, Constants.RenderTickTime);
        timer2.scheduleAtFixedRate(UpdateTimerTask, 0, Constants.UpdateTickTime);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (drawer == null) drawer = new Drawer(g2d);
        requestFocus();
        drawer.setGraphics2D(g2d);
        if (GameState.getGameState().isGameOver()) {
            drawer.drawGameState(GameState.getGameState());
            drawer.drawGameOver(g2d);
        } else {
            drawer.drawGameState(GameState.getGameState());
        }
    }

    private void update() {
        if (!GameState.getGameState().isGamePaused()) {
            if (GameState.getGameState().getGameBoard().canGoDown()) {
                GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
            } else {
                GameState.getGameState().setScore(GameState.getGameState().getScore() + 1);
                GameState.getGameState().getGameBoard().updateCurrentTetromino();
                GameState.getGameState().getGameBoard().removeRowsIfPossible();
            }
        }
    }

    private class RenderTimer extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }

    private class UpdateTimer extends TimerTask {
        @Override
        public void run() {
            if (!GameState.getGameState().isGameOver()) update();
            repaint();
        }
    }
}
