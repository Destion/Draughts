package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Rogier on 30-10-15.
 */
public class BoardToByte {

    public static final int EMPTY = 0;
    public static final int BLACK_MEN = 4;
    public static final int WHITE_MEN = 6;
    public static final int BLACK_KING = 5;
    public static final int WHITE_KING = 7;


    public static int getInteger(Piece piece) {
        int result;
        if (piece instanceof Man && piece.getColour() == Colour.BLACK) {
            result = BLACK_MEN;
        } else if (piece instanceof Man && piece.getColour() == Colour.WHITE) {
            result = WHITE_MEN;
        } else if (piece instanceof King && piece.getColour() == Colour.BLACK) {
            result = BLACK_KING;
        } else if (piece instanceof King && piece.getColour() == Colour.WHITE) {
            result = WHITE_KING;
        } else {
            result = EMPTY;
        }
        return result;
    }

    public static ArrayList<Integer> convertToBytes(Map<Position, Piece> grid) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int y = 10; y >= 1; y--) {
            for (int x = 1; x <= 10; x++) {
                if (y % 2 == 0 && x % 2 == 1) {
                    if (grid.containsKey(new Position(x, y))) {
                        array.add(getInteger(grid.get(new Position(x, y))));
                    } else {
                        array.add(EMPTY);
                    }
                } else if (y % 2 == 1 && x % 2 == 0) {
                    if (grid.containsKey(new Position(x, y))) {
                        array.add(getInteger(grid.get(new Position(x, y))));
                    } else {
                        array.add(EMPTY);
                    }
                }
            }
        }

        return array;
    }

}
