package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board extends java.util.Observable {
    private Map<Position, Piece> grid;
    public static final int BOARDSIZE = 10;
    public static final int NUMPIECES = 20;
    private int numberWhite;
    private int numberBlack;
    private Colour winner;
    private int threeKingCounter;
    private int twoKingOneManCounter;
    private static Map<Position, Integer> heuristics = new HashMap<>();


    public Map<Position, Piece> getGrid() {
        return grid;
    }

    public void initializeBoard() {
        grid = new HashMap<>();
        for (int y = 1; y <= 4; y++) {
            for (int x = 1; x <= BOARDSIZE; x++) {
                if (y % 2 == 1) {
                    if (x % 2 == 1) {
                        grid.put(new Position(x, y), new Man(Colour.WHITE));
                    }
                } else {
                    if (x % 2 == 0) {
                        grid.put(new Position(x, y), new Man(Colour.WHITE));
                    }
                }

            }
        }
        for (int y = 7; y <= 10; y++) {
            for (int x = 1; x <= BOARDSIZE; x++) {
                if (y % 2 == 1) {
                    if (x % 2 == 1) {
                        grid.put(new Position(x, y), new Man(Colour.BLACK));
                    }
                } else {
                    if (x % 2 == 0) {
                        grid.put(new Position(x, y), new Man(Colour.BLACK));
                    }
                }

            }
        }
        numberWhite = NUMPIECES;
        numberBlack = NUMPIECES;
        threeKingCounter = 0;
        twoKingOneManCounter = 0;
        this.setChanged();
        this.notifyObservers(grid);
    }


    public Map<Position, Piece> deepCopy() {
        Map<Position, Piece> result = new HashMap<>();
        for (Position position : grid.keySet()) {
            if (grid.get(position) instanceof Man) {
                result.put(new Position(position.getX(), position.getY()), new Man(grid.get(position).getColour()));
            } else if (grid.get(position) instanceof King) {
                result.put(new Position(position.getX(), position.getY()), new King(grid.get(position).getColour()));
            }
        }
        return result;
    }

    public void setGrid(Map<Position, Piece> grid) {
        this.grid = grid;
    }

    public void move(Move move) {
        Position oldPosition = move.getOldPos();
        Piece piece = grid.get(oldPosition);

        grid.put(move.getNewPos(), grid.get(oldPosition));
        grid.remove(oldPosition);
        if (move.getCaptured() != null && move.getCaptured().size() > 0) {
            for (Position position : move.getCaptured()) {
                Piece tmpPiece = grid.get(position);
                if (tmpPiece != null && tmpPiece.getColour() == Colour.WHITE) {
                    numberWhite--;
                } else if (tmpPiece != null && tmpPiece.getColour() == Colour.BLACK) {
                    numberBlack--;
                } else {
                    System.out.println("Huh " + position);
                }
                grid.remove(position);
            }
        }
        if ((piece.getColour() == Colour.WHITE && move.getNewPos().getY() == 10)
                || (piece.getColour() == Colour.BLACK && move.getNewPos().getY() == 1)) {
            this.promotePiece(move.getNewPos(), piece);
        }
        this.setChanged();
        this.notifyObservers(move);
    }

    public void computerMove(Move move) {
        Position oldPosition = move.getOldPos();
        Piece piece = grid.get(oldPosition);

        grid.put(move.getNewPos(), grid.get(oldPosition));
        grid.remove(oldPosition);
        if (move.getCaptured() != null && move.getCaptured().size() > 0) {
            for (Position position : move.getCaptured()) {
                Piece tmpPiece = grid.get(position);
                if (tmpPiece != null && tmpPiece.getColour() == Colour.WHITE) {
                    numberWhite--;
                } else if (tmpPiece != null && tmpPiece.getColour() == Colour.BLACK) {
                    numberBlack--;
                } else {
                    System.out.println("Huh");
                }
                grid.remove(position);
            }
        }

        if ((piece.getColour() == Colour.WHITE && move.getNewPos().getY() == 10)
                || (piece.getColour() == Colour.BLACK && move.getNewPos().getY() == 1)) {
            this.promotePiece(move.getNewPos(), piece);
        }

    }

    public void promotePiece(Position position, Piece piece) {
        grid.remove(position);
        grid.put(position, new King(piece.getColour()));
    }

    public List<Move> generatePossibleMoves(Colour colour) {
        List<Move> possibleMoves = new ArrayList<>();
        boolean mustCapture = false;
        for (Position position : grid.keySet()) {
            if (grid.get(position).getColour() == colour) {
                if (grid.get(position).canCapture(position, null, this)) {
                    possibleMoves.addAll(grid.get(position).captureMoves(position, this));
                    mustCapture = true;
                }
            }
        }
        if (!mustCapture) {
            for (Position position : grid.keySet()) {
                if (grid.get(position).getColour() == colour) {
                    if (grid.get(position).canMove(position, this)) {
                        possibleMoves.addAll(grid.get(position).normalMoves(position, this));
                    }
                }
            }
        }
        int longestChain = 0;
        for (Move move : possibleMoves) {
            if (move.getInterPos() != null && move.getInterPos().size() > longestChain) {
                longestChain = move.getInterPos().size();
            }
        }
        List<Move> possibleCaptureMoves = new ArrayList<Move>();
        for (Move move : possibleMoves) {
            if (move.getInterPos() != null && move.getInterPos().size() == longestChain) {
                possibleCaptureMoves.add(move);
            }
        }
        return possibleCaptureMoves.size() > 0 ? possibleCaptureMoves : possibleMoves;
    }

    public boolean hasWinner() {
        return Colour.WHITE == winner() || Colour.BLACK == winner();
    }


    public Colour winner() {
        if (numberBlack == 0) {
            winner = Colour.WHITE;
        } else if (numberWhite == 0) {
            winner = Colour.BLACK;
        } else {
            winner = null;
        }
        return winner;
    }

    public Colour getWinner() {
        return winner;
    }

    public void setWinner(Colour winner) {
        this.winner = winner;
    }

    public boolean freePosition(Position position) {
        return !grid.containsKey(position) && position.getX() >= 1 && position.getX() <= 10 && position.getY() >= 1 && position.getY() <= 10;
    }

    public boolean draw() {
        boolean result = false;
        if (grid.size() < 4) {
            List<Piece> pieces = new ArrayList<Piece>();
            for (Position position : grid.keySet()) {
                pieces.add(grid.get(position));
            }
            if (pieces.size() == 2 && pieces.get(0) instanceof King && pieces.get(1) instanceof King) {
                result = true;

            }
            if (pieces.size() == 3 && pieces.get(0) instanceof King && pieces.get(1) instanceof King && pieces.get(2) instanceof King) {
                if (threeKingCounter > 4) {
                    result = true;
                } else {
                    threeKingCounter++;
                }
            }
            if (pieces.size() == 3 && ((pieces.get(0) instanceof King && pieces.get(1) instanceof King)
                    || (pieces.get(0) instanceof King && pieces.get(2) instanceof King)
                    || (pieces.get(1) instanceof King && pieces.get(2) instanceof King))) {
                if (twoKingOneManCounter > 15) {
                    result = true;
                } else {
                    twoKingOneManCounter++;
                }
            }
        }


        return result;
    }

    public void emptyMap() {
        this.grid = null;
    }

    public boolean gameOver() {
        return this.hasWinner() || this.draw();
    }

    public void printBoard() {

        for (int j = Board.BOARDSIZE; j >= 1; j--) {

            String s = j + ". ";
            s = (j < 10) ? s + " " : s;
            for (int i = 1; i <= Board.BOARDSIZE; i++) {
                if (grid.containsKey(new Position(i, j))) {
                    s = s + grid.get(new Position(i, j)).getColour().toString() + grid.get(new Position(i, j)).toString() + " | ";
                } else {
                    s = s + "   | ";
                }
            }
            System.out.println(s);
            System.out.println("----------------------------------------------------");
        }
        System.out.println("    a  | b  | c  | d  | e  | f  | g  | h  | i  | j");

    }

    public HashMap<Position, Piece> bytetoMap(ArrayList<Integer> ints){

        HashMap<Position, Piece> res = new HashMap<>();

        for (int i=10; i>0; i--){

            for (int j=0; j<15; j+=3){
                String temp = "";
                temp += ints.get(0);
                temp += ints.get(1);
                temp += ints.get(2);

                Piece tempPiece = null;
                Position tempPos = null;

                if (!temp.equals("000")){
                    if (temp.equals("001")){
                        tempPiece = new Man(Colour.BLACK);
                    } else if (temp.equals("011")){
                        tempPiece = new Man(Colour.WHITE);
                    } else if (temp.equals("101")){
                        tempPiece = new King(Colour.BLACK);
                    } else if (temp.equals("111")){
                        tempPiece = new King(Colour.WHITE);
                    }
                }
                if (i%2 == 0){
                    if (j>=0 && j<=2){
                        tempPos = new Position(2, i);
                    } else if (j>=3 && j<=5) {
                        tempPos = new Position(4, i);
                    } else if (j>=6 && j<=8) {
                        tempPos = new Position(6, i);
                    } else if (j>=8 && j<=11) {
                        tempPos = new Position(8, i);
                    } else if (j>=12 && j<=14) {
                        tempPos = new Position(10, i);
                    }
                } else {
                    if (j>=0 && j<=2){
                        tempPos = new Position(1, i);
                    } else if (j>=3 && j<=5) {
                        tempPos = new Position(3, i);
                    } else if (j>=6 && j<=8) {
                        tempPos = new Position(5, i);
                    } else if (j>=8 && j<=11){
                        tempPos = new Position(7, i);
                    } else if (j>=12 && j<=14) {
                        tempPos = new Position(9, i);
                    }
                }
                if ((tempPiece != null) && (tempPos != null)) {
                    res.put(tempPos, tempPiece);
                }
                ints.remove(2);
                ints.remove(1);
                ints.remove(0);
            }
        }
        return res;
    }

    public int getNumberWhite() {
        return numberWhite;
    }

    public int getNumberBlack() {
        return numberBlack;
    }

    public void setNumberWhite(int numberWhite) {
        this.numberWhite = numberWhite;
    }

    public void setNumberBlack(int numberBlack) {
        this.numberBlack = numberBlack;
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
