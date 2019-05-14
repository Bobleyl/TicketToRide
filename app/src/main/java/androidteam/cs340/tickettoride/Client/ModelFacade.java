package androidteam.cs340.tickettoride.Client;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class ModelFacade {
    private Lobby lobby;
    private User user;
    private Player player;
    private List<IPresenter> presenters;
    private Game game;

    private ModelFacade() {
        lobby = new Lobby();
        presenters = new ArrayList<>();
    }

    public static ModelFacade SINGLETON = new ModelFacade();

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public Result login(User toLogin) {
        Result result = ServerProxy.SINGLETON.login(toLogin);
        if(result.getSuccess()){
            user = new User(toLogin.getUsername(), toLogin.getPassword());
        }
        return ServerProxy.SINGLETON.login(toLogin);
    }

    public Result register(User toRegister) {
        Result result = ServerProxy.SINGLETON.login(toRegister);
        if(result.getSuccess()){
            user = new User(toRegister.getUsername(), toRegister.getPassword());
        }
        return ServerProxy.SINGLETON.register(toRegister);
    }

//    public Result createGame(Game toCreate){
//        Result createGameResult = ServerProxy.SINGLETON.createGame(toCreate);
//        if(createGameResult.getSuccess()){
//            game = new Game(toCreate.getGameSize());
//            game.addPlayer(this.player);
//        }
//        Result joinGameResult = joinGame(game);
//        return createGameResult;
//    }
//
//    public Result joinGame(Game toJoin){
//        Result result = ServerProxy.SINGLETON.joinGame(toJoin);
//        if(result.getSuccess()){
//            game = new Game(toJoin.getGameSize());
//            game.addPlayer(this.player);
//        }
//    }
}
