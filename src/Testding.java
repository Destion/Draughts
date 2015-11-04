import controllers.CommunicationController;
import model.Board;
import model.BoardToByte;

import java.math.BigInteger;

public class Testding {
    public static void main(String[] args) {
//        Board board = new Board();
//        board.initializeBoard();
//        board.printBoard();
//        CommunicationController cc = new CommunicationController();
//        cc.sendBytes(BoardToByte.convertToInteger(board.getGrid()));
//        board.printBoard();


        String s = "11001111000011110000111100001111";
        int i = new BigInteger(s, 2).intValue();
        System.out.println(i);

    }
}
