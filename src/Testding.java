import controllers.CommunicationController;
import model.Board;
import model.BoardToByte;

public class Testding {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        board.printBoard();
        CommunicationController cc = new CommunicationController();
        cc.sendBytes(BoardToByte.convertToInteger(board.getGrid()));
        board.printBoard();
    }
}
