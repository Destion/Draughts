package model;

/**
 * Created by Rogier on 23-10-15.
 */
public class Men extends Piece{
    public Men(Colour colour) {
        super(colour);
    }

    public boolean validMove(Move move, Board board) {
        return false;
    }

    @Override
    public boolean canMove(Position position, Board board) {
        return this.canMoveForward(position,board) || canMoveForward(position, board);
    }

    public boolean canMoveForward(Position position, Board board){
        return false;
    }

    public boolean canCapture(Position position, Board board){
        return false;
    }
}
