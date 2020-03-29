package GameController;

import GameElements.Constants;
import GameElements.GameCell;
import GameElements.Tetrominoes.Tetromino;
import GameElements.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class Drawer {
    private Graphics2D graphics2D;

    public Drawer(Graphics2D graphics2D) {
        setGraphics2D(graphics2D);
    }

    private int getCellSize() {
        return GameRunner.getGameRunner().getFrameHeight() / 30;
    }

    private int getFontSize() {
        return (GameRunner.getGameRunner().getFrameWidth() + GameRunner.getGameRunner().getFrameHeight()) / 100;
    }

    private int getBoardWidth() {
        return Constants.boardWidth * getCellSize();
    }

    private int getBoardHeight() {
        return Constants.boardHeight * getCellSize();
    }

    private void fillRect(Vector2D vector2D, int size, Color color) {
        graphics2D.setColor(color);
        graphics2D.fillRect(vector2D.getX(), vector2D.getY(), size, size);
    }

    private void cellBlockRect(Vector2D vector2D, int size, Color color) {
        fillRect(vector2D, size, color);
        graphics2D.setColor(Color.GRAY);
        graphics2D.drawRect(vector2D.getX(), vector2D.getY(), size, size);
    }

    private void fillCircle(Vector2D vector2D, int size, Color color) {
        graphics2D.setColor(color);
        graphics2D.fillOval(vector2D.getX(), vector2D.getY(), size, size);
    }

    private Vector2D getCellDrawPosition(int i, int j) {
        return new Vector2D(GameRunner.getGameRunner().getFrameWidth() / 2 +
                (GameRunner.getGameRunner().getFrameWidth() / 3 - getBoardWidth()) / 2 + i * getCellSize(),
                (GameRunner.getGameRunner().getFrameHeight() + getBoardHeight()) / 2 - j * getCellSize());
    }

    private Vector2D getCellDrawPosition(Vector2D vector2D) {
        return getCellDrawPosition(vector2D.getX(), vector2D.getY());
    }

    private void drawGameCell(GameCell gameCell, int i, int j) {
        cellBlockRect(getCellDrawPosition(i, j), getCellSize(), gameCell.getColor());
    }

    private void drawCurrentTetromino(Tetromino tetromino) {
        for (Vector2D block : tetromino.getBlocksPosition()) {
            cellBlockRect(getCellDrawPosition(block), getCellSize(), tetromino.getColor());
        }
    }

    private void drawNextTetromino(Tetromino tetromino) {
        for (Vector2D block : tetromino.getBlocksPosition()) {
            Vector2D position = new Vector2D(8 * GameRunner.getGameRunner().getFrameWidth() / 10 + block.getX() * getCellSize(),
                    GameRunner.getGameRunner().getFrameHeight() / 2 + (block.getY() - 20) * getCellSize());
            cellBlockRect(position, getCellSize(), tetromino.getColor());
        }
        String prompt = "Next: ";
        drawString(prompt, GameRunner.getGameRunner().getFrameHeight() / 2 - 3 * getCellSize(), "next tetromino");
    }

    private void drawBoard(GameState gameState) {
        for (int i = 0; i < gameState.getGameBoard().getWidth(); i++) {
            for (int j = 0; j < gameState.getGameBoard().getHeight(); j++) {
                drawGameCell(gameState.getGameBoard().getBoard()[i][j], i, j);
            }
        }
        drawCurrentTetromino(gameState.getGameBoard().getCurrentTetromino());
        drawNextTetromino(gameState.getGameBoard().getNextTetromino());
    }

    public void drawGameState(GameState gameState) {
        String title = "TETRIS";
        drawString(title, GameRunner.getGameRunner().getFrameHeight() / 8, "c", 250);

        drawBoard(gameState);
        drawGameStatistics();
        drawGameInstructions();
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    private void drawString(String prompt, int y, String align, int... relativeFontSizePercents) {
        int relativeSize = 100;
        if (relativeFontSizePercents.length > 0) {
            relativeSize = relativeFontSizePercents[0];
        }
        Font font = new Font("Helvetica", Font.BOLD, getFontSize() * relativeSize / 100);
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int width = fontMetrics.stringWidth(prompt);
        graphics2D.setFont(font);
        switch (align.toLowerCase()) {
            case "c": {
                graphics2D.drawString(prompt, (GameRunner.getGameRunner().getFrameWidth() - width) / 2, y);
                break;
            }
            case "l": {
                graphics2D.drawString(prompt, GameRunner.getGameRunner().getFrameWidth() / 20, y);
                break;
            }
            case "r": {
                graphics2D.drawString(prompt, GameRunner.getGameRunner().getFrameWidth() / 4, y);
                break;
            }
            case "next tetromino": {
                graphics2D.drawString(prompt, 8 * GameRunner.getGameRunner().getFrameWidth() / 10 + 0 * getCellSize(), y);
                break;
            }
        }
    }

    private void drawGameStatistics() {
        String prompt = "Total removed rows: " + GameState.getGameState().getCountRemovedRows();
        drawString(prompt, 31 * GameRunner.getGameRunner().getFrameHeight() / 100, "l");

        prompt = "Score: " + GameState.getGameState().getScore();
        drawString(prompt, 38 * GameRunner.getGameRunner().getFrameHeight() / 100, "l");

        prompt = "Top Ten ";
        drawString(prompt, 45 * GameRunner.getGameRunner().getFrameHeight() / 100, "l");

        prompt = "Scores:";
        drawString(prompt, 48 * GameRunner.getGameRunner().getFrameHeight() / 100, "l");

        int counter = 0;
        int totalCount = GameState.getGameState().getTopTenScores().size();
        for (Integer topTenScore : GameState.getGameState().getTopTenScores()) {
            prompt = (totalCount - counter) + ") " + topTenScore.toString();
            drawString(prompt, (50 + (totalCount - counter) * 4) * GameRunner.getGameRunner().getFrameHeight() / 100, "l");
            counter++;
        }
    }

    private void drawGameInstructions() {
        String prompt = "INSTRUCTIONS: ";
        drawString(prompt, 40 * GameRunner.getGameRunner().getFrameHeight() / 100, "r", 110);

        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Down Arrow: Fall Faster ");
        instructions.add("Right Arrow: Move Right ");
        instructions.add("Left Arrow: Move Left");
        instructions.add("Up Arrow: Rotate");
        instructions.add("Space: Free Fall");
        instructions.add("R: Repent");
        instructions.add("S: Save Game");
        instructions.add("L: Load Game");
        instructions.add("P: Pause/Play");
        instructions.add("N: New Game");

        int i = 1;
        for (String instruction : instructions) {
            drawString(instruction, (40 + 5 * i) * GameRunner.getGameRunner().getFrameHeight() / 100, "r");
            i++;
        }
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
