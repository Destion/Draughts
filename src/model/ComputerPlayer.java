package model;

import controllers.CommunicationController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;


public class ComputerPlayer extends Player {
    private Semaphore semaphore = new Semaphore(0);
    CommunicationController communicationController;


    public ComputerPlayer(String name, Colour colour, CommunicationController communicationController) {
        super(name, colour);
        this.communicationController = communicationController;
    }

    @Override
    public Move determineMove(Board board, List<Move> possibleMoves) {
        List<Move> moves = new ArrayList<>();
        for (Move possibleMove : possibleMoves) {
            Board newBoard = new Board();
            newBoard.setGrid(board.deepCopy());
            this.move(possibleMove, newBoard);
            List<Move> nextMoves = newBoard.generatePossibleMoves(this.getColour().other());
            if (nextMoves != null && nextMoves.size() > 0 && nextMoves.get(0).getCaptured() != null && nextMoves.get(0).getCaptured().size() > 0) {
                moves.add(possibleMove);
            }
        }
        if (possibleMoves.size() > moves.size()) {
            possibleMoves.removeAll(moves);
        }

        Move FPGAMove = null;
        int bestScore = 0;
        for (Move possibleMove : possibleMoves) {
            Board newBoard = new Board();
            newBoard.setGrid(board.deepCopy());
            this.move(possibleMove, newBoard);

            int score = communicationController.sendBytes(BoardToByte.convertToInteger(newBoard.getGrid()));
            if (score > bestScore) {
                bestScore = score;
                FPGAMove = possibleMove;

            }
        }
        int choice = (int) Math.floor(Math.random() * (possibleMoves.size()));
        Move move = possibleMoves.get(choice);

        return FPGAMove != null ? FPGAMove : move;
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

    public static Move generateMove(Map<Position, Piece> oldBoard, Map<Position, Piece> newBoard, Colour colour) {
        List<Position> difference = new ArrayList<>();
        List<Position> captured = new ArrayList<>();

        for (Position position : oldBoard.keySet()) {
            if (oldBoard.get(position).getColour() == colour && !newBoard.containsKey(position)) {
                difference.add(position);
            }

        }

        for (Position position : newBoard.keySet()) {
            if (newBoard.get(position).getColour() == colour && !oldBoard.containsKey(position)) {
                difference.add(position);
            }
        }

        for (Position position : oldBoard.keySet()) {
            if (oldBoard.get(position).getColour() == colour.other() && !newBoard.containsKey(position)) {
                captured.add(position);
            }

        }

        Move move = null;
        if (difference.size() >= 2) {
            move = new Move(difference.get(0), difference.get(1), null, captured);
        }


        return move;
    }
}
