package Shared;

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

    public boolean getCard(Player player) {

        Integer cardsDrawn = 0;

        if (deck.size() == 0 && discard.size() > 0) {
            deck = discard;
            Collections.shuffle(deck);
            discard = new ArrayList<>();
        }

        if (deck.size() > 0) {
            cardsDrawn++;
            DestinationCard card = deck.get(0);
            deck.remove(0);
            player.addToTempDestHand(card);
        }

        if (cardsDrawn == 0) {
            return false;
        } else {
            return true;
        }

    }

    public void returnCard(DestinationCard card) {
        assert !deck.contains(card) && !discard.contains(card);

        discard.add(card);
    }

    public List<DestinationCard> getDeck() { return deck;}
    public List<DestinationCard> getDiscard() { return discard;}
}
