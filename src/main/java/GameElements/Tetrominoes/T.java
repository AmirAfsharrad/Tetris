package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class T extends Tetromino {
    public T() {
        super(Color.MAGENTA, 2);
        getBlocks().add(new Vector2D(-1, 0));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(0, 1));
        getBlocks().add(new Vector2D(1, 0));
    }
}
