package controllers;

import model.Player;
import view.drawables.Drawable;
import view.drawables.Square;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiController extends JPanel {

    private ArrayList<Drawable> drawQueue;
    private ArrayList<Square> boardQueue;
    //    private ModelAccessObject MAO;
    private JFrame window;

    public GuiController() {
        super();
        this.window = new JFrame();
        window.setSize(1920, 1080);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setLocationRelativeTo(null);



        this.drawQueue = new ArrayList<>();
        this.boardQueue = new ArrayList<>();
        this.initBoard();




        window.getContentPane().add(this);

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        repaint();
    }

    public void addDrawable(Drawable d) {
        this.drawQueue.add(d);
    }

    public Color removeDrawable(int x, int y) {
        for (Drawable d : drawQueue) {
            if (d.getX() == x && d.getY() == y) {
                drawQueue.remove(d);
                return d.getColor();
            }
        }
        return null;
    }

    public Color getColor (int x, int y){
        for (Drawable d: drawQueue){
            if (d.getX() == x && d.getY() == y){
                return d.getColor();
            }
        }
        return null;
    }

    public ArrayList<Drawable> getQueue() {
        return this.drawQueue;
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);

//        this.MAO.makeDraw(gc.getGrid());

        for (Square sq : boardQueue) {
            sq.draw(g);
        }

        for (Drawable drawable : this.drawQueue) {
            drawable.draw(g);
        }
    }

    public void initBoard() {

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
            int x = 460 + ((i % 10) * 100);
            int y = 40 + ((i / 10) * 100);

            this.boardQueue.add(new Square(x, y, colors.get(0)));

            colors.remove(0);
        }
    }

    public void invalidMove(){
        JOptionPane.showMessageDialog(this, "This is not a valid move!");
    }

    public void displayPlayer(Player p){
        JOptionPane.showMessageDialog(this, p.getName() + " , you're up!");
    }

    //initial placement for all the red man pieces
//        for (int z=0; z<40; z++) {
//            if ((z < 10 && z >= 0 ) || ( z < 30 && z >= 20)){
//                if (z % 2 != 0) {
//                    int manx = 460 + 10 + ((z % 10) * 100);
//                    int many = 40 + 10 + ((z / 10) * 100);
//                    this.drawQueue.add(new Man(manx, many, new Color(255,255,0)));
//                }
//            } else {
//                if (z % 2 == 0){
//                    int manx = 460 + 10 + ((z % 10) * 100);
//                    int many = 40 + 10 + ((z / 10) * 100);
//                    this.drawQueue.add(new Man(manx, many, new Color(255,255,0)));
//                }
//            }
//        }

    //initial placement for all the white man pieces
//        for (int z=0; z<40; z++) {
//            if ((z < 10 && z >= 0 ) || ( z < 30 && z >= 20)){
//                if (z % 2 != 0) {
//                    int manx = 460 + 10 + ((z % 10) * 100);
//                    int many = 600 + 40 + 10 + ((z / 10) * 100);
//                    this.drawQueue.add(new Man(manx, many, new Color(204,0,0)));
//                }
//            } else {
//                if (z % 2 == 0){
//                    int manx = 460 + 10 + ((z % 10) * 100);
//                    int many = 600 + 40 + 10 + ((z / 10) * 100);
//                    this.drawQueue.add(new Man(manx, many, new Color(204,0,0)));
//                }
//            }
//        }
}
