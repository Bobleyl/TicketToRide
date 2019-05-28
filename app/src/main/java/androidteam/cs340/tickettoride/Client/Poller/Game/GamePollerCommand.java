package androidteam.cs340.tickettoride.Client.Poller.Game;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Poller.IPollerCommand;
import androidteam.cs340.tickettoride.Shared.Result;

public class GamePollerCommand implements IPollerCommand {
    public void execute(Result result) {
        Phase2Facade.SINGLETON.updateCurrentGame(result);
    }
}
