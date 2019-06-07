package server;


import Shared.*;

import java.util.HashMap;

public class ReturnEndGameCommand implements CommandInterface {

    String gameID;

    public ReturnEndGameCommand(HashMap<String, Object> values) {
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        for(EndGame game : EndGameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                return game;
            }
        }

        return null;
    }
}