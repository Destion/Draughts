package view.listeners;

import controllers.GuiController;
import view.drawables.Man;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

    private GuiController gui;
    private Color prevCol;
    private boolean canplace;

    public ClickListener(GuiController g){
        super();
        this.gui = g;
        this.canplace = false;
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
        this.canplace = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //On release draw new circle

        if (this.   canplace) {
            int mousex = MouseInfo.getPointerInfo().getLocation().x;
            int mousey = MouseInfo.getPointerInfo().getLocation().y;

            int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
            int ypos = 40 + 10 + ((((mousey - 40) / 100)) * 100);

            this.gui.addDrawable(new Man(xpos, ypos, prevCol));

            this.gui.repaint();
            this.canplace = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
