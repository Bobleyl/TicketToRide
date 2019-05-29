package server;

import Shared.*;

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
            //Moved to DeleteGameCommand
            //LobbyModel.SINGLETON.deleteGame(gameID);
        }

        return true;
    }

    private void startGame() {

        System.out.println("Started game");
        GameModel game = new GameModel();

        int i = 0;
        String[] colors = {"red", "blue", "green", "yellow", "black"};
        List<Player> players = new ArrayList<>();

        DestinationCardDeck destinationCardDeck = new DestinationCardDeck();
        TrainCardDeck trainCardDeck = new TrainCardDeck();

        //Delete out double routes...
        List<Route> routes = game.getAvailableRoutes();
        if(numPlayersToStart <= 3) {
            for(int route_index = 0; route_index < routes.size(); route_index++) {
                if(routes.get(route_index).toString().contains("_2")) {
                    routes.remove(route_index);
                }
            }
            //Update routes
            game.setAvailableRoutes(routes);
        }

        for (String player : playerIDs) {
            Player currentPlayer = new Player(player);

            //Set color and name
            currentPlayer.setColor(colors[i]);
            currentPlayer.setUID(player);
            i++;
            currentPlayer.setName("Player" + i);

            //Add initial 3 cards to player's destination list
            int j = 0;
            List<DestinationCard> destinationCards = new ArrayList<>();
            while (j < 3) {
                destinationCardDeck.getCard(currentPlayer);
                j++;
            }
            //currentPlayer.setTempDestinationCard(destinationCards);

            //Add 4 cards to players trainCardHand
            int k = 0;
            List<TrainCard> trainCards = new ArrayList<>();
            while (k < 4) {
                trainCards.add(trainCardDeck.drawFromDown());
                k++;
            }
            currentPlayer.setTrainCardsHand(trainCards);

            //Add object to array list
            players.add(currentPlayer);
        }

        game.setGameID(gameID);
        game.setPlayerTurn(playerIDs.get(0));
        game.setPlayerList(players);
        game.setGameSize(numPlayersToStart);
        game.setDestinationCardDeck(destinationCardDeck);
        game.setTrainCardDeck(trainCardDeck);
        System.out.println(gameID);
        GameList.SINGLETON.addGame(game);

    }
}
