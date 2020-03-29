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
            if (keyEvent.getKeyCode() == KeyEvent.VK_P) {
                AudioPlayer.play("Audio/GameResume.wav");
                GameState.getGameState().setGamePaused(false);
            }
        } else {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (GameState.getGameState().getGameBoard().canGoLeft()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveLeft();
                        AudioPlayer.play("Audio/MoveSuccess.wav");
                    } else {
                        AudioPlayer.play("Audio/MoveFail.wav");
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (GameState.getGameState().getGameBoard().canGoRight()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveRight();
                        AudioPlayer.play("Audio/MoveSuccess.wav");
                    } else {
                        AudioPlayer.play("Audio/MoveFail.wav");
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (GameState.getGameState().getGameBoard().canRotate()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().rotate();
                        AudioPlayer.play("Audio/RotateSuccess.wav");
                    } else {
                        AudioPlayer.play("Audio/RotateFail.wav");
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    for (int i = 0; i < 2; i++) {
                        if (GameState.getGameState().getGameBoard().canGoDown()) {
                            GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
                        }
                    }
                    AudioPlayer.play("Audio/MoveSuccess.wav");
                    break;
                case KeyEvent.VK_SPACE:
                    while (GameState.getGameState().getGameBoard().canGoDown()) {
                        GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
                    }
                    AudioPlayer.play("Audio/FreeFall.wav");
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
                    AudioPlayer.play("Audio/GamePause.wav");
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
