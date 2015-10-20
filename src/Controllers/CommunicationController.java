package Controllers;

public class CommunicationController {

    private CommunicationController instance;

    public CommunicationController getInstance(){
        if (instance == null){
            return this.init();
        } else {
            return this.instance;
        }
    }

    private CommunicationController init(){
        return null;
    }

}
