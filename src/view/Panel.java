package view;

import view.drawObjects.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by destion on 26-10-15.
 */
public class Panel extends JPanel{

    ArrayList<Square> squares;

    public Panel(ArrayList<Square> sqs){
        this.squares = sqs;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (Square sq : squares){
            g.setColor(sq.getColor());
            g.fillRect(sq.getX(), sq.getY(), 100, 100);
        }
    }

    public void drawClick(Graphics g, int x, int y){
        g.setColor(Color.blue);
        g.fillRect(x, y, 100, 100);
    }
}
