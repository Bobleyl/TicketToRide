package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Shared.Result;

public interface IPollerCommand {

    public void execute(Result result);
}
