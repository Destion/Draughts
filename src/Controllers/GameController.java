package Controllers;

import java.util.HashMap;


public class GameController {

    private GameController instance;

    public GameController getInstance(){
        if (instance == null){
            return this.init();
        } else {
            return instance;
        }
    }

    private GameController init(){
        return null;
    }

}
