package model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Rogier on 09-11-15
 */
public class Negamax {
    private Colour colour;
    private boolean analyseBetter;

    public Negamax(Colour colour, boolean analyseBetter) {
        this.colour = colour;
        this.analyseBetter = analyseBetter;
    }

    public Negamax() {
    }

    public int negaDeep(int depth, Colour playerColour, Board board, int alpha, int beta, List<Move> possibleMoves) {
        int bestValue = Integer.MIN_VALUE;
        if (depth == 0 || board.gameOver()) {
            if (analyseBetter){
                bestValue = this.analyse2(board,playerColour);
            } else {
                bestValue = this.analyse(board, playerColour);
            }
        } else {
            for (Move move : possibleMoves) {
                Board newBoard = new Board();
                newBoard.setGrid(board.deepCopy());
                newBoard.setNumberBlack(board.getNumberBlack());
                newBoard.setNumberWhite(board.getNumberWhite());
                newBoard.move(move);
                List<Move> newMoves = newBoard.generatePossibleMoves(playerColour.other());
                int value = -this.negaDeep(depth - 1, playerColour.other(), newBoard, -beta, -alpha, newMoves);
                bestValue = Math.max(bestValue, value);

                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        return bestValue;
    }

    public Move negaShallow(int depth, Colour playerColour, Board board, int alpha, int beta, List<Move> possibleMoves) {
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = null;
        for (Move move : possibleMoves) {
            Board newBoard = new Board();
            newBoard.setGrid(board.deepCopy());
            newBoard.setNumberBlack(board.getNumberBlack());
            newBoard.setNumberWhite(board.getNumberWhite());
            newBoard.move(move);
            List<Move> newMoves = newBoard.generatePossibleMoves(playerColour.other());
            int value = -this.negaDeep(depth - 1, playerColour.other(), newBoard, -beta, -alpha, newMoves);
//            System.out.println(move.toString() + " has a value of " + value);

            if (value > bestValue){
                bestMove = move;
                bestValue = value;
            }

            alpha = Math.max(alpha, value);
            if (alpha >= beta) {
                break;
            }
        }
//        System.out.println(bestMove.toString());
        return bestMove;
    }

    public int analyse(Board board, Colour playerColour) {
        int score = 0;
        score = score + (board.getNumberBlack() * ((playerColour == Colour.BLACK) ? 1 : -1));
        score = score + (board.getNumberWhite() * ((playerColour == Colour.WHITE) ? 1 : -1));
        if (board.gameOver()) {
            if (board.winner() == playerColour) {
                score = score + 1000;
            } else if (board.winner() == playerColour.other()) {
                score = score - 1000;
            } else if (board.draw()) {
                score = score - 100;
            }
        }

        for (Position position : board.getGrid().keySet()) {
            Piece piece = board.getGrid().get(position);
            if (piece.getColour() == playerColour && piece instanceof King) {
                score = score + 100;
            }
            if(piece.getColour() == playerColour && piece instanceof Man && (position.getY() == 1 || position.getY() == 10)){
                score = score + 5;
            }
        }

        return score;
    }


    public int analyse2(Board board, Colour playerColour){
        int score = 0;
        int pVar = (this.colour == playerColour ? 1 : -1);
        List<Move> posssibleMoves = board.generatePossibleMoves(playerColour);
        Set<Position> moveableMan = new HashSet<>();
        Set<Position> moveableKing = new HashSet<>();
        for (Move move: posssibleMoves){
            if (board.getGrid().get(move.getOldPos()) instanceof Man){
                moveableMan.add(move.getOldPos());
            } else if (board.getGrid().get(move.getOldPos()) instanceof King){
                moveableKing.add(move.getOldPos());
            }
        }
        score = score + 74 * moveableMan.size() + 53 * moveableKing.size();
        for (Position position : board.getGrid().keySet()){
            Piece piece = board.getGrid().get(position);
            if (piece instanceof Man){
                score = score + 989;
            } else if (piece instanceof King){
                score = score + 1590;
            }


        }


        return score * pVar;
    }
}
