package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public class Man implements Piece {
    private Colour colour;

    public Man(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position position, Board board, Colour colour) {
        int var = (colour == Colour.WHITE) ? 1 : -1;
        return board.freePosition(new Position(position.getX() + 1, position.getY() + var))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + var));
    }

    public boolean canCapture(Position position, Board board, Colour colour) {
        boolean canCapture = false;
        if (captureOption(position, board, colour, -1, -1) || captureOption(position, board, colour, -1, 1)
                || captureOption(position, board, colour, 1, -1) || captureOption(position, board, colour, 1, 1)) {
            canCapture = true;
        }
        return canCapture;
    }

    public boolean captureOption(Position position, Board board, Colour colour, int i, int j) {
        boolean option = false;
        Position option1 = new Position(position.getX() + i, position.getY() + j);
        if (board.getGrid().containsKey(option1)) {
            if (board.getGrid().get(option1).getColour() == colour.other()) {
                if (board.freePosition(new Position(option1.getX() + i, option1.getY() + j))) {
                    option = true;

                }
            }
        }
        return option;
    }

    @Override
    public List<Move> movesOnPosition(Position position, Board board, Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        int var = (colour == Colour.WHITE) ? 1 : -1;
        Position option1 = new Position(position.getX() + 1, position.getY() + var);
        Position option2 = new Position(position.getX() - 1, position.getY() + var);
        if (board.freePosition(option1)) {
            Move move = new Move(position, option1, null);
            possibleMoves.add(move);
        }
        if (board.freePosition(option2)) {
            Move move = new Move(position, option2, null);
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    @Override
    public List<Move> capturesOnPosition(Position position, Board board, Colour colour) {
        //TODO Implementation with more than 4 options
        List<Move> possibleMoves = new ArrayList<>();
        if (captureOption(position, board, colour, -1, -1)) {
            List<Position> interPos = new ArrayList<>();
            interPos.add(position);
            Move move = new Move(position, null, interPos);
            move = this.getCaptureMove(new Position(position.getX() - 2, position.getY() - 2), board, colour, move);
            possibleMoves.add(move);
            move.getInterPos().remove(0);
        } else if (captureOption(position, board, colour, -1, 1)) {
            List<Position> interPos = new ArrayList<>();
            interPos.add(position);
            Move move = new Move(position, null, interPos);
            move = this.getCaptureMove(new Position(position.getX() - 2, position.getY() + 2), board, colour, move);
            possibleMoves.add(move);
            move.getInterPos().remove(0);
        } else if (captureOption(position, board, colour, 1, -1)) {
            List<Position> interPos = new ArrayList<>();
            interPos.add(position);
            Move move = new Move(position, null, interPos);
            move = this.getCaptureMove(new Position(position.getX() + 2, position.getY() - 2), board, colour, move);
            possibleMoves.add(move);
            move.getInterPos().remove(0);
        } else if (captureOption(position, board, colour, 1, 1)) {
            List<Position> interPos = new ArrayList<>();
            interPos.add(position);
            Move move = new Move(position, null, interPos);
            move = this.getCaptureMove(new Position(position.getX() + 2, position.getY() + 2), board, colour, move);
            possibleMoves.add(move);
            move.getInterPos().remove(0);
        }
        return possibleMoves;
    }

    public Move getCaptureMove(Position position, Board board, Colour colour, Move move) {
        if (captureOption(position, board, colour, -1, -1)
                && !(new Position(position.getX() - 2, position.getY() - 2).equals(move.getInterPos().get(move.getInterPos().size() - 1)))) {
            move.getInterPos().add(position);
            move = this.getCaptureMove(new Position(position.getX() - 2, position.getY() - 2), board, colour, move);
        } else if (captureOption(position, board, colour, -1, 1)
                && !(new Position(position.getX() - 2, position.getY() + 2).equals(move.getInterPos().get(move.getInterPos().size() - 1)))) {
            move.getInterPos().add(position);
            move = this.getCaptureMove(new Position(position.getX() - 2, position.getY() + 2), board, colour, move);
        } else if (captureOption(position, board, colour, 1, -1)
                && !(new Position(position.getX() + 2, position.getY() - 2).equals(move.getInterPos().get(move.getInterPos().size() - 1)))) {
            move.getInterPos().add(position);
            move = this.getCaptureMove(new Position(position.getX() + 2, position.getY() - 2), board, colour, move);
        } else if (captureOption(position, board, colour, 1, 1)
                && !(new Position(position.getX() + 2, position.getY() + 2).equals(move.getInterPos().get(move.getInterPos().size() - 1)))) {
            move.getInterPos().add(position);
            move = this.getCaptureMove(new Position(position.getX() + 2, position.getY() + 2), board, colour, move);
        } else {
            move.setNewPos(position);
        }


        return move;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "M";
    }
}
