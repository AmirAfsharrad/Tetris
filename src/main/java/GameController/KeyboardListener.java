package GameController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (GameState.getGameState().isGamePaused()) {
            if (keyEvent.getKeyCode() == keyEvent.VK_P) {
                GameState.getGameState().setGamePaused(false);
            }
        } else {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (GameState.getGameState().getGameBoard().canGoLeft()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveLeft();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (GameState.getGameState().getGameBoard().canGoRight()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveRight();
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (GameState.getGameState().getGameBoard().canRotate()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().rotate();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    for (int i = 0; i < 2; i++) {
                        if (GameState.getGameState().getGameBoard().canGoDown()) {
                            GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
                        }
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    while (GameState.getGameState().getGameBoard().canGoDown()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
                    }
                    break;
                case KeyEvent.VK_L:
                    GameState.getGameState().loadGame("user saved.ser");
                    break;
                case KeyEvent.VK_S:
                    GameState.getGameState().saveGame("user saved.ser");
                    break;
                case KeyEvent.VK_R:
                    GameState.getGameState().getGameBoard().repent();
                    break;
                case KeyEvent.VK_P:
                    GameState.getGameState().setGamePaused(!GameState.getGameState().isGamePaused());
                    break;
                case KeyEvent.VK_N:
                    GameState.getGameState().reset();
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
