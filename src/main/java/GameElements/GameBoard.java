package GameElements;

import GameElements.Tetrominoes.Tetromino;

import javax.smartcardio.TerminalFactory;
import java.util.ArrayList;

public class GameBoard {
    private int width = Constants.boardWidth;
    private int height = Constants.boardHeight;
    private GameCell[][] board;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;
    private Vector2D newBlockInitPosition;

    public GameBoard() {
        board = new GameCell[width][height + 4];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height + 4; j++) {
                board[i][j] = new GameCell();
            }
        }
        newBlockInitPosition = new Vector2D((int) Math.ceil(width / 2.0) - 1, height - 1);
        currentTetromino = Tetromino.getRandomTetromino();
        currentTetromino.setPosition(newBlockInitPosition);
        nextTetromino = Tetromino.getRandomTetromino();
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

    public void setCurrentTetromino(Tetromino currentTetromino) {
        this.currentTetromino = currentTetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public void setNextTetromino(Tetromino nextTetromino) {
        this.nextTetromino = nextTetromino;
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
                System.out.println("strange fuck");
                return false;
            }
            if (!getCell(blockPosition).isEmpty()) {
                System.out.println("really fuck");
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
            if (tempVector2.getX() < 0 || tempVector2.getX() > width - 1 || tempVector2.getY() < 0) {
                System.out.println("fuck1");
                return false;
            }
            if (!board[tempVector2.getX()][tempVector2.getY()].isEmpty()) {
                System.out.println("fuck2");
                return false;
            }
        }
        return true;
    }

    public boolean canGoDown() {
        for (Vector2D blockPosition : currentTetromino.getBlocksPosition()) {
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
        currentTetromino = nextTetromino;
        currentTetromino.setPosition(newBlockInitPosition);
        nextTetromino = Tetromino.getRandomTetromino();
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
                removeRow(i);
                i--;
            }
        }
    }

    private void removeRow(int row) {
        for (int i = 0; i < width; i++) {
            board[i][row].setEmpty(true);
            for (int j = row + 1; j < height; j++) {
                if (!board[i][j].isEmpty()) {
                    board[i][j - 1].setColor(board[i][j].getColor());
                    board[i][j - 1].setEmpty(false);
                    board[i][j].setEmpty(true);
                } else {
                    break;
                }
            }
        }
    }


}
