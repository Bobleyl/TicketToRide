package server;


import Shared.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class DrawFaceUpCommand implements CommandInterface {

    String gameID;
    String playerID;
    Integer position;

    public DrawFaceUpCommand(Map<String, Object> values) {

        this.gameID = (String)values.get("game_id");
        this.playerID = (String)values.get("player_id");
        this.position = (Integer) values.get("position");
    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if (player.getUID().equals(playerID)) {
                        TrainCardDeck trainCardDeck = game.getTrainCardDeck();
                        List<TrainCard> trainCards = new ArrayList<>();
                        trainCards = player.getTrainCardsHand();
                        trainCards.add(trainCardDeck.drawFromUp(position));
                        player.setTrainCardsHand(trainCards);
                        returnMessage.setReponseMessage("success");
                        successfulExecute = true;
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
