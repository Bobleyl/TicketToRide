package server;

import java.util.ArrayList;
import java.util.List;

public class GameList {

    private List<server.GameModel> games = new ArrayList<>();

    public static GameList SINGLETON = new GameList();

    private GameList() {}

    public void addGame(server.GameModel game) {games.add(game);}

    public List<GameModel> getGames() { return games; }

}
