package server;

import server.shared.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LobbyGameModel {
    private int numPlayersToStart = 0;
    private String gameID = UUID.randomUUID().toString();
    private ArrayList<String> playerIDs = new ArrayList<>();

    public LobbyGameModel(int numPlayersToStart, String playerID) {

        this.numPlayersToStart = numPlayersToStart;
        playerIDs.add(playerID);
    }

    public int getNumPlayersToStart() {
        return numPlayersToStart;
    }

    public String getGameID() {
        return gameID;
    }

    public ArrayList<String> getPlayerIDs() {
        return playerIDs;
    }

    public synchronized boolean addPlayer(String playerID) {

        if (playerIDs.size() == numPlayersToStart) {
            return false;
        }

        playerIDs.add(playerID);

        if (playerIDs.size() == numPlayersToStart) {
            startGame();

            //Remove game from lobby
            LobbyModel.SINGLETON.deleteGame(gameID);
        }

        return true;
    }

    private void startGame() {

        System.out.println("Started game");
        Game game = new Game();

        int i = 0;
        String[] colors = {"red", "blue", "green", "yellow", "black"};
        List<Player> players = new ArrayList<>();

        DestinationCardDeck destinationCardDeck = new DestinationCardDeck();


        for (String player : playerIDs) {
            Player currentPlayer = new Player(player);

            //Set color and name
            currentPlayer.setColor(colors[i]);
            i++;
            currentPlayer.setName("Player" + i);

            //Add initial 3 cards to player's destination list
            int j = 0;
            List<DestinationCard> destinationCards = new ArrayList<>();
            while (j < 3) {
                destinationCards.add(destinationCardDeck.getCard());
                j++;
            }
            currentPlayer.setDestinationHand(destinationCards);

            //Add object to array list
            players.add(currentPlayer);
        }

        game.setGameID(gameID);
        game.setPlayerTurn(playerIDs.get(0));
        game.setPlayerList(players);
        game.setGameSize(numPlayersToStart);
        System.out.println(gameID);
        GameList.SINGLETON.addGame(game);

    }
}
