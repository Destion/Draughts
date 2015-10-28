package model;


import java.util.List;

public interface Piece {

    boolean canMove(Position position, Board board);

    boolean canCapture(Position currentPosition, List<Position> lastCaptured, Board board);

    List<Move> movesOnPosition(Position position, Board board);

    List<Move> capturesOnPosition(Position position, Board board);

    Colour getColour();


}
