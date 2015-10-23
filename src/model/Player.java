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

    public void makeMove(Board board){
        Position[] pos = this.determineMove(board);
        if (pos != null && pos.length == 2){
            board.move(pos[0],pos[1]);
        }
    }

    public abstract Position[] determineMove(Board board);
}
