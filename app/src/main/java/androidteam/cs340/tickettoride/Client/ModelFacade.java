package androidteam.cs340.tickettoride.Client;

import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import java.util.List;
import java.util.ArrayList;

public class ModelFacade {
    private ModelFacade() {}

    private Game currentGame;
    private Player currentPlayer;
    private Lobby currentLobby;

    public static ModelFacade SINGLETON = new ModelFacade();

    public Result login(User toLogin) {
        return ServerProxy.SINGLETON.login(toLogin);
    }

    public Result register(User toRegister) {
        return ServerProxy.SINGLETON.register(toRegister);
    }

    public Game getGame() { return currentGame; }

    //public void addGame(Game game) { currentGame = game; }

    public List<Game> getLobbyGames(){
        return currentLobby.getGames();
    }

    //public void addLobby(Lobby lobby) { currentLobby = lobby; }

    public Player getPlayer() { return currentPlayer; }

    //public void addPlayer(Player player) { currentPlayer = player; }

    public void updateCurrentGames(List<Game> gamesList) {
        currentLobby.updateCurrentGames(gamesList);
    }
}
