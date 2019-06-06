package server;

import Shared.*;

import java.util.HashMap;
import java.util.List;

public class LastTurnCommand implements CommandInterface {

    String gameID;
    String playerID;

    public LastTurnCommand(HashMap<String, Object> values) {

        this.gameID = (String)values.get("game_id");
        this.playerID = (String)values.get("player_id");

    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if(player.getUID().equals(playerID)) {
                        player.setFinalTurnTrue();
                    }
                }
            }
        }

        if (successfulExecute) {
            return returnMessage;
        } else {
            throw new Exception("Failed to claim route");
        }
    }
}

