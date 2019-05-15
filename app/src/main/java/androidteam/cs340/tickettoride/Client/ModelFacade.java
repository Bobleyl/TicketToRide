package androidteam.cs340.tickettoride.Client;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.Poller;
import androidteam.cs340.tickettoride.Client.ServerPoller.ParseResults;
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
    private Poller poller = null;

    private ModelFacade() {
        currentLobby = new Lobby();
        presenters = new ArrayList<>();
    }


    public static ModelFacade SINGLETON = new ModelFacade();

    //turn on poller and start updating the lobby
    public void startPoller(){
        if(poller == null){
            poller = new Poller();
            poller.startPolling();
        }
    }

    //turn off poller entirely
    public void stopPoller(){
        poller.stopPolling();
        poller = null;
    }

    //tells the poller to switch over to updating commands for the game instead of lobby
    public void startPollingCommands(){
        poller.setPollerSwitch(currentGame.getUID());
    }

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public void updatePresenter() {
        for(IPresenter presenter : presenters) {
            presenter.Update();
        }
    }

    public Result login(User user) {
        return ServerProxy.SINGLETON.login(user);
    }

    public Result register(User user) {
        return ServerProxy.SINGLETON.register(user);
    }

    public String getGameID() { return currentGame.getUID(); }

    public Game getGame() { return currentGame; }

    public void setGame(Game game) { currentGame = game; }

    public List<Game> getLobbyGames(){
        return currentLobby.getGames();
    }

    public void addLobby(Lobby lobby) { currentLobby = lobby; }

    public Player getPlayer() { return currentPlayer; }

    public void addPlayer(Player player) { currentPlayer = player; }

    public void updateCurrentGames(Result result) {
        List<Game> gamesList = new ArrayList<Game>();
        gamesList = ParseResults.SINGLETON.parseLobbyResult(result);
        // call on ParseResults and get list of games
        currentLobby.updateCurrentGames(gamesList);
    }

    public Result createGame(String playerID, int size){
        Result createGameResult = ServerProxy.SINGLETON.createGame(playerID, size);
            if(createGameResult.getStatusCode() == HttpURLConnection.HTTP_OK){
            currentGame = new Game(size);
            currentGame.addPlayer(this.currentPlayer);
            //get game id from response
        }
        //Result joinGameResult = joinGame(playerID, 1);
        return createGameResult;
    }

    //public Result joinGame(String playerID, String gameID){
      //  return ServerProxy.SINGLETON.joinGame(playerID, gameID);
    //}

}
