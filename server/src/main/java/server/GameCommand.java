package server;

import androidteam.cs340.tickettoride.Shared.GameModel;

import java.util.HashMap;

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

        throw new Exception("Failed to return game");

    }
}
