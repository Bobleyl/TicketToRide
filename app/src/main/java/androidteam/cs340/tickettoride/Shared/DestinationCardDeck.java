package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;
import androidteam.cs340.tickettoride.Shared.DestinationCard;

public class DestinationCardDeck {
    private List<DestinationCard> destinationDeck;

    DestinationCardDeck() {
        this.destinationDeck = new ArrayList<>();
    }

    // Returns the top 3 cards from the deck
    public List<DestinationCard> drawCard(){
        int x = 3;
        List<DestinationCard> destinationList = new ArrayList<>();
        while(x > 0){
            DestinationCard card = destinationDeck.get(0);
            destinationList.add(card);
            destinationDeck.remove(0);
            x--;
        }
        return destinationList;
    }
}
