package GameElements;

import java.awt.*;

public class GameCell {
    private Color color;
    private boolean empty;

    public GameCell() {
        color = Color.LIGHT_GRAY;
        empty = true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
        if (empty){
            color = Color.LIGHT_GRAY;
        }
    }
}
