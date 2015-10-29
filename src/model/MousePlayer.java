package model;

import java.util.List;

/**
 * Created by destion on 28-10-15.
 */
public class MousePlayer extends Player {

    private boolean movedone;

    public MousePlayer(String name, Colour colour) {
        super(name, colour);
        this.movedone = false;
    }

    @Override
    public Move determineMove(Board board, List<Move> possibleMoves) {
        return null;
    }
}
