package androidteam.cs340.tickettoride.Client.Presenters;

import java.util.UUID;

public class WaitingRoomPresenter implements IPresenter {
    String ID;

    public WaitingRoomPresenter(){
        ID = UUID.randomUUID().toString();
    }

    @Override
    public void Update() {

    }

    @Override
    public String getID(){
        return ID;
    }
}
