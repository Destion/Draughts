package model;

import java.util.ArrayList;
import java.util.List;

public class Man implements Piece {
    private Colour colour;

    public Man(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position position, Board board) {
        int var = (colour == Colour.WHITE) ? 1 : -1;
        return board.freePosition(new Position(position.getX() + 1, position.getY() + var))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + var));
    }

    public boolean canCapture(Position currentPosition, List<Position> lastCaptured, Board board) {
        boolean canCapture = false;
        if ((captureOption(currentPosition, lastCaptured, board, -1, -1) != null) || (captureOption(currentPosition, lastCaptured, board, -1, 1) != null)
                || (captureOption(currentPosition, lastCaptured, board, 1, -1)) != null || (captureOption(currentPosition, lastCaptured, board, 1, 1)) != null) {
            canCapture = true;
        }
        return canCapture;
    }

    public Position captureOption(Position currentPosition, List<Position> lastCaptured, Board board, int i, int j) {
        Position option = null;
        Position option1 = new Position(currentPosition.getX() + i, currentPosition.getY() + j);
        boolean alreadyCaptured = false;
        if (lastCaptured != null) {
            for (Position capture : lastCaptured) {
                if (option1.equals(capture)) {
                    alreadyCaptured = true;
                }
            }

        }
        if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other() && !alreadyCaptured) {
            if (board.freePosition(new Position(option1.getX() + i, option1.getY() + j))) {
                option = new Position(option1.getX() + i, option1.getY() + j);
            }
        }
        return option;
    }

    public List<Move> movesOnPosition(Position position, Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        int var = (colour == Colour.WHITE) ? 1 : -1;
        Position option1 = new Position(position.getX() + 1, position.getY() + var);
        Position option2 = new Position(position.getX() - 1, position.getY() + var);
        if (board.freePosition(option1)) {
            Move move = new Move(position, option1, null, null);
            possibleMoves.add(move);
        }
        if (board.freePosition(option2)) {
            Move move = new Move(position, option2, null, null);
            possibleMoves.add(move);
        }
        return possibleMoves;
    }

    public List<Move> capturesOnPosition(Position currentPosition, Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        if (this.captureOption(currentPosition, null, board, -1, -1) != null) {
            Move move1 = new Move(currentPosition, null, new ArrayList<Position>(), null);
            move1.addCaptured(new Position(currentPosition.getX() - 1, currentPosition.getY() - 1));
            if (canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2),
                    move1.getCaptured(), board)) {
                this.multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }
        }
        if (this.captureOption(currentPosition, null, board, -1, 1) != null) {
            Move move1 = new Move(currentPosition, null, new ArrayList<Position>(), null);
            move1.addCaptured(new Position(currentPosition.getX() - 1, currentPosition.getY() + 1));
            if (canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), move1.getCaptured(), board)) {
                this.multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2));
                possibleMoves.add(move1);
            }
        }
        if (this.captureOption(currentPosition, null, board, 1, -1) != null) {
            Move move1 = new Move(currentPosition, null, new ArrayList<Position>(), null);
            move1.addCaptured(new Position(currentPosition.getX() + 1, currentPosition.getY() - 1));
            if (canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), move1.getCaptured(), board)) {
                this.multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }
        }
        if (this.captureOption(currentPosition, null, board, 1, 1) != null) {
            Move move1 = new Move(currentPosition, null, new ArrayList<Position>(), null);
            move1.addCaptured(new Position(currentPosition.getX() + 1, currentPosition.getY() + 1));
            if (canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), move1.getCaptured(), board)) {
                this.multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2));
                possibleMoves.add(move1);
            }
        }
        return possibleMoves;
    }

    public void multipleCapture(Position currentPosition, Board board, Move move, List<Move> possibleMoves) {
        move.getInterPos().add(currentPosition);
        if (this.captureOption(currentPosition, move.getCaptured(), board, -1, -1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
            move1.addCaptured(new Position(currentPosition.getX() - 1, currentPosition.getY() - 1));
            if (this.canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), move1.getCaptured(), board)) {
                multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2), board, move1, possibleMoves);

            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }
        }
        if (this.captureOption(currentPosition, move.getCaptured(), board, -1, 1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
            move1.addCaptured(new Position(currentPosition.getX() - 1, currentPosition.getY() + 1));
            if (this.canCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), move1.getCaptured(), board)) {
                multipleCapture(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() - 2, currentPosition.getY() + 2));
                possibleMoves.add(move1);
            }

        }
        if (this.captureOption(currentPosition, move.getCaptured(), board, 1, -1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
            move1.addCaptured(new Position(currentPosition.getX() + 1, currentPosition.getY() - 1));
            if (this.canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), move1.getCaptured(), board)) {
                multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2), board, move1, possibleMoves);
            } else {
                move1.setNewPos(new Position(currentPosition.getX() + 2, currentPosition.getY() - 2));
                possibleMoves.add(move1);
            }

        }
        if (this.captureOption(currentPosition, move.getCaptured(), board, 1, 1) != null) {
            Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
            move1.addCaptured(new Position(currentPosition.getX() + 1, currentPosition.getY() + 1));
            if (this.canCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), move1.getCaptured(), board)) {
                multipleCapture(new Position(currentPosition.getX() + 2, currentPosition.getY() + 2), board, move1, possibleMoves);
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
