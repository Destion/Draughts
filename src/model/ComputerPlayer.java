package model;

import java.util.List;

/**
 * Created by Rogier on 27-10-15.
 */
public class ComputerPlayer extends Player {

    public ComputerPlayer(String name, Colour colour) {
        super(name, colour);
    }

    @Override
    public Move determineMove(Board board, List<Move> possibleMoves) {
        int choice = (int) Math.floor(Math.random() * (possibleMoves.size()));
        Move move = possibleMoves.get(choice);


        return move;
    }
}
