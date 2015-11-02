import controllers.GameController;
import model.Colour;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import view.Log;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        if ((args.length > 0) && (args[0].equals("-log"))) {
            Player player1 = new ComputerPlayer("Rogier", Colour.WHITE);
            Player player2 = new HumanPlayer("Barry", Colour.BLACK);
            GameController game = new GameController(player1, player2, args[0]);
            game.run();
        } else {
            Player player1 = new ComputerPlayer("Rogier", Colour.WHITE);
            Player player2 = new HumanPlayer("Barry", Colour.BLACK);
            GameController game = new GameController(player1, player2);
            game.run();
        }
    }
}
