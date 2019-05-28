package server;


import Shared.DestinationCard;
import Shared.DestinationCardDeck;
import Shared.GameModel;
import Shared.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReturnDestinationCardCommand implements CommandInterface {

    String gameID;
    String playerID;
    ArrayList<DestinationCard> destinationCards;

    public ReturnDestinationCardCommand(HashMap<String, Object> values) {

        Gson gson = new Gson();

        //Allow arrayList
        TypeToken<List<DestinationCard>> token = new TypeToken<List<DestinationCard>>() {};

        //Load values into arrayList
        this.destinationCards = gson.fromJson((String)values.get("destination_card"), token.getType());
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

                        DestinationCardDeck destinationCardDeck = game.getDestinationCardDeck();
                        List<DestinationCard> playersDestCards = player.getDestinationHand();
                        for(DestinationCard destinationCard : destinationCards) {
                            destinationCardDeck.returnCard(destinationCard);
                            playersDestCards.remove(destinationCard);
                        }
                        player.setDestinationHand(playersDestCards);

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