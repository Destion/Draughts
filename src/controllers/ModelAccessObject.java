//package controllers;
//
//import model.*;
//import view.drawables.Drawable;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * Created by destion on 27-10-15.
// */
//public class ModelAccessObject {
//    private Map<Position, model.Piece> grid;
//    private GameController gc;
//    private GuiController gui;
//
//    public ModelAccessObject(GameController game, GuiController gui){
//        this.gc = game;
//        this.gui = gui;
//
//        this.grid = game.getGrid();
//    }
//
//    public void makeDraw(Map<Position, model.Piece> map){
//        ArrayList<Drawable> temp = gui.getQueue();
//        for (Position pos : map.keySet()){
//            int x = pos.getX();
//            int y = pos.getY();
//
//            int screenx = (460 + 10 + 100*x) - 100;
//            int screeny = 1000 -((40 + 10 + 100*y) - 100);
//
//            boolean whiteMan = false;
//            boolean blackMan = false;
//
//            if (temp.size() > 0) {
//                for (Drawable draw : temp) {
//                    if ((screenx == draw.getX()) && (screeny == draw.getY())) {
//                        break;
//                    } else {
//                        if (map.get(pos) instanceof Man) {
//                            if (map.get(pos).getColour() == Colour.BLACK) {
//                                blackMan = true;
//                            } else {
//                                whiteMan = true;
//                            }
//                        } else if (map.get(pos).getClass().isInstance(King.class)) {
//                            break;
//                            //TODO implement drawing of kings
//                        }
//                    }
//                }
//            } else {
//                if (map.get(pos) instanceof Man) {
//                    if (map.get(pos).getColour() == Colour.BLACK) {
//                        gui.addDrawable(new view.drawables.Man(screenx, screeny, new Color(255, 255, 0)));
//                    } else {
//                        gui.addDrawable(new view.drawables.Man(screenx, screeny, new Color(204, 0, 0)));
//                    }
//                } else if (map.get(pos).getClass().isInstance(King.class)) {
//                    break;
//                    //TODO implement drawing of kings
//                }
//            }
//
//            if (blackMan){
//                gui.addDrawable(new view.drawables.Man(screenx, screeny, new Color(255, 255, 0)));
//            } else if (whiteMan){
//                gui.addDrawable(new view.drawables.Man(screenx, screeny, new Color(204, 0, 0)));
//            }
//        }
//
//
//    }
//
//}
