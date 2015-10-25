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
        boolean canMove = false;
        int var = 0;
        if (board.getGrid().get(position).getColour() == Colour.WHITE) {
            var = 1;
        } else {
            var = -1;
        }
        Position option1 = board.getPositionOnBoard(new Position(position.getX() + 1, position.getY() + var));
        Position option2 = board.getPositionOnBoard(new Position(position.getX() - 1, position.getY() + var));
        if (option1 == null || option2 == null) {
            canMove = true;
        }
        return canMove;
    }

    public boolean canCapture(Position position, Board board){
        boolean canCapture = false;
        // check if next to is black
        // check if free spot
        // loop this for multiple capture



        return false;
    }
}
