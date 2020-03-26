package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class L extends Tetromino {
    public L() {
        super(Color.ORANGE);
        getBlocks().add(new Vector2D(-1, 0));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(1, 0));
        getBlocks().add(new Vector2D(1, 1));
    }
}
