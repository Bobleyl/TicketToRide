package server.shared;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class TrainCardDeck {
    private TrainCard[] upDeck = new TrainCard[5];
    private List<TrainCard> downDeck = new ArrayList<>();
    private List<TrainCard> discard = new ArrayList<>();

    TrainCardDeck(){
        List uniqueCards = Arrays.asList(TrainCard.values());

        for (int i = 0; i < 12; i++) {

            downDeck.addAll(uniqueCards);
        }

        downDeck.add(TrainCard.Locomotive);
        downDeck.add(TrainCard.Locomotive);

        Collections.shuffle(downDeck);

        for (int i = 0; i < 5; i++) {

            upDeck[i] = downDeck.get(0);
            downDeck.remove(0);
        }
    }

    //Removes and returns the top card of the downDeck
    public TrainCard drawFromDown(){
        if (downDeck.size() == 0 && discard.size() > 0) {
            refreshDownDeck();
        }

        if (downDeck.size() > 0) {
            TrainCard card = downDeck.get(0);
            downDeck.remove(0);
            return card;
        }

        return null;
    }

    //Removes card from position in upDeck and replaces the card in the upDeck
    public TrainCard drawFromUp(int position){
        assert position >= 0 && position < upDeck.length;
        assert upDeck[position] != null;

        if (downDeck.size() == 0 && discard.size() > 0) {
            refreshDownDeck();
        }

        TrainCard card = upDeck[position];
        TrainCard nextCard = null;
        if (downDeck.size() > 0) {
            nextCard = downDeck.get(0);
            downDeck.remove(0);
        }
        upDeck[position] = nextCard;

        return card;
    }

    private void refreshDownDeck() {
        downDeck = discard;
        Collections.shuffle(downDeck);
        discard = new ArrayList<>();
    }

}
