package androidteam.cs340.tickettoride.Client;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import java.util.List;
import java.util.ArrayList;

public class ModelFacade {
    private Lobby currentLobby;
    private User user;
    private Player currentPlayer;
    private List<IPresenter> presenters;
    private Game currentGame;

    private ModelFacade() {
        currentLobby = new Lobby();
        presenters = new ArrayList<>();
    }

    public static ModelFacade SINGLETON = new ModelFacade();

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public Result login(User user) {
        return ServerProxy.SINGLETON.login(user);
    }

    public Result register(User user) {
        return ServerProxy.SINGLETON.register(user);
    }

    public Game getGame() { return currentGame; }

    public void addGame(Game game) { currentGame = game; }

    public List<Game> getLobbyGames(){
        return currentLobby.getGames();
    }

    public void addLobby(Lobby lobby) { currentLobby = lobby; }

    public Player getPlayer() { return currentPlayer; }

    public void addPlayer(Player player) { currentPlayer = player; }

    public void updateCurrentGames(List<Game> gamesList) {
        currentLobby.updateCurrentGames(gamesList);
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
