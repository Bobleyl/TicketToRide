package server.shared;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    String playerTurn;
    String gameID;
    int gameSize;

    private List<GameModel> games = new ArrayList<>();
    private List<Player> playersList = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private List<TrainCard> trainCardDeck = new ArrayList<>();
    private List<DestinationCard> destinationCards = new ArrayList<>();

    GameModel game = null;

    public GameModel(){ }

    public List<GameModel> getGames() { return games; }

    public void addGame(GameModel game) { games.add(game);}

    public String getGameID() {
        return gameID;
    }

    public void setGame(GameModel game_) {game = game_;}

    public GameModel getGame() { return game;}

    public void setPlayerTurn(String playerTurn_) {this.playerTurn = playerTurn_;}

    public String getPlayerTurn() {return this.playerTurn;}

    public void setMessages(List<Message> messages) { this.messages = messages; }

    public List<Message> getMessages() { return this.messages; }

    public void setDestinationCardDeck(List<DestinationCard> destinationCardDeck) { this.destinationCards = destinationCardDeck; }

    public List<DestinationCard> getDestinationCardDeck() { return this.destinationCards; }

    public void setTrainCardDeck(List<TrainCard> trainCardDeck) { this.trainCardDeck = trainCardDeck; }

    public List<TrainCard> getTrainCardDeck() { return this.trainCardDeck; }

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

}