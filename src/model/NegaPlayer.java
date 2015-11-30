package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 09-11-15
 */
public class NegaPlayer extends Player {
    private int depth;
    private boolean analyse;

    public NegaPlayer(String name, Colour colour, int depth) {
        super(name, colour);
        this.depth = depth;
    }

    public NegaPlayer(String name, Colour colour, int depth, boolean analyse) {
        super(name, colour);
        this.depth = depth;
        this.analyse = analyse;
    }

    @Override
    protected Move determineMove(Board board, List<Move> possibleMoves) {
        Negamax maxie;
        if (analyse){
            maxie = new Negamax(this.getColour(), analyse);
        } else {
            maxie = new Negamax();
        }


//        for (Move move : possibleMoves) {
//            List<Move> possibleList = new ArrayList<>();
//            possibleList.add(move);
//            int value = -maxie.negamax(this.depth, 1, this.getColour(), board, Integer.MIN_VALUE, Integer.MAX_VALUE, possibleList);
////            System.out.println(move.toString() + " has a value of " + value);
//            if (value > bestValue) {
//                choice = move;
//                bestValue = value;
//            }
//        }
//        System.out.println(choice.toString());
        return maxie.negaShallow(this.depth,this.getColour(),board,Integer.MIN_VALUE,Integer.MAX_VALUE,possibleMoves);
    }

}
