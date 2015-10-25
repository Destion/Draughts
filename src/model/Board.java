package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {
    private Map<Position, Piece> grid;
    public static final int BOARDSIZE = 10;
    public static final int NUMPIECES = 20;
    public int numberWhite;
    public int numberBlack;

    public Map<Position, Piece> getGrid() {
        return grid;
    }

    public void initializeBoard() {
        grid = new HashMap<Position, Piece>();
        for (int x = 1; x <= BOARDSIZE; x++) {
            for (int y = 1; y <= BOARDSIZE; y++) {
                if (x % 2 != 0) {
                    if (y == 1 || y == 3) {
                        grid.put(new Position(x, y), new Men(Colour.WHITE));
                    } else if (y == 7 || y == 9) {
                        grid.put(new Position(x, y), new Men(Colour.BLACK));
                    }
                } else {
                    if (y == 2 || y == 4) {
                        grid.put(new Position(x, y), new Men(Colour.WHITE));
                    } else if (y == 8 || y == 10) {
                        grid.put(new Position(x, y), new Men(Colour.BLACK));
                    }
                }

            }
        }
        numberWhite = 20;
        numberBlack = 20;
    }

    public void move(Move move) {
        Position oldPosition = this.getPositionOnBoard(move.getOldPos());
        Piece piece = grid.get(oldPosition);
        if (piece.validMove(move, this)) {
            grid.put(move.getNewPos(), grid.get(oldPosition));
            grid.remove(oldPosition);
            if (move.getCaptured().size() > 0) {
                for (Position position : move.getCaptured()) {
                    Position tmp = this.getPositionOnBoard(position);
                    Piece tmpPiece = grid.get(tmp);
                    if (tmpPiece.getColour() == Colour.WHITE) {
                        numberWhite--;
                    } else {
                        numberBlack--;
                    }
                    grid.remove(tmp);
                }
            }
            if ((piece.getColour() == Colour.WHITE && move.getNewPos().getY() == 10)
                    || (piece.getColour() == Colour.BLACK && move.getNewPos().getY() == 1)) {
                this.promotePiece(move.getNewPos(), piece);
            }
        }

    }

    private void promotePiece(Position position, Piece piece) {
        grid.remove(position);
        grid.put(position, new King(piece.getColour()));
    }

    public List<Move> generatePossibleMoves(Colour colour) {
        List<Move> possibleMoves = new ArrayList<Move>();
            for (Position position : grid.keySet()){
                if(grid.get(position).getColour() == colour){
                    grid.get(position).canMove(position,this);
                }
            }

        return possibleMoves;
    }

    public Position getPositionOnBoard(Position pos) {
        Position pos1 = null;
        for (Position tmp : grid.keySet()) {
            if (tmp.equals(pos)) {
                pos1 = tmp;
            }
        }
        return pos1;
    }

    public boolean hasWinner() {
        return numberWhite == 0 || numberBlack == 0;
    }


    public Colour winner() {
        Colour winner;
        if (numberBlack == 0) {
            winner = Colour.WHITE;
        } else if (numberWhite == 0) {
            winner = Colour.BLACK;
        } else {
            winner = null;
        }
        return winner;
    }

    public boolean draw() {
        return false;
    }

    public boolean gameOver() {
        return this.hasWinner() || this.draw();
    }

    // does not work this way
    // a10 b10 c10 d10 e10 f10 g10 h10 i10
    // a9  b9  c9  d9  e9  f9  g9  h9  i9
    // a8  b8  c8  d8  e8  f8  g8  h8  i8
    // a7  b7  c7  d7  e7  f7  g7  h7  i7
    // a6  b6  c6  d6  e6  f6  g6  h6  i6
    // a5  b5  c5  d5  e5  f5  g5  h5  i5
    // a4  b4  c4  d4  e4  f4  g4  h4  i4
    // a3  b3  c3  d3  e3  f3  g3  h3  i3
    // a2  b2  c2  d2  e2  f2  g2  h2  i2
    // a1  b1  c1  d1  e1  f1  g1  h1  i1
}
