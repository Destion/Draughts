package model;


import java.util.List;

public interface Piece {

    boolean canMove(Position position, Board board, Colour colour);

    boolean canCapture(Position position, Board board, Colour colour);

    List<Move> movesOnPosition(Position position, Board board, Colour colour);

    List<Move> capturesOnPosition(Position position, Board board, Colour colour);

    Colour getColour();
}
