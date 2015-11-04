package view.drawables;


import java.awt.*;


public class Square implements Drawable {

    int x;
    int y;
    final int WIDTH = 100;
    final int HEIGHT = 100;
    Color color;

    public Square(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
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
