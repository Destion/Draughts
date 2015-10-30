import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import controllers.CommunicationController;
import model.Board;
import model.BoardToByte;

import java.util.ArrayList;

/**
 * Created by destion on 29-10-15.
 */
public class Testding   {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        CommunicationController cc = new CommunicationController();
        cc.sendBytes(BoardToByte.convertToBytes(board.getGrid()));
    }
}
