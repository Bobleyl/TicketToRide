package androidteam.cs340.tickettoride.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DestinationCardDeck {
    List<DestinationCard> deck = new ArrayList<>(Arrays.asList(DestinationCard.values()));

    public DestinationCardDeck() {
        Collections.shuffle(deck);
    }

    public boolean getCard(Player player) {

        int cardsDrawn = 0;

        if (deck.size() > 0) {
            cardsDrawn++;
            DestinationCard card = deck.get(0);
            deck.remove(0);
            player.addToTempDestHand(card);
        }

        return cardsDrawn != 0;

    }

    public void returnCard(DestinationCard card) {
        assert !deck.contains(card);
        deck.add(card);
    }

    public List<DestinationCard> getDeck() { return deck;}
}
