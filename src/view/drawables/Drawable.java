package view.drawables;

import java.awt.*;

public interface Drawable {
    void draw(Graphics g);

    void setColor(Color col);

    int getX();

    int getY();
}
