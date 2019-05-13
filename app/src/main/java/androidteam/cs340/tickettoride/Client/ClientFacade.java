package androidteam.cs340.tickettoride.Client;

public class ClientFacade {

    private static ClientFacade INSTANCE = null;

    private ClientFacade(){}

    public static ClientFacade getInstance(){
        if (INSTANCE == null)
            INSTANCE = new ClientFacade();
        return INSTANCE;
    }

}
