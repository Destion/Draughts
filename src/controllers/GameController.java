package controllers;


import model.*;

import java.util.List;
import java.util.Map;

public class GameController {
    private Board board;
    private Player player1;
    private Player player2;
    private int counter;
    public static final int PLAYERCOUNT = 2;

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();
    }

    public void game() {
        this.setup();
        this.play();
    }

    public void setup(){
        board.initializeBoard();
        counter = 0;
    }

    public void play(){
        this.temporaryTUI();
        while (!board.gameOver()) {
            if (counter % PLAYERCOUNT == 0) {
                //player1
                List<Move> possibleMoves = board.generatePossibleMoves(player1.getColour());
                player1.makeMove(board, possibleMoves);
                this.temporaryTUI();

            } else {
                //player2
                List<Move> possibleMoves = board.generatePossibleMoves(player2.getColour());
                player2.makeMove(board, possibleMoves);
                this.temporaryTUI();
            }
            counter++;
        }
    }

    public void temporaryTUI(){
        Map<Position, Piece> grid = board.getGrid();

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
        }
        System.out.println("    a  | b  | c  | d  | e  | f  | g  | h  | i  | j");

    }

}
