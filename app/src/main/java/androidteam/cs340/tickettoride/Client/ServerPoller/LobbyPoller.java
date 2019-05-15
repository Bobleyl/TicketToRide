package androidteam.cs340.tickettoride.Client.ServerPoller;

import androidteam.cs340.tickettoride.Client.ClientCommunicator;
import androidteam.cs340.tickettoride.Shared.Result;

public class LobbyPoller extends ServerPoller {

    public LobbyPoller(IPollerCommand command, int frequency) {
        super(command, frequency);
    }

    @Override
    Result getServerData() {

        return ClientCommunicator.SINGLETON.send("lobby");
    }


}
