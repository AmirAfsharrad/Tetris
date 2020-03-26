package GameElements.Tetrominoes;

import GameElements.Vector2D;

import java.awt.*;

public class J extends Tetromino {
    public J() {
        super(Color.BLUE);
        getBlocks().add(new Vector2D(-1, 1));
        getBlocks().add(new Vector2D(-1, 0));
        getBlocks().add(new Vector2D(0, 0));
        getBlocks().add(new Vector2D(1, 0));
    }
}
