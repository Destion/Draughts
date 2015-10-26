package view.drawObjects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by destion on 26-10-15.
 */
public class Square extends JComponent {

    int x;
    int y;
    int width;
    int height;
    String color;

    public Square(int x, int y, int width, int height, String color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Color getColor(){
        if (this.color.equals("Black")){
            return Color.black;
        } else {
            return Color.white;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
