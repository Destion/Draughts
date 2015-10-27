package controllers;

import view.drawables.Drawable;
import view.drawables.Square;
import view.listeners.ClickListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiController extends JPanel {

    private ArrayList<Drawable> drawQueue;

    public GuiController(){
        super();

        this.drawQueue = new ArrayList<>();
        this.initBoard();

        JFrame window = new JFrame();
        window.setSize(1920, 1080);
        window.setResizable(false);
        window.setUndecorated(true);
        window.setLocationRelativeTo(null);

        window.getContentPane().add(this);
        window.addMouseListener(new ClickListener(this));


        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void addDrawable(Drawable d){
        this.drawQueue.add(d);
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);

        for (Drawable drawable : this.drawQueue) {
            drawable.draw(g);
        }
    }

    public void initBoard(){

        ArrayList<Color> colors = new ArrayList<>();

        String color;
        for (int row=1; row <= 10; row++){
            for (int col=1; col <= 10; col ++){
                if (col%2 != 0){
                    for (int spot=1; spot<=10; spot++){
                        if (spot%2!=0){
                            colors.add(Color.white);
                        } else {
                            colors.add(Color.black);
                        }
                    }
                } else {
                    for (int spot2=1; spot2<=10; spot2++){
                        if (spot2%2!=0){
                            colors.add(Color.black);
                        } else {
                            colors.add(Color.white);
                        }
                    }
                }
            }
        }

        for (int i=0; i<100; i++){
            int x = 460 + ((i % 10) * 100);
            int y = 40 + ((i / 10) * 100);

            this.drawQueue.add(new Square(x, y, colors.get(0)));

            colors.remove(0);
        }
    }
}
