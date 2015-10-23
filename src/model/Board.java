package model;

import java.util.HashMap;


public class Board {
    private HashMap<Position, Piece> board;
    public static final int BOARDSIZE = 10;
    public static final int NUMPIECES = 20;

    public HashMap<Position, Piece> getBoard(){
        return board;
    }

    public void setBoard(HashMap<Position,Piece> board){
        this.board = board;
    }

    public void initializeBoard(){
        for (int x = 1; x <= BOARDSIZE; x++){
            for (int y = 1; y <= BOARDSIZE; y++){
                if (x % 2 != 0 ){
                    if (y == 1 || y == 3) {
                        board.put(new Position(x, y), new Men(Colour.WHITE));
                    } else if (y == 7 || y ==9){
                        board.put(new Position(x, y), new Men(Colour.BLACK));
                    } else {
                        board.put(new Position(x, y), null);
                    }
                } else {
                    if (y == 2 || y == 4) {
                        board.put(new Position(x, y), new Men(Colour.WHITE));
                    } else if (y == 8 || y ==10){
                        board.put(new Position(x, y), new Men(Colour.BLACK));
                    } else {
                        board.put(new Position(x, y), null);
                    }
                }

            }
        }

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
