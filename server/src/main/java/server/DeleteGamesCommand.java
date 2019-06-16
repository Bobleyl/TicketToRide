package server;

import Shared.CommandInterface;

import java.util.Map;

public class DeleteGamesCommand implements CommandInterface {

    public DeleteGamesCommand(Map<String, Object> values) {

    }

    @Override
    public Object execute() throws Exception {

        ServerCommunicator.factory.getGameDAO().deleteGames();
        return null;

    }
}
