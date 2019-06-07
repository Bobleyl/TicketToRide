package server;

import Shared.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class EndGameCommand implements CommandInterface {

    String gameID;

    public EndGameCommand(HashMap<String, Object> values) {
        this.gameID = (String)values.get("game_id");
    }

    @Override
    public Object execute() throws Exception {

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                return(calculateDestinationCards(game));
            }
        }

        return null;
    }

    public static EndGame calculateDestinationCards(GameModel gameModel) {
        EndGame endGame = new EndGame();

        List<Player> updatedPlayers = new ArrayList<>();

        for (Player player : gameModel.getPlayersList()) {

            ArrayList<String> currentRouteArray = new ArrayList<>();
            TreeSet<DestinationCard> completedCards = new TreeSet<>();
            TreeSet<DestinationCard> nonCompletedCards = new TreeSet<>();

            for (Route route : player.getClaimedRoutes()) {

                String[] route_split = route.toString().split("_");

                //Guarentee to pick up all matches..
                for (int i = 0; i < player.getClaimedRoutes().size(); i++) {

                    for (Route city_city : player.getClaimedRoutes()) {

                        String[] city_split = city_city.toString().split("_");

                        //Add initial connecting routes
                        if (currentRouteArray.isEmpty()) {
                            currentRouteArray.add(route_split[0]);
                            currentRouteArray.add(route_split[1]);
                        }

                        //Example:
                        //currentRouteArray contains Sanfransico -> SaltLakeCity
                        //if the current city_city = SaltLakeCity -> LasVegas
                        //We want to add LasVegas and not SaltLakeCity
                        else if (currentRouteArray.contains(city_split[0]) && !currentRouteArray.contains(city_split[1])) {
                            currentRouteArray.add(city_split[1]);
                        }

                        //Example:
                        //currentRouteArray contains Sanfransico -> SaltLakeCity
                        //if the current city_city = SaltLakeCity -> LasVegas
                        //We want to add SaltLakeCity and not LasVegas
                        else if (currentRouteArray.contains(city_split[1]) && !currentRouteArray.contains(city_split[0])) {
                            currentRouteArray.add(city_split[0]);
                        }

                    }
                }

                for (DestinationCard destinationCard : player.getDestinationHand()) {

                    String[] destination_route_split = destinationCard.toString().split("_");
                    int i = 0;

                    for (String destination_route : destination_route_split) {

                        if (currentRouteArray.contains(destination_route)) {
                            i++;
                        }

                    }

                    if (i == 2) {
                        completedCards.add(destinationCard);
                    }
                    else {
                        nonCompletedCards.add(destinationCard);
                    }
                }

                currentRouteArray = new ArrayList<>();
            }

            int points = player.getScore();
            int foundDestinationPoints = 0;
            int notFoundDestinationPoints = 0;

            for (DestinationCard destinationCard : completedCards) {
                points += destinationCard.points;
                foundDestinationPoints += destinationCard.points;
            }

            for (DestinationCard destinationCard : nonCompletedCards) {
                points -= destinationCard.points;
                notFoundDestinationPoints += destinationCard.points;
            }

            player.setScore(points);
            player.setDestinationsFoundPoints(foundDestinationPoints);
            player.setDestinationsNotFoundPoints(notFoundDestinationPoints);

            updatedPlayers.add(player);
        }

        String firstPlace = "";
        int maxPoints = -10000;

        List<Player> longestPath = gameModel.findLongestRoute();
        List<String> longestPathPlayerList = new ArrayList<>();

        for (Player player : longestPath) {
            longestPathPlayerList.add(player.getUID());
            player.setScore(player.getScore()+10);
        }

        endGame.setLongestRoadPlayers(longestPathPlayerList);

        //If they tie... Add some logic
        for (Player player : updatedPlayers) {
            if (player.getScore() > maxPoints) {
                maxPoints = player.getScore();
                firstPlace = player.getUID();
            }
        }

        endGame.setFirstPlace(firstPlace);
        endGame.setPlayers(updatedPlayers);

        return endGame;
    }
}