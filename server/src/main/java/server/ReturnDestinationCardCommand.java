package server;


import Shared.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ReturnDestinationCardCommand implements CommandInterface {

    String gameID;
    String playerID;
    ArrayList<DestinationCard> destinationCards;

    public ReturnDestinationCardCommand(Map<String, Object> values) {

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

                        //Grab all the desination cards...
                        DestinationCardDeck destinationCardDeck = game.getDestinationCardDeck();

                        //Grab tempDestCards
                        List<DestinationCard> tempDestCardDeck = player.getTempDestinationCard();

                        //Grab players current destination cards
                        List<DestinationCard> currentDestCards = player.getDestinationHand();

                        //Go through all the cards that were supposed to be returned..
                        for(DestinationCard destinationCard : destinationCards) {
                            destinationCardDeck.returnCard(destinationCard);
                            tempDestCardDeck.remove(destinationCard);
                        }


                        currentDestCards.addAll(tempDestCardDeck);

                        player.setDestinationHand(currentDestCards);

                        //Empty temp hand
                        player.setTempDestinationCard(new ArrayList<>());

                        game.setDestinationCardDeck(destinationCardDeck);

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