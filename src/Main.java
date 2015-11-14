import controllers.GameController;
import model.*;

public class Main {

    public static void main(String[] args) {
        if ((args.length > 0) && (args[0].equals("-log"))) {
            Player player1 = new HumanPlayer("Rogier", Colour.WHITE);
            Player player2 = new NegaPlayer("AI", Colour.BLACK, 2);
            GameController game = new GameController(player1, player2, args[0]);
            game.run();
        } else {
            Player player1 = new ComputerPlayer("Rogier", Colour.WHITE);
            Player player2 = new NegaPlayer("AI", Colour.BLACK, 7);
            GameController game = new GameController(player1, player2);
            game.run();
        }
    }
}
