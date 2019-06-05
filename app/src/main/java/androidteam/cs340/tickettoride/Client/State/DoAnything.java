package androidteam.cs340.tickettoride.Client.State;

import androidteam.cs340.tickettoride.Client.Phase2Facade;

public class DoAnything implements State {

    public static final DoAnything SINGLETON = new DoAnything();

    private DoAnything() {}

    public void drawDestinationCard(TurnState turnState) {
        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
        Phase2Facade.SINGLETON.endTurn();
    }

    public void claimRoute(TurnState turnState) {
        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
        Phase2Facade.SINGLETON.endTurn();
    }

    public void drawFaceUpWild(TurnState turnState) {
        TurnState.SINGLETON.setState(EndTurn.SINGLETON);
        Phase2Facade.SINGLETON.endTurn();
    }

    public void drawFaceDown(TurnState turnState) {
        TurnState.SINGLETON.setState(DownNotWild.SINGLETON);
    }

    public void drawFaceUpNonWild(TurnState turnState) {
        TurnState.SINGLETON.setState(DownNotWild.SINGLETON);
    }

}
