package model;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Move determineMove(Board board, List<Move> possibleMoves) {
        int i = possibleMoves.size();
        this.temporaryTUI(possibleMoves);
        return null;
    }

    public void temporaryTUI(List<Move> possibleMoves) {
        System.out.println("Possible Moves: ");
        int i = 0;
        for (Move move : possibleMoves) {
            String s = i + " " + move.getOldPos().toString() + " ";
            if (move.getInterPos() != null) {
                for (Position position : move.getInterPos()) {
                    s = s + position.toString() + " ";
                }
            }
            s = s + move.getNewPos().toString();
            System.out.println(s);
            i++;
        }

    }

}
