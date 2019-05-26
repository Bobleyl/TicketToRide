package server;

import server.shared.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawDestinationCardCommand implements CommandInterface {

    String gameID;
    String playerID;

    public DrawDestinationCardCommand(HashMap<String, Object> values) {

        this.gameID = (String)values.get("game_id");
        this.playerID = (String)values.get("player_id");
    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();
        int drawnCards = 0;

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if (player.getUID().equals(playerID)) {

                        int j = 0;
                        DestinationCardDeck destinationCardDeck = game.getDestinationCardDeck();
                        List<DestinationCard> playerDeck = player.getDestinationHand();

                        while (j < 3) {
                            DestinationCard destinationCard = destinationCardDeck.getCard();
                            if (destinationCard != null) {
                                playerDeck.add(destinationCard);
                                drawnCards++;
                            }
                            j++;
                        }
                        player.setDestinationHand(playerDeck);
                        successfulExecute = true;
                        returnMessage.setReponseMessage("success");
                    }
                }
            }
        }

        if (successfulExecute && drawnCards > 0) {
            return returnMessage;
        } else {
            throw new Exception("No destination cards available...");
        }
    }
}
