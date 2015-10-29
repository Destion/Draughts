package view.drawables;


import java.awt.*;


public class Square implements Drawable {

    int x;
    int y;
    final int WIDTH = 60;
    final int HEIGHT = 60;
    Color color;

    public Square(int x, int y, Color color){
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
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setColor(Color col) {
        this.color = col;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
