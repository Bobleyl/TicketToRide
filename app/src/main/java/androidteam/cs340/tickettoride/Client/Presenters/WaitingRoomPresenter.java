package androidteam.cs340.tickettoride.Client.Presenters;

import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.WaitingRoomActivity;

public class WaitingRoomPresenter implements IPresenter {
    String ID;
    WaitingRoomActivity activity;

    public WaitingRoomPresenter(WaitingRoomActivity activity){
        ID = UUID.randomUUID().toString();
        this.activity = activity;
    }

    @Override
    public void Update() {
        activity.updateUI();
    }

    @Override
    public String getID(){
        return ID;
    }
}
