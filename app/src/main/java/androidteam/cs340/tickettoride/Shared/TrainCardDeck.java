package androidteam.cs340.tickettoride.Shared;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class TrainCardDeck {
    private TrainCard[] upDeck = new TrainCard[5];
    private List<TrainCard> downDeck = new ArrayList<>();
    private List<TrainCard> discard = new ArrayList<>();

    public TrainCardDeck(){
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

    public static TrainCard[] removeTheElement(TrainCard[] arr, int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        TrainCard[] anotherArray = new TrainCard[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    //Removes card from position in upDeck and replaces the card in the upDeck
    //If three face up cards are locomotives.. place 5 cards into discard and redraw 5 new cards
    public TrainCard drawFromUp(int position) {
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

        if (nextCard == null) {
            upDeck = removeTheElement(upDeck, position);
        } 

        //Check to see if there are 3 locomotives
        //If there are.. add the face up cards to the discard
        //Take the top 5 cards from the deck and replace the faceup cards
        if ((downDeck.size() > 0 || discard.size() > 0) && upDeck.length == 5) {

            boolean foundThreeLoco = false;

            while (!foundThreeLoco) {

                int locomotiveCount = 0;

                for (TrainCard trainCard : upDeck) {
                    if (trainCard.color.equals("wild")) {
                        locomotiveCount++;
                    }
                }

                if (locomotiveCount >= 3) {

                    discard.addAll(Arrays.asList(upDeck));
                    upDeck = new TrainCard[5];

                    //Think the error is coming from here...
                    for (int i = 0; i < 5; i++) {
                        if (downDeck.size() > 0) {
                            upDeck[i] = downDeck.get(0);
                            downDeck.remove(0);
                        } else {
                            refreshDownDeck();
                        }
                    }

                } else {
                    foundThreeLoco = true;
                }
            }
        }

        return card;
    }

    public void returnCard(TrainCard card) {
        assert !discard.contains(card);

        discard.add(card);
    }

    private void refreshDownDeck() {
        downDeck = discard;
        Collections.shuffle(downDeck);
        discard = new ArrayList<>();
    }

    public List<TrainCard> getDownDeck() { return downDeck;}
    public TrainCard[] getUpDeck() { return upDeck; }
    public List<TrainCard> getDiscard() { return discard;}

}
