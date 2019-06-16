package server;

import java.util.Map;

public class LobbyCommand implements CommandInterface {

    public LobbyCommand(Map<String, Object> values) {

    }

    @Override
    public Object execute() throws Exception {

        return LobbyModel.SINGLETON.getGames();
    }
}