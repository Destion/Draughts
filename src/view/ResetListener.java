package view;

import model.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by destion on 2-11-15.
 */
public class ResetListener implements ActionListener {

    Board b;

    public ResetListener(Board b){
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        b.emptyMap();
    }
}
