package androidteam.cs340.tickettoride.Client.Presenters;

import androidteam.cs340.tickettoride.Client.Phase2Facade;

public class GameActivityPresenter implements IPresenter {
    @Override
    public void Update() {

    }

    @Override
    public String getID() {
        return null;
    }

    public GameActivityPresenter() {
        Phase2Facade.SINGLETON.addPresenter(this);
    }
}
