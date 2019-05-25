package server;

import server.shared.GameList;
import server.shared.GameModel;

import java.util.HashMap;
import java.util.List;

public class GameCommand implements CommandInterface {

    String gameID;

    public GameCommand(HashMap<String, Object> values) {
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
