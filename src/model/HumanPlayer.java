package model;

import java.util.List;

/**
 * Created by Rogier on 24-10-15.
 */
public class HumanPlayer extends Player {
    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Move determineMove(Board board, List<Move> possibleMoves) {
        this.temporaryTUI();

        return null;
    }

    public void temporaryTUI(){
        System.out.println();
    }
}
