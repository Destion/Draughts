package controllers;


import model.*;
import view.BoardView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

public class GameController implements ActionListener, MouseListener {
    private Board board;
    private Player player1;
    private Player player2;
    private int counter;
    public static final int PLAYERCOUNT = 2;
    private BoardView view;
    private boolean canMove;
    private int mouseCount;
    private Move input;
    private Position oldPosition;

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();


        view = new BoardView();
        board.addObserver(view);
        this.setup();
        canMove = false;
    }

    public void run() {
        this.play();
        this.displayWinner();
    }

    public void setup(){
        board.initializeBoard();
        counter = 0;
    }

    public void play(){
        while (!board.gameOver()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter % PLAYERCOUNT == 0) {
                List<Move> possibleMoves = board.generatePossibleMoves(player1.getColour());
                if (possibleMoves.size() == 0) {
                    board.setWinner(player2.getColour());
                    break;
                }
                this.move(player1, possibleMoves);
            } else {
                //this.temporaryTUI(player2);
                List<Move> possibleMoves = board.generatePossibleMoves(player2.getColour());
                if (possibleMoves.size() == 0) {
                    board.setWinner(player1.getColour());
                    break;
                }
                this.move(player2, possibleMoves);
            }

            counter++;
        }
    }

    public void move(Player player, List<Move> possibleMoves) {
        if (player instanceof HumanPlayer) {
            canMove = true;
            mouseCount = 0;
            while (mouseCount < 2 || input == null || !possibleMoves.contains(input)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            board.move(input);
        } else {
            player.makeMove(board, possibleMoves);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (canMove && mouseCount == 0) {
            int mousex = MouseInfo.getPointerInfo().getLocation().x;
            int mousey = MouseInfo.getPointerInfo().getLocation().y;
            int x = 340 + 5 + ((((mousex - 340) / 60) % 10) * 60);
            int y = 60 + 5 + ((((mousey - 60) / 60)) * 60);
            oldPosition = new Position(x, y);
            System.out.println(oldPosition);

        } else if (mouseCount == 1) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void displayWinner() {
        System.out.println(board.getWinner());
    }

    public void temporaryTUI(Player player) {
        Map<Position, Piece> grid = board.getGrid();
        System.out.println("For player " + player.getName());
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
            System.out.println("----------------------------------------------------");
        }
        System.out.println("    a  | b  | c  | d  | e  | f  | g  | h  | i  | j");

    }

    public Map<Position, Piece> getGrid(){
        return this.board.getGrid();
    }

    public boolean isValid(List<Move> possibilities, Move move){
        boolean valid = false;

        for (Move m : possibilities){
            if ((m.getOldPos().getX() == move.getOldPos().getX())
                    && (m.getOldPos().getY() == move.getOldPos().getY())
                    && (m.getNewPos().getX() == move.getNewPos().getX())
                    && (m.getNewPos().getY() == move.getNewPos().getY())){
                valid = true;
                break;
            }
        }

        return valid;
    }

    public List<Move> getPossibilities(Colour col){
        return this.board.generatePossibleMoves(col);
    }

    public Board getBoard(){
        return this.board;
    }

}
