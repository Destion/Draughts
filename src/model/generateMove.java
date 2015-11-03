package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogier on 03-11-15
 */
public class generateMove {


    public static Move generateMove(Map<Position, Piece> oldBoard, Map<Position, Piece> newBoard, Colour colour) {
        List<Position> oldList = new ArrayList<>();
        List<Position> newList = new ArrayList<>();
        List<Position> difference = new ArrayList<>();

        for (Position position : oldBoard.keySet()) {
            if (oldBoard.get(position).getColour() == colour) {
                oldList.add(position);
            }

        }

        for (Position position : newBoard.keySet()) {
            if (newBoard.get(position).getColour() == colour) {
                newList.add(position);
            }
        }

        for (Position position : newList) {
            if (!oldList.contains(position)) {
                difference.add(position);
            }
        }

        for (Position position : oldList) {
            if (!newList.contains(position)) {
                difference.add(position);
            }
        }

        Move move = null;
        if (difference.size() >= 2) {
            move = new Move(difference.get(0), difference.get(1), null, null);
        }


        return null;
    }
}
