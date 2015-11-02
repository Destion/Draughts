package model;

import java.util.List;

public abstract class Player {
    private String name;
    private Colour colour;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void makeMove(Board board, List<Move> possibleMoves) {
        Move move = this.determineMove(board, possibleMoves);
        if (move != null) {
            board.move(move);
        }
    }

    protected abstract Move determineMove(Board board, List<Move> possibleMoves);

    public Colour getColour() {
        return colour;
    }
}
