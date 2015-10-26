package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 24-10-15.
 */
public class Move {
    private Position oldPos;
    private Position newPos;
    private List<Position> interPos;
    private List<Position> captured;

    public Move(Position oldPos, Position newPos, List<Position> interPos) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.interPos = interPos;
        captured = new ArrayList<Position>();
    }

    public Position getOldPos() {
        return oldPos;
    }

    public void setOldPos(Position oldPos) {
        this.oldPos = oldPos;
    }

    public Position getNewPos() {
        return newPos;
    }

    public void setNewPos(Position newPos) {
        this.newPos = newPos;
    }

    public List<Position> getInterPos() {
        return interPos;
    }

    public void setInterPos(List<Position> interPos) {
        this.interPos = interPos;
    }

    public void calculateCaptured(){
        if(interPos != null || Math.abs(oldPos.getY() - newPos.getY()) > 1 ){
            Position tmp = oldPos;
            if (interPos != null) {
                for (Position position : interPos) {
                    int x = (tmp.getX() + position.getX()) / 2;
                    int y = (tmp.getY() + position.getY()) / 2;
                    captured.add(new Position(x, y));
                    tmp = position;
                }
            }
            int x = (tmp.getX() + newPos.getX())/2;
            int y = (tmp.getY() + newPos.getY())/2;
            captured.add(new Position(x, y));
        }
    }

    public List<Position> getCaptured() {
        return captured;
    }
}
