package model;


import java.util.List;

public interface Piece {

    boolean canMove(Position position, Board board);

    boolean canCapture(Position currentPosition, List<Position> lastCaptured, Board board);

    List<Move> normalMoves(Position currentPosition, Board board);

    List<Move> captureMoves(Position currentPosition, Board board);

    Colour getColour();

}
