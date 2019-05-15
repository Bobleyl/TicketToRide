package server;

import java.util.HashMap;
import java.util.UUID;

public class JoinGameCommand implements CommandInterface {

    private String gameID;
    private String playerID;

    public JoinGameCommand(HashMap<String, Object> values) {
        this.playerID = (String)values.get("player_id");
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        //LobbyModel.SINGLETON.addGame(, playerID);
        LobbyModel.SINGLETON.addPlayerToGame(playerID, gameID);
        return null;
    }


}