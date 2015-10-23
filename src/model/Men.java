package model;

/**
 * Created by Rogier on 23-10-15.
 */
public class Men extends Piece{
    public Men(Colour colour) {
        super(colour);
    }

    public boolean validMove(Position oldPos, Position newPos, Board board){

        return false;
    }
}
