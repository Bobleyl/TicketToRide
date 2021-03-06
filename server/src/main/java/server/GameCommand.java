package server;


import Shared.CommandInterface;
import Shared.GameList;
import Shared.GameModel;

import java.util.Map;

public class GameCommand implements CommandInterface {

    String gameID;

    public GameCommand(Map<String, Object> values) {
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                return game;
            }
        }

        return null;
    }
}
