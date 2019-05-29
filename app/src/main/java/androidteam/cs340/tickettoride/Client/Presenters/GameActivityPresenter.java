package androidteam.cs340.tickettoride.Client.Presenters;

import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.GameActivity;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Shared.Player;

public class GameActivityPresenter implements IPresenter {
    private String ID;
    private GameActivity currentActivity;

    @Override
    public void Update() {
        List<Player> currentPlayers = Phase2Facade.SINGLETON.getCurrentGame().getPlayersList();
        currentActivity.upDatePlayerTextViews(currentPlayers);
    }

    @Override
    public String getID() {
        return ID;
    }

    public GameActivityPresenter(GameActivity currentActivity) {
        ID = UUID.randomUUID().toString();
        Phase2Facade.SINGLETON.addPresenter(this);
        this.currentActivity = currentActivity;
    }
}
