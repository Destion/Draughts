package view.drawables;

import java.awt.*;

/**
 * Created by destion on 27-10-15.
 */
public interface Drawable {
    void draw(Graphics g);
    void setPos(int x, int y);
    void setColor(Color col);
    int getX();
    int getY();
    Color getColor();
}
