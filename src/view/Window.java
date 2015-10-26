package view;

import view.drawObjects.Square;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by destion on 26-10-15.
 */
public class Window extends JFrame{

    ArrayList<Square> squares;
    Panel panel;

    public Window(){
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

        panel = new Panel(this.initBoard());

        this.getContentPane().add(panel);

        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame window = new Window();
    }

    public ArrayList<Square> initBoard(){
        squares = new ArrayList<>();

        ArrayList<String> colors = new ArrayList<>();

        String color;
        for (int row=1; row <= 10; row++){
            for (int col=1; col <= 10; col ++){
                if (col%2 != 0){
                    for (int spot=1; spot<=10; spot++){
                        if (spot%2!=0){
                            colors.add("White");
                        } else {
                            colors.add("Black");
                        }
                    }
                } else {
                    for (int spot2=1; spot2<=10; spot2++){
                        if (spot2%2!=0){
                            colors.add("Black");
                        } else {
                            colors.add("White");
                        }
                    }
                }
            }
        }

        for (int i=0; i<100; i++){
            int x = 460 + ((i % 10) * 100);
            int y = 40 + ((i / 10) * 100);




            squares.add(new Square(x, y, 100, 100, colors.get(0)));
            colors.remove(0);
        }
        return squares;
    }

}
