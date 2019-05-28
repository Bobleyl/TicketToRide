package server;

import java.util.HashMap;

public class DeleteGameCommand implements CommandInterface {

    String gameID;

    public DeleteGameCommand(HashMap<String, Object> values) {

        this.gameID = (String) values.get("game_id");

    }

    @Override
    public Object execute() throws Exception {

        LobbyModel.SINGLETON.deleteGame(this.gameID);
        return null;

    }
}
