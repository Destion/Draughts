package view;

import controllers.GameController;
import view.drawables.NonClickableSelectionModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by destion on 2-11-15.
 */
public class Log extends JFrame{

    private JList<String> loglist;
    private DefaultListModel<String> logModel;
    private JScrollPane logScrollPane;


    public Log(){
        setSize(400, 400);
        setLocation();
        setResizable(false);
        setUndecorated(false);

        loglist = new JList<>();
        logModel = new DefaultListModel<>();
        logScrollPane = new JScrollPane(loglist);

        loglist.setSelectionModel(new NonClickableSelectionModel());

        loglist.setFont(new Font("Roboto", Font.PLAIN, 12));

        getContentPane().add(logScrollPane);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void setLocation(){
        setLocation((int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth(), 0);
    }

    public void addMessage(String s){
        logModel.addElement(s);
        loglist.setModel(logModel);
        loglist.ensureIndexIsVisible(this.logModel.size() - 1);
    }
}
