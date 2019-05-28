package Shared;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String color;
    private String name;
    private String uid;

    private List<DestinationCard> destinationHand;
    private List<TrainCard> trainCardsHand;
    private List<Route> claimedRoutes;
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

    public List<DestinationCard> getDestinationHand() {
        return destinationHand;
    }

    public void addToDestinationHand(DestinationCard destinationCard) { destinationHand.add(destinationCard);}

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

}
