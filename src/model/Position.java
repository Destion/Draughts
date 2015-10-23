package model;

public class Position {
    private XAxis x;
    private int y;

    public Position(XAxis x, int y) {
        this.x = x;
        this.y = y;
    }

    public XAxis getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(XAxis x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
