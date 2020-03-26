package UserInterface;
import GameController.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
    private GameState gameState = GameState.getGameState();

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (gameState.getGameBoard().canGoLeft()) {
                    gameState.getGameBoard().getCurrentTetromino().moveLeft();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (gameState.getGameBoard().canGoRight()) {
                    gameState.getGameBoard().getCurrentTetromino().moveRight();
                }
                break;
            case KeyEvent.VK_UP:
                if (gameState.getGameBoard().canRotate()) {
                    gameState.getGameBoard().getCurrentTetromino().rotate();
                }
                break;
            case KeyEvent.VK_DOWN:
                while (gameState.getGameBoard().canGoDown()) {
                    gameState.getGameBoard().getCurrentTetromino().moveDown();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
