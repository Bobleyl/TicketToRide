package androidteam.cs340.tickettoride.Client.Presenters;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class LoginPresenter implements IPresenter {
    public Result login(User toLogin) {
        ModelFacade.SINGLETON.login(toLogin);
        return null;
    }

}
