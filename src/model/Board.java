package model;

import java.util.HashMap;


public class Board {
    private HashMap<Position, Piece> board;
    public static final int BOARDSIZE = 10;

    public HashMap<Position, Piece> getBoard(){
        return board;
    }

    public void setBoard(HashMap<Position,Piece> board){
        this.board = board;
    }

    public void initializeBoard(){
        for (int i = 1; i <= BOARDSIZE; i++){
            board.put(new Position(XAxis.A, i), null);
            board.put(new Position(XAxis.B, i), null);
            board.put(new Position(XAxis.C, i), null);
            board.put(new Position(XAxis.D, i), null);
            board.put(new Position(XAxis.E, i), null);
            board.put(new Position(XAxis.F, i), null);
            board.put(new Position(XAxis.G, i), null);
            board.put(new Position(XAxis.H, i), null);
            board.put(new Position(XAxis.I, i), null);
            board.put(new Position(XAxis.J, i), null);
        }
        // put white and black men on position.
    }
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
