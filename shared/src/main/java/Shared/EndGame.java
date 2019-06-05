package Shared;

import java.util.List;

public class EndGame {

    private String firstPlace;
    private List<Player> players;
    private List<String> longestRoadPlayers;

    public static final EndGame SINGLETON = new EndGame();

    private EndGame() {}

    public void setFirstPlace(String firstPlace_) { firstPlace = firstPlace_; }

    public String getFirstPlace() { return firstPlace; }

    public void setPlayers(List<Player> players_) { players = players_; }

    public List<Player> getPlayers() { return players; }

    public void setLongestRoadPlayers(List<String> longestRoadPlayers_) { longestRoadPlayers = longestRoadPlayers_; }

    public List<String> getLongestRoadPlayers() { return longestRoadPlayers; }

}
