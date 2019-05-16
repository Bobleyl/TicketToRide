package androidteam.cs340.tickettoride.Client.Presenters;

import android.view.Display;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.ServerPoller.ParseResults;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class LoginRegisterPresenter implements IPresenter {
    public Result login(User user) {
        Result result = ModelFacade.SINGLETON.login(user);
        String playerID = ParseResults.SINGLETON.parseSingleString(result);
        Player toAdd = new Player(playerID);
        ModelFacade.SINGLETON.addPlayer(toAdd);
        //ModelFacade.SINGLETON.startPoller();
        return result;
    }

    public Result register(User user) {
        Result result = ModelFacade.SINGLETON.register(user);
        return result;
    }

    @Override
    public void Update() {

    }
}
