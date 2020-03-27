package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class I extends Tetromino {
    public I() {
        super(Color.CYAN, 1);
        getBlocks().add(new Vector2D(-1, 0));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(1, 0));
        getBlocks().add(new Vector2D(2, 0));
    }
}
