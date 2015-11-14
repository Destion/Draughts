package model;

import java.util.List;

/**
 * Created by Rogier on 09-11-15
 */
public class Negamax {


    public Negamax() {
    }

    public int negamax(int depth, int colourVal, Colour playerColour, Board board, int alpha, int beta, List<Move> possibleMoves) {
        int bestValue = Integer.MIN_VALUE;
        if (depth == 0 || board.gameOver()) {
            bestValue = colourVal * this.analyse(board, playerColour);
        } else {
            for (Move move : possibleMoves) {
                Board newBoard = new Board();
                newBoard.setGrid(board.deepCopy());
                newBoard.setNumberBlack(board.getNumberBlack());
                newBoard.setNumberWhite(board.getNumberWhite());
                newBoard.move(move);
                List<Move> newMoves = newBoard.generatePossibleMoves(playerColour.other());
                int value = -this.negamax(depth - 1, -1 * colourVal, playerColour.other(), newBoard, -beta, -alpha, newMoves);
                bestValue = Math.max(bestValue, value);

                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
        }
        return bestValue;
    }

    public int analyse(Board board, Colour playerColour) {
        int score = 0;
        score = score + (board.getNumberBlack() * ((playerColour == Colour.BLACK) ? 1 : -1));
        score = score + (board.getNumberWhite() * ((playerColour == Colour.WHITE) ? 1 : -1));
        if (board.winner() == playerColour) {
            score = score + 1000;
        }
        for (Position position : board.getGrid().keySet()) {
            if (board.getGrid().get(position).getColour() == playerColour && board.getGrid().get(position) instanceof King) {
                score = score + 100;
            }
//            if ((board.getGrid().get(position).getColour() == playerColour && position.getX() == 1)
//                    || (board.getGrid().get(position).getColour() == playerColour && position.getX() == 10)){
//                score = score + 1;
//            }
        }

        return score;
    }

}
