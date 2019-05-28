package androidteam.cs340.tickettoride.Client.Poller.Game;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Poller.IPollerCommand;
import androidteam.cs340.tickettoride.Shared.Result;

public class GamePollerCommand implements IPollerCommand {
    public void execute(Result result) {
        ModelFacade.SINGLETON.updateCurrentGame(result);
    }
}
