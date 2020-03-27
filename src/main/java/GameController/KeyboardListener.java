package GameController;
import GameController.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
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
                while (GameState.getGameState().getGameBoard().canGoDown()) {
                    GameState.getGameState().getGameBoard().getCurrentTetromino().moveDown();
                }
                break;
            case KeyEvent.VK_L:
                GameState.getGameState().load();
                break;
            case KeyEvent.VK_S:
                try {
                    FileOutputStream fileOut = new FileOutputStream("saved data/gameState");
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(GameState.getGameState());
                    objectOut.close();
                    System.out.println("Game Object Saved");
                    System.exit(0);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
