package androidteam.cs340.tickettoride.Client.State;

public interface State {

    public void drawDestinationCard(TurnState turnState);
    public void claimRoute(TurnState turnState);
    public void drawFaceUpWild(TurnState turnState);
    public void drawFaceDown(TurnState turnState);
    public void drawFaceUpNonWild(TurnState turnState);

}
