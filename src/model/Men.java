package model;

import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public class Men implements Piece {
    private Colour colour;

    public Men(Colour colour) {
        this.colour = colour;
    }

    public boolean validMove(Move move, Board board, Colour colour) {
        //        TODO Implementation

        return false;
    }

    public boolean canMove(Position position, Board board, Colour colour) {
        int var = (colour == Colour.WHITE) ? 1 : -1;
        return !board.getGrid().containsKey(new Position(position.getX() + 1, position.getY() + var))
                || !board.getGrid().containsKey(new Position(position.getX() - 1, position.getY() + var));
    }

    public boolean canCapture(Position position, Board board, Colour colour) {
        boolean canCapture = false;
        // TODO check if next to is black
        // check if free spot
        // loop this for multiple capture


        return canCapture;
    }

    @Override
    public List<Move> movesOnPosition(Position position, Board board, Colour colour) {
//        TODO Implementation
        return null;
    }

    @Override
    public List<Move> capturesOnPosition(Position position, Board board, Colour colour) {
        //        TODO Implementation
        return null;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
