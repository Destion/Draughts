package view.drawables;

import java.awt.*;

public class Man implements Drawable {

    private int x;
    private int y;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private Color color;

    public Man(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, WIDTH, HEIGHT);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
