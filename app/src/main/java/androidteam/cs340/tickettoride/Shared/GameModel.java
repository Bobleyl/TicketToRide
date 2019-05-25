package androidteam.cs340.tickettoride.Shared;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    String playerTurn;
    String gameID;
    int gameSize;

    private List<Player> playersList = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private List<TrainCard> trainCardDeckDown = new ArrayList<>();
    private TrainCard[] trainCardDeckUp = new TrainCard[5];
    private List<DestinationCard> destinationCards = new ArrayList<>();

    public GameModel(){ }

    public String getGameID() {
        return gameID;
    }

    public void setPlayerTurn(String playerTurn_) {this.playerTurn = playerTurn_;}

    public String getPlayerTurn() {return this.playerTurn;}

    public void setMessages(List<Message> messages) { this.messages = messages; }

    public List<Message> getMessages() { return this.messages; }

    public void setDestinationCardDeck(List<DestinationCard> destinationCardDeck) { this.destinationCards = destinationCardDeck; }

    public List<DestinationCard> getDestinationCardDeck() { return this.destinationCards; }

    public void setTrainCardDeckDown(List<TrainCard> trainCardDeckDown) { this.trainCardDeckDown = trainCardDeckDown; }

    public List<TrainCard> getTrainCardDeckDown() { return this.trainCardDeckDown; }

    public void setTrainCardDeckUp(TrainCard[] trainCardDeckUp) { this.trainCardDeckUp = trainCardDeckUp; }

    public TrainCard[] getTrainCardDeckUp() { return this.trainCardDeckUp; }

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
