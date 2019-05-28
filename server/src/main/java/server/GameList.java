package server;

import androidteam.cs340.tickettoride.Shared.GameModel;

import java.util.ArrayList;
import java.util.List;

public class GameList {

    private List<GameModel> games = new ArrayList<>();

    public static GameList SINGLETON = new GameList();

    private GameList() {}

    public void addGame(GameModel game) {games.add(game);}

    public List<GameModel> getGames() { return games; }

}
