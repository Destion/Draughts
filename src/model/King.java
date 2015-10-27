package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rogier on 23-10-15.
 */
public class King implements Piece {
    private Colour colour;

    public King(Colour colour) {
        this.colour = colour;
    }

    public boolean canMove(Position position, Board board, Colour colour) {
        return board.freePosition(new Position(position.getX() + 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() + 1))
                || board.freePosition(new Position(position.getX() + 1, position.getY() - 1))
                || board.freePosition(new Position(position.getX() - 1, position.getY() - 1));
    }

    public boolean canCapture(Position currentPosition, Position oldPosition, Board board, Colour colour) {
        boolean canCapture = false;
        if (numberOfFreeSpots(currentPosition, board, -1, -1) >= 0) {
            int spacesInFront = numberOfFreeSpots(currentPosition, board, -1, -1);
            Position option1 = new Position(currentPosition.getX() - (1 + spacesInFront), currentPosition.getY() - (1 + spacesInFront));
            if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other()) {
                if (numberOfFreeSpots(new Position(option1.getX(), option1.getY()), board, -1, -1) > 0) {
                    canCapture = true;
                }

            }
        }
        if (numberOfFreeSpots(currentPosition, board, -1, 1) >= 0) {
            int spacesInFront = numberOfFreeSpots(currentPosition, board, -1, 1);
            Position option1 = new Position(currentPosition.getX() - (1 + spacesInFront), currentPosition.getY() + (1 + spacesInFront));
            if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other()) {
                if (numberOfFreeSpots(new Position(option1.getX(), option1.getY()), board, -1, 1) > 0) {
                    canCapture = true;
                }

            }
        }
        if (numberOfFreeSpots(currentPosition, board, 1, -1) >= 0) {
            int spacesInFront = numberOfFreeSpots(currentPosition, board, 1, -1);
            Position option1 = new Position(currentPosition.getX() + (1 + spacesInFront), currentPosition.getY() - (1 + spacesInFront));
            if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other()) {
                if (numberOfFreeSpots(new Position(option1.getX(), option1.getY()), board, 1, -1) > 0) {
                    canCapture = true;
                }

            }
        }
        if (numberOfFreeSpots(currentPosition, board, 1, 1) >= 0) {
            int spacesInFront = numberOfFreeSpots(currentPosition, board, 1, 1);
            Position option1 = new Position(currentPosition.getX() + (1 + spacesInFront), currentPosition.getY() + (1 + spacesInFront));
            if (board.getGrid().containsKey(option1) && board.getGrid().get(option1).getColour() == colour.other()) {
                if (numberOfFreeSpots(new Position(option1.getX(), option1.getY()), board, 1, 1) > 0) {
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


    public List<Move> movesOnPosition(Position position, Board board, Colour colour) {
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
            freeSpots.add(new Move(currentPosition, new Position(currentPosition.getX() + x, currentPosition.getY() + y), null));
            x = (x >= 0) ? x + 1 : x - 1;
            y = (y >= 0) ? y + 1 : y - 1;
        }
        return freeSpots;
    }


    public List<Move> capturesOnPosition(Position position, Board board, Colour colour) {
        //        TODO Implementation

        return null;
    }

    public Colour getColour() {
        return colour;
    }

    public String toString() {
        return "K";
    }
}
