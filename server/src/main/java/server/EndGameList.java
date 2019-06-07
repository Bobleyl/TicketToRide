package server;

import Shared.*;

import java.util.ArrayList;
import java.util.List;

public class EndGameList {

    private List<EndGame> games = new ArrayList<>();

    public static EndGameList SINGLETON = new EndGameList();

    private EndGameList() {}

    public void addGame(EndGame game) {games.add(game);}

    public List<EndGame> getGames() { return games; }

}