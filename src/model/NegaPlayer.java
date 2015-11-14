package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 09-11-15
 */
public class NegaPlayer extends Player {
    private int depth;
    private List<Long> times = new ArrayList<>();

    public NegaPlayer(String name, Colour colour, int depth) {
        super(name, colour);
        this.depth = depth;
    }

    @Override
    protected Move determineMove(Board board, List<Move> possibleMoves) {
        Negamax maxie = new Negamax();
        int bestValue = Integer.MIN_VALUE;
        Move choice = null;
        for (Move move : possibleMoves) {
            List<Move> possibleList = new ArrayList<>();
            possibleList.add(move);
            int value = -maxie.negamax(this.depth, 1, this.getColour(), board, Integer.MIN_VALUE, Integer.MAX_VALUE, possibleList);
            if (value > bestValue) {
                choice = move;
                bestValue = value;
            }
        }

        return choice;
    }

    public List<Long> getTimes() {
        return times;
    }
}
