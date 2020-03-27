package UserInterface;

import GameElements.Constants;
import GameElements.GameCell;
import GameController.GameState;
import GameElements.Tetrominoes.Tetromino;
import GameElements.Vector2D;
import GameController.GameRunner;

import java.awt.*;

public class Drawer {
    private Graphics2D graphics2D;

    public Drawer(Graphics2D graphics2D) {
        setGraphics2D(graphics2D);
    }

    private int getCellSize() {
        int width = GameRunner.getGameRunner().getFrameWidth();
        int height = GameRunner.getGameRunner().getFrameHeight();
        return height/30;
    }

    private int getBoardWidth() {
        return Constants.boardWidth * getCellSize();
    }

    private void fillRect(Vector2D vector2D, int size, Color color) {
        graphics2D.setColor(color);
        graphics2D.fillRect(vector2D.getX(), vector2D.getY(), size, size);
    }

    private void CellBlockRect(Vector2D vector2D, int size, Color color) {
        fillRect(vector2D, size, color);
        graphics2D.setColor(Color.GRAY);
        graphics2D.drawRect(vector2D.getX(), vector2D.getY(), size, size);
    }

    private void fillCircle(Vector2D vector2D, int size, Color color) {
        graphics2D.setColor(color);
        graphics2D.fillOval(vector2D.getX(), vector2D.getY(), size, size);
    }

    private Vector2D getCellDrawPosition(int i, int j) {
        return new Vector2D( GameRunner.getGameRunner().getFrameWidth() / 3 +
                (2 * GameRunner.getGameRunner().getFrameWidth() / 3 - getBoardWidth()) / 2 + i * getCellSize(),
                GameRunner.getGameRunner().getFrameHeight() - 100 - j * getCellSize());
    }

    private Vector2D getCellDrawPosition(Vector2D vector2D) {
        return getCellDrawPosition(vector2D.getX(), vector2D.getY());
    }

    private void drawGameCell(GameCell gameCell, int i, int j) {
        CellBlockRect(getCellDrawPosition(i, j), getCellSize(), gameCell.getColor());
    }

    private void drawTetromino(Tetromino tetromino) {
        for (Vector2D block : tetromino.getBlocksPosition()) {
            CellBlockRect(getCellDrawPosition(block) ,getCellSize(), tetromino.getColor());
        }
    }

    public void drawBoard(GameState gameState) {
        for (int i = 0; i < gameState.getGameBoard().getWidth(); i++) {
            for (int j = 0; j < gameState.getGameBoard().getHeight(); j++) {
                drawGameCell(gameState.getGameBoard().getBoard()[i][j], i, j);
            }
        }
        drawTetromino(gameState.getGameBoard().getCurrentTetromino());
    }

    public void drawGameState(GameState gameState) {
        drawBoard(gameState);
        drawGameStatistics(graphics2D);
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public void drawGameStatistics(Graphics2D graphics2D) {
        String prompt = "Total removed rows: " + GameState.getGameState().getCountRemovedRows();
        Font font = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int width = fontMetrics.stringWidth(prompt);
        graphics2D.setFont(font);
        graphics2D.drawString(prompt, (GameRunner.getGameRunner().getFrameWidth() / 3 - width) / 2,
                (GameRunner.getGameRunner().getFrameHeight() - 50) / 2);

    }


    public void drawGameOver(Graphics2D graphics2D) {
        String prompt = "Game Over! :(";
        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int width = fontMetrics.stringWidth(prompt);
        graphics2D.setFont(font);
        graphics2D.drawString(prompt, (GameRunner.getGameRunner().getFrameWidth() - width) / 2,
                (GameRunner.getGameRunner().getFrameHeight() - 50) / 2);

    }
}
