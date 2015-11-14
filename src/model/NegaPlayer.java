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
        maxie.negamax(this.depth, 1, this.getColour(), board, Integer.MIN_VALUE, Integer.MAX_VALUE, possibleMoves);

        return possibleMoves.get(0);
    }

    public List<Long> getTimes() {
        return times;
    }
}
