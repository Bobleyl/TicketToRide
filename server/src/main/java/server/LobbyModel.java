package server;

import java.util.ArrayList;

public class LobbyModel {
    public static final LobbyModel SINGLETON = new LobbyModel();

    private ArrayList<LobbyGameModel> games = new ArrayList<>();

    private LobbyModel() {};

    LobbyGameModel addGame(int numPlayersToStart, String playerID) {

        LobbyGameModel newGame = new LobbyGameModel(numPlayersToStart, playerID);

        games.add(newGame);

        return newGame;
    }

    void deleteGame(String gameID) {

        for (int i = 0; i < games.size(); i++) {

            if (gameID.equals(games.get(i).getGameID())) {

                games.remove(i);
                break;
            }
        }
    }

    public ArrayList<LobbyGameModel> getGames() {
        return games;
    }
}
