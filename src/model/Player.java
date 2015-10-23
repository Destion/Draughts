package model;

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

    public Position makeMove(Board board){
        Position newPos = this.determineMove(board);
        return newPos;
    }

    public abstract Position determineMove(Board board);
}
