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
import java.util.concurrent.Semaphore;

public class GameController implements ActionListener, MouseListener {
    private Board board;
    private Player player1;
    private Player player2;
    private int counter;
    public static final int PLAYERCOUNT = 2;
    private BoardView view;
    private int mouseCount;
    private Move input;
    private List<Move> possibleMoves;
    private Semaphore needsInput = new Semaphore(0);
    private Semaphore play = new Semaphore(0);

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = new Board();


        view = new BoardView();
        board.addObserver(view);
        view.addMouseListener(this);
        view.addButtonListener(this);
    }

    public void run() {
        while (1 < 2) {
            this.setup();
            view.setButtonText("Play");
            view.setButtonActive();
            try {
                play.acquire();
                view.setButtonText("Playing...");
                view.setButtonInactive();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.play();
            this.displayWinner();
        }
    }

    private void setup() {
        board.initializeBoard();
        counter = 0;
    }

    private void play() {
        while (!board.gameOver()) {

            if (counter % PLAYERCOUNT == 0) {
                possibleMoves = board.generatePossibleMoves(player1.getColour());
                if (possibleMoves.size() == 0) {
                    board.setWinner(player2.getColour());
                    break;
                }
                this.move(player1);
            } else {
                //this.temporaryTUI(player2);
                possibleMoves = board.generatePossibleMoves(player2.getColour());
                if (possibleMoves.size() == 0) {
                    board.setWinner(player1.getColour());
                    break;
                }
                this.move(player2);
            }

            counter++;
        }
    }

    private void move(Player player) {
        if (player instanceof HumanPlayer) {
            mouseCount = 0;
//            ((HumanPlayer) player).temporaryTUI(possibleMoves);
            try {
                needsInput.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            board.move(input);
        } else {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.makeMove(board, possibleMoves);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) {
            play.release();
            view.displayMessage("Play by clicking a piece");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (mouseCount == 0 && possibleMoves != null) {
            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
            int mouseY = MouseInfo.getPointerInfo().getLocation().y;
            int x = 1 + ((mouseX - 340) / 60);
            int y = 10 - ((mouseY - 85) / 60);
            Position oldPosition = new Position(x, y);
            view.displayMessage("Old Position: " + oldPosition);
            boolean wrongMove = true;
            for (Move move : possibleMoves) {
                if (move.getOldPos().equals(oldPosition)) {
                    input = move;
                    mouseCount++;
                    wrongMove = false;
                    break;
                }
            }
            if (wrongMove) {
                view.displayMessage("Wrong move " + oldPosition);
            }
//
        } else if (mouseCount == 1 && possibleMoves != null) {
            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
            int mouseY = MouseInfo.getPointerInfo().getLocation().y;
            int x = 1 + ((mouseX - 340) / 60);
            int y = 10 - ((mouseY - 85) / 60);
            Position newPosition = new Position(x, y);
            view.displayMessage("New Position: " + newPosition);
            boolean wrongMove = true;
            for (Move move : possibleMoves) {
                if (move.getNewPos().equals(newPosition) && (move.equals(input) || move.getOldPos().equals(input.getOldPos()))) {
                    mouseCount++;
                    input = move;
                    wrongMove = false;
                    break;
                }
            }
            if (wrongMove) {
                mouseCount--;
//                display message
                view.displayMessage("Wrong move " + newPosition + ", try again!");
            } else {
                needsInput.release();
                view.displayMessage("Succes " + input.getOldPos() + " - " + input.getNewPos() + "!");
            }

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


    private void displayWinner() {
        Colour winner = board.getWinner();
        if (winner == player1.getColour()) {
            view.displayWinner(player1);
        } else if (winner == player2.getColour()) {
            view.displayWinner(player2);
        } else {
            view.draw();
        }
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

    public Board getBoard(){
        return this.board;
    }

}
