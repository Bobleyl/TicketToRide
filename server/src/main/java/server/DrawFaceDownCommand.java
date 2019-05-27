package server;

import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.TrainCard;
import androidteam.cs340.tickettoride.Shared.TrainCardDeck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawFaceDownCommand implements CommandInterface {

    String gameID;
    String playerID;

    public DrawFaceDownCommand(HashMap<String, Object> values) {

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
                        trainCards.add(trainCardDeck.drawFromDown());
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