package server;

import java.util.ArrayList;
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
            //Start game

            //Remove game from lobby
            LobbyModel.SINGLETON.deleteGame(gameID);
        }

        return true;
    }
}
