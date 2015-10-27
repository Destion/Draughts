package view.drawables;

import java.awt.*;

public class King implements Drawable {

    private int x;
    private int y;
    private final int WIDTH = 80;
    private final int HEIGHT = 80;
    private Color color;
    
    public King(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(x, y, WIDTH, HEIGHT);
        //TODO Also implement a way to draw a crown on top of the king
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
