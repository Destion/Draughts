package view;

import model.Piece;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

/**
 * Created by Rogier on 31-10-15
 */
public class ImprovedBoardView extends JPanel implements Observer {
    private Map<Position, Piece> grid;
    private JFrame window;

    public ImprovedBoardView() {
        super(new GridLayout());
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        super.paintComponents(g);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
