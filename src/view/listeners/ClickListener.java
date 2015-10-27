package view.listeners;

import controllers.GuiController;
import view.drawables.Man;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

    private GuiController gui;
    private Color prevCol;

    public ClickListener(GuiController g){
        super();
        this.gui = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //On press remove circle

        int mousex = MouseInfo.getPointerInfo().getLocation().x;
        int mousey = MouseInfo.getPointerInfo().getLocation().y;

        int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
        int ypos = 40 + 10 + ((((mousey - 40) / 100)) *100 ) ;

        prevCol = gui.removeDrawable(xpos, ypos);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //On release draw new circle

        int mousex = MouseInfo.getPointerInfo().getLocation().x;
        int mousey = MouseInfo.getPointerInfo().getLocation().y;

        int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
        int ypos = 40 + 10 + ((((mousey - 40) / 100)) *100 ) ;

        System.out.println(mousey);
        System.out.println(ypos);

        this.gui.addDrawable(new Man(xpos, ypos, prevCol));

        this.gui.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
