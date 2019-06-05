package androidteam.cs340.tickettoride.Client.State;

public class TurnState {

    private State state;

    public static final TurnState SINGLETON = new TurnState();

    private TurnState() {
        state = DoAnything.SINGLETON;
    }

    public void drawDestinationCard() {
        state.drawDestinationCard(this);
    }

    public void claimRoute() {
        state.claimRoute(this);
    }

    public void drawFaceUpWild() {
        state.drawFaceUpWild(this);
    }

    public void drawFaceDown() {
        state.drawFaceDown(this);
    }

    public void drawFaceUpNonWild() {
        state.drawFaceUpNonWild(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAnythingState() {
        return state instanceof DoAnything;
    }

    public boolean isEndState() {
        return state instanceof EndTurn;
    }
}
