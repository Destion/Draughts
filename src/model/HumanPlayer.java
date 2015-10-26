package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rogier on 24-10-15.
 */
public class HumanPlayer extends Player {
    private BufferedReader reader;

    public HumanPlayer(String name, Colour colour) {
        super(name, colour);
    }

    public Move determineMove(Board board, List<Move> possibleMoves) {
        int i = possibleMoves.size();
        this.temporaryTUI(possibleMoves);
//    TODO System.in
        int choice = this.getInput();
        return null;
    }

    public void temporaryTUI(List<Move> possibleMoves) {
        int i = 0;
        for (Move move : possibleMoves) {
            String s = i + " " + move.getOldPos().toString() + " ";
            if (move.getInterPos() != null) {
                for (Position position : move.getInterPos()) {
                    s = s + position.toString() + " ";
                }
            }
            s = s + move.getNewPos().toString();
            System.out.println(s);
            i++;
        }

    }

    public int getInput() {
        boolean intRead = false;
        int choice = 0;
        do {
            try {
                choice = Integer.parseInt(reader.readLine());
                intRead = true;
            } catch (IOException | NumberFormatException e) {
                System.out.println("ERROR: Enter a number");
            }
        } while (!intRead);
        return choice;

    }
}
