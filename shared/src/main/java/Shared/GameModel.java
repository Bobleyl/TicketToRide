package Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameModel {

    String playerTurn;
    String gameID;
    int gameSize;

    private List<Player> playersList = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private DestinationCardDeck destinationCardDeck;
    private TrainCardDeck trainCardDeck;
    List<Route> availableRoutes = new ArrayList<>(Arrays.asList(Route.values()));

    public GameModel(){ }

    public String getGameID() {
        return gameID;
    }

    public void setPlayerTurn(String playerTurn_) {this.playerTurn = playerTurn_;}

    public String getPlayerTurn() {return this.playerTurn;}

    public void setMessages(List<Message> messages) { this.messages = messages; }

    public List<Message> getMessages() { return this.messages; }

    public void setDestinationCardDeck(DestinationCardDeck destinationCardDeck) { this.destinationCardDeck = destinationCardDeck; }

    public DestinationCardDeck getDestinationCardDeck() { return this.destinationCardDeck; }

    public void setPlayerList(List<Player> players){ this.playersList = players; }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public int getGameSize() {
        return gameSize;
    }

    public void setAvailableRoutes(List<Route> routes) {
        this.availableRoutes = routes;
    }

    public List<Route> getAvailableRoutes() {
        return availableRoutes;
    }

    public void setTrainCardDeck(TrainCardDeck trainCardDeck) { this.trainCardDeck = trainCardDeck;}

    public TrainCardDeck getTrainCardDeck() { return trainCardDeck;}

    public List<Player> findLongestRoute() {

        List<Player> longestRoutePlayers = new ArrayList<>();
        int longest = 0;

        for (Player player : playersList) {

            RouteGraph rg = new RouteGraph();

            for (Route route : player.getClaimedRoutes()) {

                rg.addRoute(route);
            }

            int temp = rg.findLongestRoute();

            if (temp > longest) {

                longest = temp;
                longestRoutePlayers = new ArrayList<>();
                longestRoutePlayers.add(player);
            } else if (temp == longest) {

                longestRoutePlayers.add(player);
            }
        }

        for (Player player: longestRoutePlayers) {

            player.setScore(player.getScore() + 10);
        }

        return longestRoutePlayers;
    }
}