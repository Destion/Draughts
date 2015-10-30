import controllers.CommunicationController;

import java.util.ArrayList;

/**
 * Created by destion on 29-10-15.
 */
public class Testding   {
    public static void main(String[] args) {
        CommunicationController comcont = new CommunicationController();

        ArrayList<Integer> array = new ArrayList<>();
        array.add(111);
        array.add(110);
        array.add(000);
        array.add(011);
        array.add(101);
        array.add(001);
        array.add(000);
        array.add(100);

        comcont.sendBytes(array);
    }
}
