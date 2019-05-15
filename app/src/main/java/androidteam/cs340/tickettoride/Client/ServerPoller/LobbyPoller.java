package androidteam.cs340.tickettoride.Client.ServerPoller;

import androidteam.cs340.tickettoride.Client.ClientCommunicator;
import androidteam.cs340.tickettoride.Client.ServerProxy;
import androidteam.cs340.tickettoride.Shared.Result;

public class LobbyPoller extends ServerPoller {

    public LobbyPoller(IPollerCommand command, int frequency) {
        super(command, frequency);
    }

    @Override
    Result getServerData() {

        //This should create the json to send or have a method in the ServerProxy
        return ServerProxy.SINGLETON.lobby();
    }


}
