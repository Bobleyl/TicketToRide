package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String color;
    private String name;
    private String uid;

    private List<DestinationCard> destinationHand;
    private List<TrainCard> trainCardsHand;
    private List<DestinationCard> tempDestinationCard = new ArrayList<>();
    private List<Route> claimedRoutes;
    private int destinationsFoundPoints;
    private int destinationsNotFoundPoints;
    private int score;
    private int trainCars;


    public Player(String uid){
        this.color = "";
        this.name = "";
        this.uid = uid;
        this.destinationHand = new ArrayList<>();
        this.trainCardsHand = new ArrayList<>();
        this.claimedRoutes = new ArrayList<>();
        this.score = 0;
        this.trainCars = 45;
        this.destinationsFoundPoints = 0;
        this.destinationsNotFoundPoints = 0;
    }

    public String getName(){ return name; }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUID() {
        return this.uid;
    }

    public void setUID(String UID) {
        this.uid = uid;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<DestinationCard> getTempDestinationCard() {
        return tempDestinationCard;
    }

    public void setTempDestinationCard(List<DestinationCard> tempDestinationCard) { this.tempDestinationCard = tempDestinationCard; }

    public List<DestinationCard> getDestinationHand() {
        return destinationHand;
    }

    public void addToDestinationHand(DestinationCard destinationCard) { destinationHand.add(destinationCard);}

    public void addToTempDestHand(DestinationCard destinationCard) { tempDestinationCard.add(destinationCard);}

    public void setDestinationHand(List<DestinationCard> destinationHand) {
        this.destinationHand = destinationHand;
    }

    public List<TrainCard> getTrainCardsHand() {
        return trainCardsHand;
    }

    public void setTrainCardsHand(List<TrainCard> trainCardsHand) {
        this.trainCardsHand = trainCardsHand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(int trainCars) {
        this.trainCars = trainCars;
    }

    public List<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void setClaimedRoutes(List<Route> routes) {
        this.claimedRoutes = routes;
    }

    public void claimRoute(Route route){
        claimedRoutes.add(route);
    }

    public void setDestinationsFoundPoints(int destinationsFoundPoints_) { destinationsFoundPoints = destinationsFoundPoints_; }

    public int getDestinationsFoundPoints() { return destinationsFoundPoints; }

    public void setDestinationsNotFoundPoints(int destinationsNotFoundPoints_) { destinationsNotFoundPoints = destinationsNotFoundPoints_; }

    public int getDestinationsNotFoundPoints() { return destinationsNotFoundPoints; }

}
