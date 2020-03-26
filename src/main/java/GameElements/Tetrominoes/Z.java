package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class Z extends Tetromino {
    public Z() {
        super(Color.RED);
        getBlocks().add(new Vector2D(-1, 1));
        getBlocks().add(new Vector2D(0, 1));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(1, 0));
    }
}
