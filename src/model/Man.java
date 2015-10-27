package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public class Man implements Piece {
    private Colour colour;
    private List<Move> options;

    public Man(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position position, Board board, Colour colour) {
        int var = (colour == Colour.WHITE) ? 1 : -1;
        return board.freePosition(new Position(position.getX() + 1, position.getY() + var))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + var));
    }

    public boolean canCapture(Position currentPosition, Position oldPosition, Board board, Colour colour) {
        boolean canCapture = false;
        if ((captureOption(currentPosition, oldPosition, board, colour, -1, -1) != null) || (captureOption(currentPosition, oldPosition, board, colour, -1, 1) != null)
                || (captureOption(currentPosition, oldPosition, board, colour, 1, -1)) != null || (captureOption(currentPosition, oldPosition, board, colour, 1, 1)) != null) {
            canCapture = true;
        }
        return canCapture;
    }

    public Position captureOption(Position currentPosition, Position oldPosition, Board board, Colour colour, int i, int j) {
        Position option = null;
        Position option1 = new Position(currentPosition.getX() + i, currentPosition.getY() + j);
        if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other()) {
            if (board.freePosition(new Position(option1.getX() + i, option1.getY() + j))
                    && !(new Position(option1.getX() + i, option1.getY() + j).equals(oldPosition))) {
                option = new Position(option1.getX() + i, option1.getY() + j);

            }
        }
        return option;
    }

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

    public List<Move> capturesOnPosition(Position currentPosition, Board board, Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        if (this.canCapture(currentPosition, null, board, colour)) {
            if (this.captureOption(currentPosition, null, board, colour, -1, -1) != null) {
                Move move1 = new Move(currentPosition, null, new ArrayList<Position>());
                if (canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), currentPosition, board, colour)) {
                    this.multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), board, colour, move1, possibleMoves);
                } else {
                    move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2));
                    possibleMoves.add(move1);
                }
            }
            if (this.captureOption(currentPosition, null, board, colour, -1, 1) != null) {
                Move move1 = new Move(currentPosition, null, new ArrayList<Position>());
                if (canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), currentPosition, board, colour)) {

                    this.multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), board, colour, move1, possibleMoves);
                } else {
                    move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2));
                    possibleMoves.add(move1);
                }
            }
            if (this.captureOption(currentPosition, null, board, colour, 1, -1) != null) {
                Move move1 = new Move(currentPosition, null, new ArrayList<Position>());
                if (canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), currentPosition, board, colour)) {
                    this.multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), board, colour, move1, possibleMoves);
                } else {
                    move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2));
                    possibleMoves.add(move1);
                }
            }
            if (this.captureOption(currentPosition, null, board, colour, 1, 1) != null) {
                Move move1 = new Move(currentPosition, null, new ArrayList<Position>());
                if (canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), currentPosition, board, colour)) {
                    this.multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), board, colour, move1, possibleMoves);
                } else {
                    move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2));
                    possibleMoves.add(move1);
                }
            }
        }
        return possibleMoves;
    }

    public void multipleCapture(Position currentPosition, Board board, Colour colour, Move move, List<Move> possibleMoves) {
        Position oldPosition;
        if (move.getInterPos().size() == 0) {
            oldPosition = move.getOldPos();

        } else {
            oldPosition = move.getInterPos().get(move.getInterPos().size() - 1);
        }
        move.getInterPos().add(currentPosition);
        if (this.captureOption(currentPosition, oldPosition, board, colour, -1, -1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos());

            if (this.canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), currentPosition, board, colour)) {
//                move1.getInterPos().add(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2));
                multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), board, colour, move1, possibleMoves);

            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }
        }
        if (this.captureOption(currentPosition, oldPosition, board, colour, -1, 1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos());

            if (this.canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), currentPosition, board, colour)) {
//                move1.getInterPos().add(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2));
                multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), board, colour, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2));
                possibleMoves.add(move1);
            }

        }
        if (this.captureOption(currentPosition, oldPosition, board, colour, 1, -1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos());

            if (this.canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), currentPosition, board, colour)) {
//                move1.getInterPos().add(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2));
                multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), board, colour, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }

        }
        if (this.captureOption(currentPosition, oldPosition, board, colour, 1, 1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos());

            if (this.canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), currentPosition, board, colour)) {
//                move1.getInterPos().add(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2));
                multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), board, colour, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2));
                possibleMoves.add(move1);
            }

        }

    }

    public Colour getColour() {
        return colour;
    }

    public String toString() {
        return "M";
    }
}
