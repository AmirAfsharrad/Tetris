package GameElements;

import java.io.Serializable;

public class Vector2D implements Serializable {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    public static Vector2D rotate(Vector2D vector) {
        return new Vector2D(-vector.getY(), vector.getX());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int deltaX) {
        setX(x + deltaX);
    }

    public void addY(int deltaY) {
        setY(y + deltaY);
    }

    public void add(Vector2D vector) {
        x = x + vector.getX();
        y = y + vector.getY();
    }

    public void rotate() {
        int temp = y;
        setY(x);
        setX(-temp);
    }

    public void copy(Vector2D vector2D) {
        x = vector2D.getX();
        y = vector2D.getY();
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
