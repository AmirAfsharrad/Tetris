package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tetromino implements Serializable {
    private Color color;
    private ArrayList<Vector2D> blocks;
    private ArrayList<Vector2D> blocksPosition;
    private Vector2D position;
    private int height;
    private int howManyTimesRotated = 0;

    public Tetromino() {}

    public Tetromino(Color color, int height) {
        this.color = color;
        blocks = new ArrayList<>();
        blocksPosition = new ArrayList<>(Collections.nCopies(4, null));
        position = new Vector2D(0, 0);
        this.height = height;
    }

    public static Tetromino getRandomTetromino() {
        Random random = new Random(System.nanoTime());
        switch (random.nextInt(7)) {
            case 0:
                return new I();
            case 1:
                return new J();
            case 2:
                return new L();
            case 3:
                return new O();
            case 4:
                return new S();
            case 5:
                return new T();
            default:
                return new Z();
        }
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Vector2D> getBlocks() {
        return blocks;
    }

    protected void setBlocks(ArrayList<Vector2D> blocks) {
        this.blocks = blocks;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position.copy(position);
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Vector2D> getBlocksPosition() {
        int i = 0;
        for (Vector2D block : blocks) {
            blocksPosition.set(i, Vector2D.add(position, block));
            i++;
        }
        return blocksPosition;
    }

    public int getHowManyTimesRotated() {
        return howManyTimesRotated % 4;
    }

    public void rotate() {
        for (Vector2D block : blocks) {
            block.rotate();
        }
        howManyTimesRotated++;
    }

    public void moveDown() {
        position.addY(-1);
    }

    public void moveRight() {
        position.addX(1);
    }

    public void moveLeft() {
        position.addX(-1);
    }
}
