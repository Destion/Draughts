package model;


public abstract class Piece {
    private Colour colour;

    public Piece(Colour colour) {
        this.colour = colour;
    }

    public abstract boolean validMove(Move move, Board board);

    public abstract boolean canMove(Position position, Board board);

    public Colour getColour() {
        return colour;
    }
}
