package server;

import java.util.Map;

public class DeleteGameCommand implements CommandInterface {

    String gameID;

    public DeleteGameCommand(Map<String, Object> values) {

        this.gameID = (String) values.get("game_id");

    }

    @Override
    public Object execute() throws Exception {

        LobbyModel.SINGLETON.deleteGame(this.gameID);
        return null;

    }
}
