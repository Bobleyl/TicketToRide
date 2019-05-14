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
    private User currentUser;
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

    public Result login(User toLogin) {
        Result result = ServerProxy.SINGLETON.login(toLogin);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK){
            currentUser = new User(toLogin.getUsername(), toLogin.getPassword());
        }
        return ServerProxy.SINGLETON.login(toLogin);
    }

    public Result register(User toRegister) {
        Result result = ServerProxy.SINGLETON.login(toRegister);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK){
            currentUser = new User(toRegister.getUsername(), toRegister.getPassword());
        }
        return ServerProxy.SINGLETON.register(toRegister);
    }

    public Game getGame() { return currentGame; }

    public void setGame(Game game) { currentGame = game; }

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

    public void updatePlayersInGame(List<Player> playerList){
        currentGame.setPlayersList(playerList);
    }

}
