package androidteam.cs340.tickettoride.Client.Presenters;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class RegisterPresenter implements IPresenter {
    public Result register(User toRegister) {
        Result result = ModelFacade.SINGLETON.register(toRegister);
        return result;
    }

}
