package androidteam.cs340.tickettoride.Client.Presenters;

import android.content.Intent;

import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.StartGame;
import androidteam.cs340.tickettoride.Client.Activities.WaitingRoomActivity;
import androidteam.cs340.tickettoride.Client.ModelFacade;

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
