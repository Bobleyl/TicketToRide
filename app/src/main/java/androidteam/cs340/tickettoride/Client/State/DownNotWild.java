package androidteam.cs340.tickettoride.Client.State;

import androidteam.cs340.tickettoride.Client.Phase2Facade;

public class DownNotWild implements State {

    public static final DownNotWild SINGLETON = new DownNotWild();

    private DownNotWild() {}

    public void drawDestinationCard(TurnState turnState) {
        throw new RuntimeException();
    }

    public void claimRoute(TurnState turnState) {
        throw new RuntimeException();
    }

    public void drawFaceUpWild(TurnState turnState) {
        throw new RuntimeException();
    }

    public void drawFaceDown(TurnState turnState) {
        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
        Phase2Facade.SINGLETON.endTurn();
    }

    public void drawFaceUpNonWild(TurnState turnState) {
        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
        Phase2Facade.SINGLETON.endTurn();
    }

}
