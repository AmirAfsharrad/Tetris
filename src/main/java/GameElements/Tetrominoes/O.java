package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class O extends Tetromino {
    public O() {
        super(Color.YELLOW, 2);
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(0, 1));
        getBlocks().add(new Vector2D(1, 0));
        getBlocks().add(new Vector2D(1, 1));
    }

    @Override
    public void rotate() {
    }
}
