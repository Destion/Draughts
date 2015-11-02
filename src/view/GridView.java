package view;

import model.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rogier on 31-10-15
 */
public class GridView extends JComponent {
    public GridView() {
        super();

    }

    @Override
    protected void paintComponent(Graphics g) {
        int squareSize = this.getWidth() / 10;
        for (int x = 0; x < Board.BOARDSIZE; x++) {
            for (int y = 0; y < Board.BOARDSIZE; y++) {
                if (x % 2 == 0) {

                }
            }
        }


        super.paintComponent(g);
    }
}
