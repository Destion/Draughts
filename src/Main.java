import controllers.GameController;
import model.Colour;
import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;

public class Main {

    public static void main(String[] args) {
        Player player1 = new ComputerPlayer("Rogier", Colour.WHITE);
        Player player2 = new ComputerPlayer("Barry", Colour.BLACK);
        GameController game = new GameController(player1, player2);
        game.run();
    }


    public static void test() {

    }
}
