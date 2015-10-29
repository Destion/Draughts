//package view.listeners;
//
//import controllers.GameController;
//import controllers.GuiController;
//import model.Colour;
//import model.Move;
//import model.Player;
//import model.Position;
//import view.drawables.Man;
//import java.util.List;
//
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//
//public class ClickListener implements MouseListener {
//
//    private GuiController gui;
//    private Color prevCol;
//    private Colour prevcolour;
//    private boolean canplace;
//
//    private boolean requested;
//
//    private boolean movedone;
//
//    private int prevmousex;
//    private int prevmousey;
//
//    private List possi;
//
//    private GameController gc;
//
//    public ClickListener(GuiController g){
//        super();
//        this.gui = g;
//        this.canplace = false;
//        this.gc = gui.getGC();
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        //On press remove circle
//
//        this.prevmousex = MouseInfo.getPointerInfo().getLocation().x;
//        this.prevmousey = MouseInfo.getPointerInfo().getLocation().y;
//
//        this.canplace = true;
//
////        int mousex = MouseInfo.getPointerInfo().getLocation().x;
////        int mousey = MouseInfo.getPointerInfo().getLocation().y;
////
////        int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
////        int ypos = 40 + 10 + ((((mousey - 40) / 100)) *100 ) ;
////
////        prevCol = gui.removeDrawable(xpos, ypos);
////        this.canplace = true;
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        //On release check if the move is valid and (when valid) remove last circle and place new one
//
//        int prevxpos = 460 + 10 + ((((this.prevmousex - 460) / 100) % 10) * 100);
//        int prevypos = 40 + 10 + ((((this.prevmousey - 40) / 100)) * 100);
//
//        if (this.canplace) {
//            int mousex = MouseInfo.getPointerInfo().getLocation().x;
//            int mousey = MouseInfo.getPointerInfo().getLocation().y;
//
//
//            //Converts the mouseposition to the grid
//            int xpos = 460 + 10 + ((((mousex - 460) / 100) % 10) * 100);
//            int ypos = 40 + 10 + ((((mousey - 40) / 100)) * 100);
//
//            //Converts the previous position to the grid (1.1 - 10.10)
//            int prevgridx = (prevxpos + 100 - 460 - 10) / 100;
//            int prevgridy = (-((prevypos - 1000 + 100 - 40 - 10) / 100)) + 1;
//
//            int newgridx = (xpos + 100 - 460 - 10) / 100;
//            int newgridy = (-((ypos - 1000 + 100 - 40 - 10) / 100)) + 1;
//        }
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//}
