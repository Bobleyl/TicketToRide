package server;

import java.util.HashMap;
import java.util.UUID;

public class JoinGameCommand implements CommandInterface {

    private String gameID;
    private String playerID;

    public RegisterCommand(HashMap<String, Object> values) {
        this.numPlayersToStart = (Integer)values.get("player_id");
        this.playerID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        LobbyModel.SINGLETON.addGame(numPlayersToStart, playerID);
        return null;
    }


}