package model;

/**
 * Created by Rogier on 23-10-15.
 */
public enum Colour {
    BLACK, WHITE;


    @Override
    public String toString() {
        return (this == BLACK) ? "B" : "W";
    }
}
