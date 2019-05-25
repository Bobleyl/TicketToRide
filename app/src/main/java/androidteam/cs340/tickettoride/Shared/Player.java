package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String color;
    private String name;
    private String UID;

    private List<DestinationCard> destinationHand;
    private List<TrainCard> trainCardsHand;
    private int score;
    private int trainCars;


    public Player(String UID){
        this.color = color;
        this.name = name;
        this.UID = UID;
        this.destinationHand = new ArrayList<>();
        this.trainCardsHand = new ArrayList<>();
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
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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

    public void drawFaceUp(){
        //Reference Active Face up deck's drawFromUp() method
    }

    public void drawFaceDown(){
        //Reference Active Face down deck's drawFromDown() method
    }

    public void drawDestinationCard(){
        //Reference active DestinationCard Deck's method for drawCard()
    }

    public void claimRoute(){
        //Reference Route's method for route claiming
    }

}
