package server;

import java.util.HashMap;

public class LobbyCommand implements CommandInterface {

    public LobbyCommand(HashMap<String, Object> values) {

    }

    @Override
    public Object execute() throws Exception {

        return LobbyModel.SINGLETON.getGames();
    }
}