package server;

import com.google.gson.Gson;
import server.shared.*;

import java.util.HashMap;
import java.util.List;

public class ClaimRouteCommand implements CommandInterface {

    String gameID;
    String playerID;
    Route route;

    public ClaimRouteCommand(HashMap<String, Object> values) {

        Gson gson = new Gson();

        this.gameID = (String)values.get("game_id");
        this.playerID = (String)values.get("player_id");
        this.route = gson.fromJson((String)values.get("route"), Route.class);

    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if(player.getUID().equals(playerID)) {

                        List<TrainCard> trainCards = player.getTrainCardsHand();
                        TrainCardDeck trainCardDeck = game.getTrainCardDeck();

                        //Make sure player has enough colors to claim route
                        int foundColors = 0;

                        System.out.println("Route Color: " + route.color);
                        System.out.println("Route Length: " + route.length);

                        for(TrainCard trainCard : trainCards) {
                            System.out.println("Train: " + trainCard + ", color: " + trainCard.color);
                            //Idea is if the route color is grey, they can use any cards..
                            //If they have a wild that will increase the count
                            //Or if they have a card that matches the route color
                            if(trainCard.color.equals(route.color) ||
                                    trainCard.color.equals(TrainCard.Locomotive.color) ||
                                    trainCard.color.equals("grey")) {
                                foundColors++;
                            }
                        }

                        if (foundColors >= route.length) {

                            System.out.println("claimed route");
                            player.claimRoute(route);
                            //Update points, length to point relation
                            //1:1, 2:2, 3:4, 4:7, 5:10, 6:15
                            int score = 0;
                            int length = route.length;

                            //Logic to increase player score
                            switch (length) {
                                case 1:
                                    score = 1;
                                    break;
                                case 2:
                                    score = 2;
                                    break;
                                case 3:
                                    score = 4;
                                    break;
                                case 4:
                                    score = 7;
                                    break;
                                case 5:
                                    score = 10;
                                    break;
                                case 6:
                                    score = 15;
                                    break;
                            }

                            //Take original score and add it to new score
                            player.setScore(player.getScore()+score);
                            successfulExecute = true;
                            returnMessage.setReponseMessage("success");

                            //Now remove cards from players deck
                            int maxRemove = 0;
                            for(int j = 0; j < trainCards.size(); j++) {
                                if (trainCards.get(j).color.equals(route.color) ||
                                        trainCards.get(j).color.equals(TrainCard.Locomotive.color) ||
                                        route.color.equals("grey")) {
                                    //System.out.println(trainCards.indexOf(trainCard));
                                    trainCardDeck.returnCard(trainCards.get(j));
                                    System.out.println("Removed: " + trainCards.get(j));
                                    trainCards.remove(j);
                                    //Don't remove more cards than the length of the route
                                    maxRemove++;
                                    if (maxRemove == route.length) {
                                        break;
                                    }
                                }
                            }

                            //Update how many trainCars the player has
                            player.setTrainCars(player.getTrainCars()-route.length);

                        } else {
                            System.out.println("Player doesn't have enough cards to claim route (" +
                                    route  + ")" + " found colors: " + foundColors);
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
