import controllers.CommunicationController;
import controllers.GameController;
import model.Colour;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;

public class Main {

    public static void main(String[] args) {
        CommunicationController communicationController = new CommunicationController();
        if ((args.length > 0) && (args[0].equals("-log"))) {
            Player player1 = new HumanPlayer("Rogier", Colour.WHITE);
            Player player2 = new ComputerPlayer("Barry", Colour.BLACK, communicationController);
            GameController game = new GameController(player1, player2, args[0]);
            game.run();
        } else {
            Player player1 = new HumanPlayer("Rogier", Colour.WHITE);
            Player player2 = new ComputerPlayer("Barry", Colour.BLACK, communicationController);
            GameController game = new GameController(player1, player2);
            game.run();
        }
    }
}
