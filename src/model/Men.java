package model;

import java.util.ArrayList;
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
        return board.freePosition(new Position(position.getX() + 1, position.getY() + var))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + var));
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
        List<Move> possibleMoves = new ArrayList<>();
        int var = (colour == Colour.WHITE) ? 1 : -1;
        Position option1 = new Position(position.getX() + 1, position.getY() + var);
        Position option2 = new Position(position.getX() - 1, position.getY() + var);
        if (board.freePosition(option1)) {
            Move move = new Move(position, option1, null);
            possibleMoves.add(move);
        }
        if (board.freePosition(option2)) {
            Move move = new Move(position, option2, null);
            possibleMoves.add(move);
        }
        return possibleMoves;
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
        return "M";
    }
}
