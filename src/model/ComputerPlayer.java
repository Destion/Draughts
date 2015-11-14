package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;


public class ComputerPlayer extends Player {
    private Semaphore semaphore = new Semaphore(0);


    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    @Override
    public Move determineMove(Board board, List<Move> possibleMoves) {
        List<Move> moves = new ArrayList<>();
        for (Move possibleMove : possibleMoves) {
            Board newBoard = new Board();
            newBoard.setGrid(board.deepCopy());
            newBoard.move(possibleMove);
            List<Move> nextMoves = newBoard.generatePossibleMoves(this.getColour().other());
            if (nextMoves != null && nextMoves.size() > 0 && nextMoves.get(0).getCaptured() != null && nextMoves.get(0).getCaptured().size() > 0) {
                moves.add(possibleMove);
            }
        }
        if (possibleMoves.size() > moves.size()) {
            possibleMoves.removeAll(moves);
        }


        int choice = (int) Math.floor(Math.random() * (possibleMoves.size()));
        Move move = possibleMoves.get(choice);

        return move;
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
