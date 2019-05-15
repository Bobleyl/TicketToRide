package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Client.ClientCommunicator;
import androidteam.cs340.tickettoride.Shared.Result;

public class LobbyPoller extends Poller {

    public LobbyPoller(IPollerCommand command, int frequency) {
        super(command, frequency);
    }

    @Override
    Result getServerData() {

         return ClientCommunicator.SINGLETON.send("lobby");
    }


}
