package androidteam.cs340.tickettoride.Shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DestinationCardDeck {
    List<DestinationCard> deck = new ArrayList<>(Arrays.asList(DestinationCard.values()));
    List<DestinationCard> discard = new ArrayList<>();

    public DestinationCardDeck() {
        Collections.shuffle(deck);
    }

    public DestinationCard getCard() {

        if (deck.size() == 0 && discard.size() > 0) {
            deck = discard;
            Collections.shuffle(deck);
            discard = new ArrayList<>();
        }

        if (deck.size() > 0) {
            DestinationCard card = deck.get(0);
            deck.remove(0);
            return card;
        }

        return null;
    }

    public void returnCard(DestinationCard card) {
        assert !deck.contains(card) && !discard.contains(card);

        discard.add(card);
    }

    public List<DestinationCard> getDeck() { return deck;}
    public List<DestinationCard> getDiscard() { return discard;}
}
