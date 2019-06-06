package server;

import Shared.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

public class ClaimRouteCommand implements CommandInterface {

    String gameID;
    String playerID;
    Route route;
    List<TrainCard> trainCards;

    public ClaimRouteCommand(HashMap<String, Object> values) {

        Gson gson = new Gson();

        this.gameID = (String)values.get("game_id");
        this.playerID = (String)values.get("player_id");
        this.route = gson.fromJson((String)values.get("route"), Route.class);

        //Allow arrayList
        TypeToken<List<TrainCard>> token = new TypeToken<List<TrainCard>>() {};

        //Load values into arrayList
        this.trainCards  = gson.fromJson((String) values.get("train_cards"), token.getType());

    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if(player.getUID().equals(playerID)) {

                        if (trainCards.size() == route.length) {
                            successfulExecute = true;
                            returnMessage.setReponseMessage("success");

                            List<TrainCard> playersHand = player.getTrainCardsHand();
                            TrainCardDeck trainCardDecks = game.getTrainCardDeck();

                            for (TrainCard trainCard : trainCards) {
                                playersHand.remove(trainCard);
                                trainCardDecks.returnCard(trainCard);
                            }

                            player.setTrainCardsHand(playersHand);
                            player.claimRoute(route);
                            player.setTrainCars(player.getTrainCars()-route.length);

                        }

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

