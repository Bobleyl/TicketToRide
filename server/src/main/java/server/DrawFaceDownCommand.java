package server;

import Shared.GameModel;
import Shared.Player;
import Shared.TrainCard;
import Shared.TrainCardDeck;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class DrawFaceDownCommand implements CommandInterface {

    String gameID;
    String playerID;

    public DrawFaceDownCommand(Map<String, Object> values) {

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
                    if (player.getUID().equals(playerID)) {
                        TrainCardDeck trainCardDeck = game.getTrainCardDeck();
                        List<TrainCard> trainCards = new ArrayList<>();
                        trainCards = player.getTrainCardsHand();
                        TrainCard trainCard = trainCardDeck.drawFromDown();
                        if (trainCard != null) {
                            trainCards.add(trainCard);
                            successfulExecute = true;
                            returnMessage.setReponseMessage("success");
                        } else {
                            successfulExecute = false;
                        }

                        player.setTrainCardsHand(trainCards);

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