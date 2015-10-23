package controllers;

public class CommunicationsController {

    private CommunicationsController instance;

    public CommunicationsController getInstance(){
        if (instance == null){
            return this.init();
        } else {
            return this.instance;
        }
    }

    private CommunicationsController init(){
        return null;
    }

}
