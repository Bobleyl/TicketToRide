package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import androidteam.cs340.tickettoride.Shared.TrainCard;


public class TrainCardDeck {
    private List<TrainCard> upDeck;
    private List<TrainCard> downDeck;

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

    //parses through list of cards returned and inserts back into downDeck in random locations
    public void returnCards(List<TrainCard> cards){
        int listSize = downDeck.size();
        for(TrainCard i : cards) {
            int random = new Random().nextInt(listSize);
            downDeck.add(random, i);
        }
    }
}
