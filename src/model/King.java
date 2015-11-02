package model;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private Colour colour;

    public King(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position position, Board board) {
        return board.freePosition(new Position(position.getX() + 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() + 1, position.getY() - 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() - 1));
    }

    public boolean canCapture(Position currentPosition, List<Position> lastCaptured, Board board) {
        return canCaptureWithDirection(currentPosition, lastCaptured, board, -1, -1)
                || canCaptureWithDirection(currentPosition, lastCaptured, board, -1, 1)
                || canCaptureWithDirection(currentPosition, lastCaptured, board, 1, -1)
                || canCaptureWithDirection(currentPosition, lastCaptured, board, 1, 1);
    }

    public boolean canCaptureWithDirection(Position currentPosition, List<Position> lastCaptured, Board board, int x, int y) {
        boolean canCapture = false;
        if (numberOfFreeSpots(currentPosition, board, x, y) >= 0) {
            int spacesInFront = numberOfFreeSpots(currentPosition, board, x, y);
            Position capturedPiece = new Position(currentPosition.getX() + x * (1 + spacesInFront), currentPosition.getY() + y * (1 + spacesInFront));
            boolean alreadyCaptured = false;
            if (lastCaptured != null) {
                for (Position capture : lastCaptured) {
                    if (capturedPiece.equals(capture)) {
                        alreadyCaptured = true;
                    }
                }
            }
            if (board.getGrid().containsKey(capturedPiece) && board.getGrid().get(capturedPiece).getColour() == colour.other() && !alreadyCaptured) {
                if (numberOfFreeSpots(new Position(capturedPiece.getX(), capturedPiece.getY()), board, x, y) > 0) {
                    canCapture = true;
                }

            }
        }
        return canCapture;
    }


    public int numberOfFreeSpots(Position currentPosition, Board board, int x, int y) {
        int result = 0;
        while (board.freePosition(new Position(currentPosition.getX() + x, currentPosition.getY() + y))) {
            result++;
            x = (x >= 0) ? x + 1 : x - 1;
            y = (y >= 0) ? y + 1 : y - 1;
        }
        return result;
    }


    public List<Move> movesOnPosition(Position position, Board board) {
        List<Move> possibleMoves = new ArrayList<Move>();
        possibleMoves.addAll(this.freePositions(position, board, -1, -1));
        possibleMoves.addAll(this.freePositions(position, board, -1, 1));
        possibleMoves.addAll(this.freePositions(position, board, 1, -1));
        possibleMoves.addAll(this.freePositions(position, board, 1, 1));
        return possibleMoves;
    }

    public List<Move> freePositions(Position currentPosition, Board board, int x, int y) {
        List<Move> freeSpots = new ArrayList<Move>();
        while (board.freePosition(new Position(currentPosition.getX() + x, currentPosition.getY() + y))) {
            freeSpots.add(new Move(currentPosition, new Position(currentPosition.getX() + x, currentPosition.getY() + y), null, null));
            x = (x >= 0) ? x + 1 : x - 1;
            y = (y >= 0) ? y + 1 : y - 1;
        }
        return freeSpots;
    }


    public List<Move> capturesOnPosition(Position currentPosition, Board board) {
        List<Move> possibleMoves = new ArrayList<Move>();
        this.captureOnPositionDirection(currentPosition, board, possibleMoves, -1, -1);
        this.captureOnPositionDirection(currentPosition, board, possibleMoves, -1, 1);
        this.captureOnPositionDirection(currentPosition, board, possibleMoves, 1, -1);
        this.captureOnPositionDirection(currentPosition, board, possibleMoves, 1, 1);
        return possibleMoves;
    }

    public void captureOnPositionDirection(Position currentPosition, Board board, List<Move> possibleMoves, int x, int y) {
        if (this.canCaptureWithDirection(currentPosition, null, board, x, y)) {
            Position captured = this.getCapturePosition(currentPosition, board, x, y);
            int options = this.numberOfFreeSpots(captured, board, x, y);
            for (int i = 1; i <= options; i++) {
                Move move = new Move(currentPosition, null, new ArrayList<Position>(), null);
                move.addCaptured(captured);
                Position option1 = new Position(captured.getX() + (i * x), captured.getY() + (i * y));
                if (this.canCapture(option1, move.getCaptured(), board)) {
                    this.multipleCapture(option1, board, move, possibleMoves);
                } else {
                    move.setNewPos(option1);
                    possibleMoves.add(move);
                }
            }
        }
    }

    public void multipleCapture(Position currentPosition, Board board, Move move, List<Move> possibleMoves) {
        move.getInterPos().add(currentPosition);
        this.multipleCaptureDirection(currentPosition, board, move, possibleMoves, -1, -1);
        this.multipleCaptureDirection(currentPosition, board, move, possibleMoves, -1, 1);
        this.multipleCaptureDirection(currentPosition, board, move, possibleMoves, 1, -1);
        this.multipleCaptureDirection(currentPosition, board, move, possibleMoves, 1, 1);
    }

    public void multipleCaptureDirection(Position currentPosition, Board board, Move move, List<Move> possibleMoves, int x, int y) {
        if (this.canCaptureWithDirection(currentPosition, move.getCaptured(), board, x, y)) {
            Position captured = this.getCapturePosition(currentPosition, board, x, y);
            int optionsOfSameCapture = this.numberOfFreeSpots(captured, board, x, y);
            for (int i = 1; i <= optionsOfSameCapture; i++) {
                Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
                move1.addCaptured(captured);
                Position nextPosition = new Position(captured.getX() + (i * x), captured.getY() + (i * y));
                if (this.canCapture(nextPosition, move1.getCaptured(), board)) {
                    this.multipleCapture(nextPosition, board, move1, possibleMoves);
                } else {
                    move1.setNewPos(nextPosition);
                    possibleMoves.add(move1);
                }
            }
        }
    }

    public Position getCapturePosition(Position currentPosition, Board board, int x, int y) {
        int spacesInFront = numberOfFreeSpots(currentPosition, board, x, y);
        return new Position(currentPosition.getX() + x * (1 + spacesInFront), currentPosition.getY() + y * (1 + spacesInFront));
    }

    public Colour getColour() {
        return colour;
    }

    public String toString() {
        return "K";
    }
}
