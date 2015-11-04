package model;

import java.util.ArrayList;
import java.util.Map;

public class BoardToByte {

    public static final byte EMPTY = 000;
    public static final byte BLACK_MEN = 100;
    public static final byte WHITE_MEN = 110;
    public static final byte BLACK_KING = 101;
    public static final byte WHITE_KING = 111;


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

    public static byte getByte(Piece piece) {
        byte result;
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


    public static ArrayList<Integer> convertToInteger(Map<Position, Piece> grid) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int y = 10; y >= 1; y--) {
            for (int x = 1; x <= 10; x++) {
                if (y % 2 == 0 && x % 2 == 0) {
                    if (grid.containsKey(new Position(x, y))) {
                        array.add(getInteger(grid.get(new Position(x, y))));
                    } else {
                        array.add((int) EMPTY);
                    }
                } else if (y % 2 == 1 && x % 2 == 1) {
                    if (grid.containsKey(new Position(x, y))) {
                        array.add(getInteger(grid.get(new Position(x, y))));
                    } else {
                        array.add((int) EMPTY);
                    }
                }
            }
        }
        return array;
    }

    public static byte[] convertToBytes(Map<Position, Piece> grid) {
        byte[] array = new byte[50];
        int i = 0;
        for (int y = 10; y >= 1; y--) {
            for (int x = 1; x <= 10; x++) {
                if (y % 2 == 0 && x % 2 == 0) {
                    if (grid.containsKey(new Position(x, y))) {
                        array[i] = getByte(grid.get(new Position(x, y)));
                        i++;
                    } else {
                        array[i] = EMPTY;
                        i++;
                    }
                } else if (y % 2 == 1 && x % 2 == 1) {
                    if (grid.containsKey(new Position(x, y))) {
                        array[i] = getByte(grid.get(new Position(x, y)));
                        i++;
                    } else {
                        array[i] = EMPTY;
                        i++;
                    }
                }

            }
        }
        return array;
    }

}
