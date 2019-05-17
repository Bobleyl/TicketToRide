package androidteam.cs340.tickettoride.Client;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.ServerPoller.LobbyPollerCommand;
import androidteam.cs340.tickettoride.Client.ServerPoller.ParseResults;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;
import androidteam.cs340.tickettoride.Client.ServerPoller.LobbyPoller;

public class ModelFacade {
    private Lobby currentLobby;
    private User user;
    private Player currentPlayer;
    private List<IPresenter> presenters;
    private Game currentGame;
    private String gameID;
    private LobbyPollerCommand command = new LobbyPollerCommand();
    private LobbyPoller lobbyPoller = new LobbyPoller(command, 1000);

    private ModelFacade() {
        currentLobby = new Lobby();
        presenters = new ArrayList<>();
    }

    public void startPoller(){
        lobbyPoller.start();
    }

    public static ModelFacade SINGLETON = new ModelFacade();

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public void updatePresenter() {
        for(IPresenter presenter : presenters) {
            presenter.Update();
        }
    }

    public void removePresenter(IPresenter toDelete){
        for(int i = 0; i < presenters.size(); i++){
            if (presenters.get(i).getID().equals(toDelete.getID())){
                presenters.remove(i);
                break;
            }
        }
    }

    public Result login(User user) {
        return ServerProxy.SINGLETON.login(user);
    }

    public Result register(User user) {
        return ServerProxy.SINGLETON.register(user);
    }

    public String getGameID() { return currentGame.getUID(); }

    public void setGameID(String gameID_) { gameID = gameID_; }

    public Game getGame() { return currentGame; }

    public void setGame(Game game){
        currentGame = game;
    }

    public List<Game> getLobbyGames(){
        return currentLobby.getGames();
    }

    public void addLobby(Lobby lobby) { currentLobby = lobby; }

    public Player getPlayer() { return currentPlayer; }

    public void addPlayer(Player player) { currentPlayer = player; }

    public void updateCurrentGames(Result result) {
        // call on ParseResults and get list of games
        List<Game> gamesList = new ArrayList<Game>();
        gamesList = ParseResults.SINGLETON.parseLobbyResult(result);
        currentLobby.updateCurrentGames(gamesList);

        // Update currentGame if it exists
        if(currentGame != null){
            for(Game game : gamesList){
                if(game.getUID().equals(currentGame.getUID())){
                    currentGame = game;
                }
            }
        }

        updatePresenter();
    }

    public Result createGame(String playerID, int size){
        Result createGameResult = ServerProxy.SINGLETON.createGame(playerID, size);
        if(createGameResult.getStatusCode() == HttpURLConnection.HTTP_OK) {
            currentGame = new Game(size);
            currentGame.addPlayer(this.currentPlayer);
            currentGame.setUID(createGameResult.getData());
        }
        return createGameResult;
    }

    public Result joinGame(){
        String playerID = currentPlayer.getUID();
        return ServerProxy.SINGLETON.joinGame(playerID, gameID);
    }

}
