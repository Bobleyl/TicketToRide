package server;

import Shared.GameModel;

import java.util.HashMap;

public class EndGameCommand implements CommandInterface {

    String gameID;

    public EndGameCommand(HashMap<String, Object> values) {
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                //perform logic in here that calculates my code mixed in with Tylers code
                //Return EndGame object
            }
        }

        return null;
    }
}