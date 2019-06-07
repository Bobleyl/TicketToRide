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
    private boolean finalTurn;

    private ArrayList<TrainCard> orangeCards = new ArrayList<>();
    private ArrayList<TrainCard> whiteCards = new ArrayList<>();
    private ArrayList<TrainCard> redCards = new ArrayList<>();
    private ArrayList<TrainCard> wildCards = new ArrayList<>();
    private ArrayList<TrainCard> blueCards = new ArrayList<>();
    private ArrayList<TrainCard> greenCards = new ArrayList<>();
    private ArrayList<TrainCard> pinkCards = new ArrayList<>();
    private ArrayList<TrainCard> blackCards = new ArrayList<>();
    private ArrayList<TrainCard> yellowCards = new ArrayList<>();

    private void computeNumberOfCardColors () {

        orangeCards = new ArrayList<>();
        whiteCards = new ArrayList<>();
        redCards = new ArrayList<>();
        wildCards = new ArrayList<>();
        blueCards = new ArrayList<>();
        greenCards = new ArrayList<>();
        pinkCards = new ArrayList<>();
        blackCards = new ArrayList<>();
        yellowCards = new ArrayList<>();

        for (TrainCard card : trainCardsHand) {
            switch (card.color) {
                case "orange":
                    orangeCards.add(card);
                    break;
                case "white":
                    whiteCards.add(card);
                    break;
                case "red":
                    redCards.add(card);
                    break;
                case "wild":
                    wildCards.add(card);
                    break;
                case "blue":
                    blueCards.add(card);
                    break;
                case "green":
                    greenCards.add(card);
                    break;
                case "pink":
                    pinkCards.add(card);
                    break;
                case "black":
                    blackCards.add(card);
                    break;
                case "yellow":
                    yellowCards.add(card);
                    break;
                default:
                    System.out.println("ERROR! Card color does not exist.");
                    break;

            }
        }
    }


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
        this.finalTurn = false;
        computeNumberOfCardColors();
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

    public String getUid() {
        return uid;
    }

    public ArrayList<TrainCard> getOrangeCards() {
        computeNumberOfCardColors();
        return orangeCards;
    }

    public ArrayList<TrainCard> getWhiteCards() {

        computeNumberOfCardColors();
        return whiteCards;
    }

    public ArrayList<TrainCard> getRedCards() {
        computeNumberOfCardColors();
        return redCards;
    }

    public ArrayList<TrainCard> getWildCards() {
        computeNumberOfCardColors();
        return wildCards;
    }

    public ArrayList<TrainCard> getBlueCards() {
        computeNumberOfCardColors();
        return blueCards;
    }

    public ArrayList<TrainCard> getGreenCards() {
        computeNumberOfCardColors();
        return greenCards;
    }

    public ArrayList<TrainCard> getPinkCards() {
        computeNumberOfCardColors();
        return pinkCards;
    }

    public ArrayList<TrainCard> getBlackCards() {
        computeNumberOfCardColors();
        return blackCards;
    }

    public ArrayList<TrainCard> getYellowCards() {
        computeNumberOfCardColors();
        return yellowCards;
    }
    public void setDestinationsFoundPoints(int destinationsFoundPoints_) { destinationsFoundPoints = destinationsFoundPoints_; }

    public int getDestinationsFoundPoints() { return destinationsFoundPoints; }

    public void setDestinationsNotFoundPoints(int destinationsNotFoundPoints_) { destinationsNotFoundPoints = destinationsNotFoundPoints_; }

    public int getDestinationsNotFoundPoints() { return destinationsNotFoundPoints; }

    public void setFinalTurnTrue() { finalTurn = true; }

    public boolean getFinalTurn() { return finalTurn; }

}
