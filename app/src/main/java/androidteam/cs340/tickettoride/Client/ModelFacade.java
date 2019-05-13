package androidteam.cs340.tickettoride.Client;

import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class ModelFacade {
    private ModelFacade() {}

    public static ModelFacade SINGLETON = new ModelFacade();

    public Result login(User toLogin) {
        return ServerProxy.SINGLETON.login(toLogin);
    }

}
