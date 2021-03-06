package server;

import Shared.CommandInterface;

import java.util.Map;

public class CreateGameCommand implements CommandInterface {

    private int numPlayersToStart;
    private String playerID;

    public CreateGameCommand(Map<String, Object> values) {
        this.numPlayersToStart = (Integer)values.get("number_players");
        this.playerID = (String)values.get("player_id");
    }

    @Override
    public Object execute() throws Exception {

        return LobbyModel.SINGLETON.addGame(numPlayersToStart, playerID).getGameID();
    }


}