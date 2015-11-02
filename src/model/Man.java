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
        if (canCaptureInDirection(currentPosition, lastCaptured, board, -1, -1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, -1, 1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, 1, -1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, 1, 1)) {
            canCapture = true;
        }
        return canCapture;
    }

    private boolean canCaptureInDirection(Position currentPosition, List<Position> lastCaptured, Board board, int i, int j) {
        boolean canCapture = false;
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
                canCapture = true;
            }
        }
        return canCapture;
    }

    public List<Move> normalMoves(Position position, Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        int direction = (colour == Colour.WHITE) ? 1 : -1;
        Position option1 = new Position(position.getX() + 1, position.getY() + direction);
        Position option2 = new Position(position.getX() - 1, position.getY() + direction);
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

    public List<Move> captureMoves(Position currentPosition, Board board) {
        List<Move> possibleMoves = new ArrayList<>();
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, -1, -1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, -1, 1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, 1, -1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, 1, 1);
        return possibleMoves;
    }

    private void singleCaptureInDirection(Position currentPosition, Board board, List<Move> possibleMoves, int x, int y) {
        if (this.canCaptureInDirection(currentPosition, null, board, x, y)) {
            Move move = new Move(currentPosition, null, null, null);
            move.addCaptured(new Position(currentPosition.getX() + x, currentPosition.getY() + y));
            Position newPosition = new Position(currentPosition.getX() + (2 * x), currentPosition.getY() + (2 * y));
            if (canCapture(newPosition, move.getCaptured(), board)) {
                this.multipleCaptures(newPosition, board, move, possibleMoves);
            } else {
                move.setNewPos(newPosition);
                possibleMoves.add(move);
            }
        }
    }

    private void multipleCaptures(Position currentPosition, Board board, Move move, List<Move> possibleMoves) {
        move.addInterPosition(currentPosition);
        this.multipleCapturesInDirection(currentPosition, board, move, possibleMoves, -1, -1);
        this.multipleCapturesInDirection(currentPosition, board, move, possibleMoves, -1, 1);
        this.multipleCapturesInDirection(currentPosition, board, move, possibleMoves, 1, -1);
        this.multipleCapturesInDirection(currentPosition, board, move, possibleMoves, 1, 1);
    }

    private void multipleCapturesInDirection(Position currentPosition, Board board, Move move, List<Move> possibleMoves, int x, int y) {
        if (this.canCaptureInDirection(currentPosition, move.getCaptured(), board, x, y)) {
            Move newMove = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
            newMove.addCaptured(new Position(currentPosition.getX() + x, currentPosition.getY() + y));
            Position newPosition = new Position(currentPosition.getX() + (2 * x), currentPosition.getY() + (2 * y));
            if (this.canCapture(newPosition, newMove.getCaptured(), board)) {
                multipleCaptures(newPosition, board, newMove, possibleMoves);
            } else {
                newMove.setNewPos(newPosition);
                possibleMoves.add(newMove);
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
