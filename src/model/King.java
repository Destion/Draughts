package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 23-10-15
 */
public class King implements Piece {
    private Colour colour;

    public King(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position currentPosition, Board board) {
        return board.freePosition(new Position(currentPosition.getX() + 1, currentPosition.getY() + 1))
                || board.freePosition(new Position(currentPosition.getX() - 1, currentPosition.getY() + 1))
                || board.freePosition(new Position(currentPosition.getX() + 1, currentPosition.getY() - 1))
                || board.freePosition(new Position(currentPosition.getX() - 1, currentPosition.getY() - 1));
    }

    public boolean canCapture(Position currentPosition, List<Position> lastCaptured, Board board) {
        return canCaptureInDirection(currentPosition, lastCaptured, board, -1, -1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, -1, 1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, 1, -1)
                || canCaptureInDirection(currentPosition, lastCaptured, board, 1, 1);
    }

    private boolean canCaptureInDirection(Position currentPosition, List<Position> lastCaptured, Board board, int x, int y) {
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


    private int numberOfFreeSpots(Position currentPosition, Board board, int x, int y) {
        int freeSpots = 0;
        while (board.freePosition(new Position(currentPosition.getX() + x, currentPosition.getY() + y))) {
            freeSpots++;
            x = (x >= 0) ? x + 1 : x - 1;
            y = (y >= 0) ? y + 1 : y - 1;
        }
        return freeSpots;
    }


    public List<Move> normalMoves(Position position, Board board) {
        List<Move> possibleMoves = new ArrayList<Move>();
        possibleMoves.addAll(this.normalMovesInDirection(position, board, -1, -1));
        possibleMoves.addAll(this.normalMovesInDirection(position, board, -1, 1));
        possibleMoves.addAll(this.normalMovesInDirection(position, board, 1, -1));
        possibleMoves.addAll(this.normalMovesInDirection(position, board, 1, 1));
        return possibleMoves;
    }

    private List<Move> normalMovesInDirection(Position currentPosition, Board board, int x, int y) {
        List<Move> freeSpots = new ArrayList<Move>();
        while (board.freePosition(new Position(currentPosition.getX() + x, currentPosition.getY() + y))) {
            freeSpots.add(new Move(currentPosition, new Position(currentPosition.getX() + x, currentPosition.getY() + y), null, null));
            x = (x >= 0) ? x + 1 : x - 1;
            y = (y >= 0) ? y + 1 : y - 1;
        }
        return freeSpots;
    }


    public List<Move> captureMoves(Position currentPosition, Board board) {
        List<Move> possibleMoves = new ArrayList<Move>();
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, -1, -1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, -1, 1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, 1, -1);
        this.singleCaptureInDirection(currentPosition, board, possibleMoves, 1, 1);
        return possibleMoves;
    }

    private void singleCaptureInDirection(Position currentPosition, Board board, List<Move> possibleMoves, int x, int y) {
        if (this.canCaptureInDirection(currentPosition, null, board, x, y)) {
            Position captured = this.getCapturedPosition(currentPosition, board, x, y);
            int options = this.numberOfFreeSpots(captured, board, x, y);
            for (int i = 1; i <= options; i++) {
                Move move = new Move(currentPosition, null, null, null);
                move.addCaptured(captured);
                Position option1 = new Position(captured.getX() + (i * x), captured.getY() + (i * y));
                if (this.canCapture(option1, move.getCaptured(), board)) {
                    this.multipleCaptures(option1, board, move, possibleMoves);
                } else {
                    move.setNewPos(option1);
                    possibleMoves.add(move);
                }
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
            Position captured = this.getCapturedPosition(currentPosition, board, x, y);
            int optionsOfSameCapture = this.numberOfFreeSpots(captured, board, x, y);
            for (int i = 1; i <= optionsOfSameCapture; i++) {
                Move move1 = new Move(move.getOldPos(), null, move.getInterPos(), move.getCaptured());
                move1.addCaptured(captured);
                Position nextPosition = new Position(captured.getX() + (i * x), captured.getY() + (i * y));
                if (this.canCapture(nextPosition, move1.getCaptured(), board)) {
                    this.multipleCaptures(nextPosition, board, move1, possibleMoves);
                } else {
                    move1.setNewPos(nextPosition);
                    possibleMoves.add(move1);
                }
            }
        }
    }

    private Position getCapturedPosition(Position currentPosition, Board board, int x, int y) {
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
