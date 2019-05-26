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

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                for (Player player : game.getPlayersList()) {
                    if(player.getUID().equals(playerID)) {

                        List<TrainCard> trainCards = player.getTrainCardsHand();
                        //Make sure player has enough colors to claim route
                        int foundColors = 0;

                        System.out.println("Route Color: " + route.color);
                        System.out.println("Route Length: " + route.length);

                        for(TrainCard trainCard : trainCards) {
                            System.out.println("Train: " + trainCard.color);
                            if(trainCard.color.equals(route.color) ||
                                    trainCard.color.equals(TrainCard.Locomotive.color) ||
                                    trainCard.color.equals("grey")) {
                                foundColors++;
                            }
                        }

                        if (foundColors >= route.length) {

                            player.claimRoute(route);
                            System.out.println("claimed route");
                            //Update points, length to point relation
                            //1:1, 2:2, 3:4, 4:7, 5:10, 6:15
                            int score = 0;
                            int length = route.length;

                            //Logic to increase player score
                            if (length == 1) {
                                score = 1;
                            }
                            else if (length == 2) {
                                score = 2;
                            }
                            else if (length == 3) {
                                score = 4;
                            }
                            else if (length == 4) {
                                score = 7;
                            }
                            else if (length == 5) {
                                score = 10;
                            }
                            else if (length == 6) {
                                score = 15;
                            }
                            //Take original score and add it to new score
                            player.setScore(player.getScore()+score);

                        } else {
                            System.out.println("Player doesn't have enough cards to claim route (" +
                                    route  + ")" + " found colors: " + foundColors);
                        }

                    }
                }
            }
        }

        return null;
    }
}
