package view;

import model.*;
import view.drawables.Drawable;
import view.drawables.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class BoardView extends JPanel implements Observer {
    private ArrayList<Square> boardQueue;
    private JFrame window;
    private ArrayList<Drawable> drawQueue;
    private Map<Position, model.Piece> grid;
    private JLabel topLabel;
    private JButton button;

    public BoardView() {
        super(null);
        this.window = new JFrame("Draughts");

        window.setSize(1280, 720);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setLocationRelativeTo(null);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.boardQueue = new ArrayList<>();
        this.drawQueue = new ArrayList<>();

        this.initBoard();
        topLabel = new JLabel("Welcome, click Play to start", SwingConstants.CENTER);
        topLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        button = new JButton("Play");
        JButton closebutton = new JButton("Exit");
        button.setBounds(1000, 85, 100, 50);
        closebutton.setBounds(1110, 85, 100, 50);
        closebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(topLabel);
        this.add(button);
        this.add(closebutton);
        topLabel.setBounds(340, 0, 600, 50);
        window.getContentPane().add(this);


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof Board) {
            this.grid = (Map<Position, Piece>)arg;
            repaint();
        }
    }

    private void initBoard() {

        ArrayList<Color> colors = new ArrayList<>();

        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                if (col % 2 != 0) {
                    for (int spot = 1; spot <= 10; spot++) {
                        if (spot % 2 != 0) {
                            colors.add(Color.white);
                        } else {
                            colors.add(new Color(0, 100, 0));
                        }
                    }
                } else {
                    for (int spot2 = 1; spot2 <= 10; spot2++) {
                        if (spot2 % 2 != 0) {
                            colors.add(new Color(0, 100, 0));
                        } else {
                            colors.add(Color.white);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            int x = 340 + ((i % 10) * 60);
            int y = 60 + ((i / 10) * 60);

            this.boardQueue.add(new Square(x, y, colors.get(0)));

            colors.remove(0);
        }
    }

    private void makeDraw() {
        drawQueue.clear();
        for (Position pos : grid.keySet()) {
            int x = pos.getX();
            int y = pos.getY();

            int screenx = (340 + 5 + 60 * x) - 60;
            int screeny = 600 - ((60 + 5 + 60 * y) - 130);


            if (grid.get(pos) instanceof Man) {
                if (grid.get(pos).getColour() == Colour.BLACK) {
                    this.drawQueue.add(new view.drawables.Man(screenx, screeny, new Color(255, 255, 0)));
                } else {
                    this.drawQueue.add(new view.drawables.Man(screenx, screeny, new Color(204, 0, 0)));
                }
            } else if (grid.get(pos) instanceof King) {
                if (grid.get(pos).getColour() == Colour.BLACK) {
                    this.drawQueue.add(new view.drawables.King(screenx, screeny, new Color(255, 255, 0)));
                } else {
                    this.drawQueue.add(new view.drawables.King(screenx, screeny, new Color(204, 0, 0)));
                }
            }
        }


    }

    public void paintComponent(Graphics g) {

        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setRenderingHint(KEY_ANTIALIASING,
                VALUE_ANTIALIAS_ON);
        super.paintComponents(g);


        if (grid != null) {
            this.makeDraw();
            for (Square sq : boardQueue) {
                sq.draw(g);
            }

            for (Drawable drawable : drawQueue) {
                drawable.draw(g);
            }
        }
    }

    public void addButtonListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void displayMessage(String message) {
        window.repaint();
        topLabel.setText(message);
    }

    public void setButtonText(String message) {
        window.repaint();
        button.setText(message);
    }

    public void setButtonInactive() {
        button.setEnabled(false);
    }

    public void setButtonActive() {
        button.setEnabled(true);
    }

    public void displayWinner(Player player) {
        if (player.getName().equals("AI")){
            JOptionPane.showMessageDialog(this, "You have lost!");
        } else {
            JOptionPane.showMessageDialog(this, player.getName() + " is the winner!");
        }
    }

    public void draw() {
        JOptionPane.showMessageDialog(this, "It's a draw!");
    }
}
