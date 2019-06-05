package androidteam.cs340.tickettoride.Client.State;

public class EndTurn implements State {

    public static final EndTurn SINGLETON = new EndTurn();

    private EndTurn() {}

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
        throw new RuntimeException();
    }

    public void drawFaceUpNonWild(TurnState turnState) {
        throw new RuntimeException();
    }

}
