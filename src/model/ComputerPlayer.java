package model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Created by Rogier on 27-10-15.
 */
public class ComputerPlayer extends Player {
    private Semaphore semaphore = new Semaphore(0);


    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    @Override
    public Move determineMove(Board board, List<Move> possibleMoves) {
        int choice = (int) Math.floor(Math.random() * (possibleMoves.size()));
        Move move = possibleMoves.get(choice);

        return move;
    }

    public Move minMax(Board board, List<Move> possibleMoves) {


        return null;
    }


    public void move(Move move, Board board) {
        Position oldPosition = move.getOldPos();
        Map<Position, Piece> grid = board.getGrid();
        Piece piece = grid.get(oldPosition);

        grid.put(move.getNewPos(), grid.get(oldPosition));
        grid.remove(oldPosition);
        if (move.getCaptured() != null && move.getCaptured().size() > 0) {
            for (Position position : move.getCaptured()) {
//                Piece tmpPiece = grid.get(position);
////                if (tmpPiece != null && tmpPiece.getColour() == Colour.WHITE) {
////                    board.setNumberWhite(board.getNumberWhite() -1);
////                } else if (tmpPiece != null && tmpPiece.getColour() == Colour.BLACK) {
////                    numberBlack--;
////                } else {
////                    System.out.println("Huh");
////                }
                grid.remove(position);
            }
        }

        if ((piece.getColour() == Colour.WHITE && move.getNewPos().getY() == 10)
                || (piece.getColour() == Colour.BLACK && move.getNewPos().getY() == 1)) {
            board.promotePiece(move.getNewPos(), piece);
        }

    }
}
