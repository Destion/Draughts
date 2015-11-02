package view.drawables;

import javax.swing.*;

/**
 * Created by destion on 2-11-15.
 */
public class NonClickableSelectionModel extends DefaultListSelectionModel{
    @Override
    public void setSelectionInterval(int index0, int index1) {
        super.setSelectionInterval(-1, -1);
    }
}
