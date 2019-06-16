package server;

import Shared.GameModel;
import Shared.Player;

import java.util.Map;
import java.util.List;

public class EndTurnCommand implements CommandInterface {

    String gameID;

    public EndTurnCommand(Map<String, Object> values) {
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        for(GameModel game : GameList.SINGLETON.getGames()) {

            if (game.getGameID().equals(gameID)) {

                List<Player> players = game.getPlayersList();

                for (int i = 0; i < players.size(); i++) {

                    Player player = players.get(i);

                    if (player.getUID().equals(game.getPlayerTurn()) && i < (players.size() - 1)) {

                        game.setPlayerTurn(players.get(i + 1).getUID());
                        break;
                    } else if (player.getUID().equals(game.getPlayerTurn()) && i == (players.size() - 1)) {

                        game.setPlayerTurn(players.get(0).getUID());
                        break;
                    }
                }
            }
        }

        return null;
    }
}
