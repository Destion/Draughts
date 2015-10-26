package view.listeners;

import view.*;
import view.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by destion on 26-10-15.
 */
public class ClickListener implements MouseListener {

    JFrame window;

    public ClickListener(Window w){
        super();
        this.window = w;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mousex = MouseInfo.getPointerInfo().getLocation().x;
        int mousey = MouseInfo.getPointerInfo().getLocation().y;

        System.out.println("Mouse X: " +mousex);
        System.out.println("Mouse Y: " +mousey);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
