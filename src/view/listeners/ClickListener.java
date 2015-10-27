package view.listeners;

import controllers.GuiController;
import view.drawables.Man;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

    GuiController gui;

    public ClickListener(GuiController g){
        super();
        this.gui = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mousex = MouseInfo.getPointerInfo().getLocation().x;
        int mousey = MouseInfo.getPointerInfo().getLocation().y;

        int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
        int ypos = 40 + 10 + ((((mousey - 40) / 100)) *100 ) ;

        System.out.println(mousey);
        System.out.println(ypos);

        this.gui.addDrawable(new Man(xpos, ypos, Color.green));

        this.gui.repaint();
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
