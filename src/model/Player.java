package model;

import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public abstract class Player {
    private String name;
    private Colour colour;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public void makeMove(Board board, List<Move> possibleMoves){
        Move move = this.determineMove(board, possibleMoves);
        if (move != null ){
            board.move(move, colour);
        }
    }

    public abstract Move determineMove(Board board, List<Move> possibleMoves);

    public Colour getColour() {
        return colour;
    }
}
