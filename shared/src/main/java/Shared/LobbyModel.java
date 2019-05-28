package Shared;

import java.util.ArrayList;

public class LobbyModel {
    public static final LobbyModel SINGLETON = new LobbyModel();

    private ArrayList<LobbyGameModel> games = new ArrayList<>();

    private LobbyModel() {};

    public void setGames(ArrayList<LobbyGameModel> lobbyGameModels) { games = lobbyGameModels; }

}
