import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        final JPanel panel = new JPanel();
        JButton button = new JButton("Testing...");

        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("It's working!!!");
                JLabel label = new JLabel();
                panel.add(label);
            }
        });

        frame.add(panel);

        frame.setSize(600, 600);
        frame.setTitle("Jay, it works!");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
