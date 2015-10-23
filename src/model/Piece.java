package model;


public abstract class Piece {
    private Colour colour;

    public Piece(Colour colour) {
        this.colour = colour;
    }

    public abstract boolean validMove(Position oldPos, Position newPos, Board board);




}
