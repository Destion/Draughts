package controllers;


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
