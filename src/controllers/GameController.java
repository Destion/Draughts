package controllers;


import model.Board;
import model.Move;
import model.Player;

import java.util.List;

public class GameController {
    private Board board;
    private Player player1;
    private Player player2;
    private int counter;

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setup(){
        board.initializeBoard();
        counter = 0;
    }

    public void play(){
        if(counter % 2 == 0){
            //player1
            List<Move> possibleMoves = board.generatePossibleMoves(player1.getColour());
            player1.makeMove(board,possibleMoves);
            this.temporaryTUI();
        } else{
            //player2
            List<Move> possibleMoves = board.generatePossibleMoves(player2.getColour());
            player2.makeMove(board,possibleMoves);
            this.temporaryTUI();
        }

    }

    public void temporaryTUI(){
        System.out.println();
    }

}
