package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class S extends Tetromino {
    public S() {
        super(Color.GREEN);
        getBlocks().add(new Vector2D(-1, 0));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(0, 1));
        getBlocks().add(new Vector2D(1, 1));
    }
}
