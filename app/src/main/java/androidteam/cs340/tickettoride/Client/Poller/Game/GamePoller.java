package androidteam.cs340.tickettoride.Client.Poller.Game;

import android.util.Log;

import androidteam.cs340.tickettoride.Client.Poller.IPollerCommand;
import androidteam.cs340.tickettoride.Client.Poller.ServerPoller;
import androidteam.cs340.tickettoride.Client.ServerProxy;
import androidteam.cs340.tickettoride.Shared.Result;

public class GamePoller extends ServerPoller {

    private String gameID;

    public GamePoller(IPollerCommand command, int frequency, String gameID) {
        super(command, frequency);
        this.gameID = gameID;
    }

    @Override
    public Result getServerData() {
        //This should create the json to send or have a method in the ServerProxy
        return ServerProxy.SINGLETON.game(gameID);
    }


}
