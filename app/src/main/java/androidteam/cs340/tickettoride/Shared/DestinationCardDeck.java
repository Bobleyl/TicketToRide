package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;
import androidteam.cs340.tickettoride.Shared.DestinationCard;

public class DestinationCardDeck {
    private List<DestinationCard> destinationDeck;

    // Returns the top card on the deck
    public DestinationCard drawCard(){
        DestinationCard card = destinationDeck.get(0);
        destinationDeck.remove(0);
        return card;
    }
}
