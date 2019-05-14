package androidteam.cs340.tickettoride.Client.Presenters;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class LoginRegisterPresenter implements IPresenter {
    public Result login(User user) {
        Result result = ModelFacade.SINGLETON.login(user);
        return result;
    }

    public Result register(User user) {
        Result result = ModelFacade.SINGLETON.register(user);
        return result;
    }
}
