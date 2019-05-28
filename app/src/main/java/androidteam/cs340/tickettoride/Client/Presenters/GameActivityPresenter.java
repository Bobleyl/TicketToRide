package androidteam.cs340.tickettoride.Client.Presenters;

import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Phase2Facade;

public class GameActivityPresenter implements IPresenter {
    private String ID;

    @Override
    public void Update() {

    }

    @Override
    public String getID() {
        return ID;
    }

    public GameActivityPresenter() {
        //Give the newly generated presenter an ID
        ID = UUID.randomUUID().toString();
        Phase2Facade.SINGLETON.addPresenter(this);
    }
}
