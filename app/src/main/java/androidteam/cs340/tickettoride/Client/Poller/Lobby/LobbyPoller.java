package androidteam.cs340.tickettoride.Client.Poller.Lobby;

import androidteam.cs340.tickettoride.Client.Poller.IPollerCommand;
import androidteam.cs340.tickettoride.Client.Poller.ServerPoller;
import androidteam.cs340.tickettoride.Client.ServerProxy;
import androidteam.cs340.tickettoride.Shared.Result;

public class LobbyPoller extends ServerPoller {

    public LobbyPoller(IPollerCommand command, int frequency) {
        super(command, frequency);
    }

    @Override
    public Result getServerData() {

        //This should create the json to send or have a method in the ServerProxy
        return ServerProxy.SINGLETON.lobby();
    }


}
