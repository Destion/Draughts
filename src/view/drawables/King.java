package view.drawables;

import java.awt.*;

public class King implements Drawable {

    private int x;
    private int y;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private Color color;

    public King(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(x, y, WIDTH, HEIGHT);
        if (this.color.equals(new Color(255, 255, 0))) {
            g.setColor(new Color(255, 194, 38));
        } else {
            g.setColor(new Color(150, 0, 0));
        }
        g.fillOval(x + 10, y + 10, 30, 30);
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
