package betterview;

import model.Piece;
import model.Position;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rogier on 20-11-15
 */
public class BoardPanel {
    private JButton button1;
    private JPanel panel;
    private GridView gridview;
    private Map<Position, Piece> grid;
    private Map<Position, ViewPiece> pieceList;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BoardPanel");
        frame.setContentPane(new BoardPanel(new HashMap<Position, Piece>()).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public BoardPanel(Map<Position, Piece> grid) {
        this.grid = grid;
    }

    public void initializeBoard(){
        for (Position position : grid.keySet()){

        }
    }
}
