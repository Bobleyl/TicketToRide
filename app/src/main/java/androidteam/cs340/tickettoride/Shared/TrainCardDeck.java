package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import androidteam.cs340.tickettoride.Shared.TrainCard;


public class TrainCardDeck {
    private List<TrainCard> upDeck;
    private List<TrainCard> downDeck;

    TrainCardDeck(){
        this.upDeck = new ArrayList<>();
        this.downDeck = new ArrayList<>();
    }

    //Removes and returns the top card of the downDeck
    public TrainCard drawFromDown(){
        TrainCard card = downDeck.get(0);
        downDeck.remove(0);
        return card;
    }

    //Removes card from position in upDeck and replaces the card in the upDeck
    public TrainCard drawFromUp(int position){
        TrainCard card = upDeck.get(position);
        upDeck.remove(position);
        TrainCard newCard = downDeck.get(0);
        downDeck.remove(0);
        upDeck.add(position, newCard);
        return card;
    }

    public void setUpDeck(List<TrainCard> cardDeck){
        upDeck = cardDeck;
    }

    public void setDownDeck(List<TrainCard> downCardDeck){
        downDeck = downCardDeck;
    }

    public List<TrainCard> getDownDeck(){
        return downDeck;
    }

    public List<TrainCard> getUpDeck(){
        return upDeck;
    }

}
