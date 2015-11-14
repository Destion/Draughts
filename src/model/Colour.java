package model;

public enum Colour {
    BLACK, WHITE;


    public Colour other() {
        return this == BLACK ? WHITE : BLACK;
    }

    @Override
    public String toString() {
        return (this == BLACK) ? "B" : "W";
    }

}
