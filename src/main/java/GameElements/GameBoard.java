package GameElements;

import GameController.AudioPlayer;
import GameController.GameState;
import GameElements.Tetrominoes.Tetromino;

import java.io.Serializable;

public class GameBoard implements Serializable {
    private int width = Constants.boardWidth;
    private int height = Constants.boardHeight;
    private GameCell[][] board;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;
    private Vector2D newBlockInitPosition;
    private Tetromino currentTetrominoCloned;

    public GameBoard() {
        board = new GameCell[width][height + 2];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height + 2; j++) {
                board[i][j] = new GameCell();
            }
        }
        newBlockInitPosition = new Vector2D((int) Math.ceil(width / 2.0) - 1, height);
        currentTetromino = Tetromino.getRandomTetromino();
        currentTetromino.setPosition(getInitialPosition(currentTetromino));
        nextTetromino = Tetromino.getRandomTetromino();
        nextTetromino.setPosition(getInitialPosition(nextTetromino));
    }

    private Vector2D getInitialPosition(Tetromino tetromino) {
        return Vector2D.add(newBlockInitPosition, new Vector2D(0, -tetromino.getHeight()));
    }

    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public GameCell[][] getBoard() {
        return board;
    }

    public GameCell getCell(Vector2D vector2D) {
        return board[vector2D.getX()][vector2D.getY()];
    }

    public boolean canExist(Tetromino tetromino) {
        for (Vector2D blockPosition : tetromino.getBlocksPosition()) {
            if (blockPosition.getX() < 0 || blockPosition.getX() > width - 1 || blockPosition.getY() < 0) {
                return false;
            }
            if (!getCell(blockPosition).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean canRotate() {
        for (Vector2D block : currentTetromino.getBlocks()) {
            Vector2D tempVector1 = new Vector2D(block.getX(), block.getY());
            tempVector1.rotate();
            Vector2D tempVector2 = Vector2D.add(currentTetromino.getPosition(), tempVector1);
            if (tempVector2.getX() < 0 || tempVector2.getX() > width - 1 || tempVector2.getY() < 0 || tempVector2.getY() > height - 1) {
                return false;
            }
            if (!board[tempVector2.getX()][tempVector2.getY()].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean canGoDown(Tetromino... tetromino) {
        Tetromino inputTetromino;
        if (tetromino.length > 0) {
            inputTetromino = tetromino[0];
        } else {
            inputTetromino = currentTetromino;
        }
        for (Vector2D blockPosition : inputTetromino.getBlocksPosition()) {
            if (blockPosition.getY() == 0) {
                return false;
            }
            if (!board[blockPosition.getX()][blockPosition.getY() - 1].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean canGoRight() {
        for (Vector2D blockPosition : currentTetromino.getBlocksPosition()) {
            if (blockPosition.getX() == width - 1) {
                return false;
            }
            if (!board[blockPosition.getX() + 1][blockPosition.getY()].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean canGoLeft() {
        for (Vector2D blockPosition : currentTetromino.getBlocksPosition()) {
            if (blockPosition.getX() == 0) {
                return false;
            }
            if (!board[blockPosition.getX() - 1][blockPosition.getY()].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void updateCurrentTetromino() {
        for (Vector2D block : currentTetromino.getBlocksPosition()) {
            getCell(block).setEmpty(false);
            getCell(block).setColor(currentTetromino.getColor());
        }
        if (canExist(nextTetromino)) {
            currentTetromino = nextTetromino;
            currentTetromino.setPosition(getInitialPosition(currentTetromino));
            nextTetromino = Tetromino.getRandomTetromino();
            nextTetromino.setPosition(getInitialPosition(nextTetromino));
        } else {
            GameState.getGameState().setGameOver(true);
            AudioPlayer.play("Audio/GameOver.wav");
            GameState.getGameState().updateTopTenScores();
        }
    }

    public void removeRowsIfPossible() {
        for (int i = 0; i < height; i++) {
            boolean flag = true;
            for (int j = 0; j < width; j++) {
                if (board[j][i].isEmpty()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                GameState.getGameState().setCountRemovedRows(GameState.getGameState().getCountRemovedRows() + 1);
                removeRow(i);
                AudioPlayer.play("Audio/vanish.wav");
                i--;
                GameState.getGameState().setScore(GameState.getGameState().getScore() + 10);
            }
        }
    }

    private void removeRow(int row) {
        for (int i = 0; i < width; i++) {
            board[i][row].setEmpty(true);
            for (int j = row + 1; j < height; j++) {
                    board[i][j - 1].setColor(board[i][j].getColor());
                    board[i][j - 1].setEmpty(board[i][j].isEmpty());
            }
            board[i][height - 1].setEmpty(true);
        }
    }

    public void repent() {
        currentTetromino.setPosition(getInitialPosition(currentTetromino));
        while (currentTetromino.getHowManyTimesRotated() != 0) {
            currentTetromino.rotate();
        }
    }
}
