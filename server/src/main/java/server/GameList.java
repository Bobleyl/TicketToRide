package server;

import Shared.GameModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameList {

    Map<String, List<String>> deltas = new HashMap<>();

    private Integer deltaCount;

    private List<GameModel> games = new ArrayList<>();

    public static GameList SINGLETON = new GameList();

    private GameList() {}

    public void addGame(GameModel game) {games.add(game);}

    public List<GameModel> getGames() { return games; }

    public void setDeltaCount(Integer count) { deltaCount = count; }

    public Integer getDeltaCount() { return deltaCount; }

}
