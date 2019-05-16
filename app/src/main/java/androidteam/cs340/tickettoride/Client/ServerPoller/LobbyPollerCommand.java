package androidteam.cs340.tickettoride.Client.ServerPoller;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Shared.Result;

public class LobbyPollerCommand implements IPollerCommand{
    public void execute(Result result) {
        ModelFacade.SINGLETON.updateCurrentGames(result);
    }
}
