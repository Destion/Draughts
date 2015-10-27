package model;

import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public class King implements Piece {
    private Colour colour;

    public King(Colour colour) {
        this.colour = colour;
    }

    @Override
    public boolean canMove(Position position, Board board, Colour colour) {

        return board.freePosition(new Position(position.getX() + 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() + 1, position.getY() - 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() - 1));
    }

    @Override
    public boolean canCapture(Position currentPosition, Position oldPosition, Board board, Colour colour) {
        //        TODO Implementation
        return false;
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

    @Override
    public String toString() {
        return "K";
    }
}
