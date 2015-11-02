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

    public Move(Position oldPos, Position newPos, List<Position> interPos, List<Position> captured) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.interPos = interPos;
        this.captured = new ArrayList<>();
        if (captured != null) {
            for (Position position : captured) {
                this.captured.add(position);
            }
        }
    }

    public Position getOldPos() {
        return oldPos;
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

    public List<Position> getCaptured() {
        return captured;
    }

    public void addCaptured(Position lastCaptured) {
        if (captured == null) {
            captured = new ArrayList<>();
            captured.add(lastCaptured);
        } else {
            captured.add(lastCaptured);
        }
    }
}
