package androidteam.cs340.tickettoride.Client;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.DestinationCardDeck;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.TrainCard;
import androidteam.cs340.tickettoride.Shared.TrainCardDeck;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Message;
import androidteam.cs340.tickettoride.Shared.Route;

public class Phase2Facade {

    private Player currentPlayer;
    private Game currentGame;
    private List<Player> turnOrder;
    private List<IPresenter> presenters;
    private TrainCardDeck currentDecks;
    private DestinationCardDeck currentDestinationDeck;
    private DestinationCardDeck currentMyDestinationDeck;
    private List<TrainCard> currentMyDeck;

    private Phase2Facade(){
        turnOrder = new ArrayList<>();
    }

    public static Phase2Facade SINGLETON = new Phase2Facade();

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public void updatePresenter() {
        for(IPresenter presenter : presenters) {
            presenter.Update();
        }
    }

    public void removePresenter(IPresenter toDelete){
        for(int i = 0; i < presenters.size(); i++){
            if (presenters.get(i).getID().equals(toDelete.getID())){
                presenters.remove(i);
                break;
            }
        }
    }

    // COMMANDS --------------------- // ------------------------

    public Result returnDestination(ArrayList<DestinationCard> cards) {
        return ServerProxy.SINGLETON.returnDestinationCard(currentGame.getUID(), currentPlayer.getUID(),cards);
    }

    public Result claimRoute(Route route){
        return ServerProxy.SINGLETON.claimRoute(currentGame.getUID(),currentPlayer.getUID(), route);
    }

    public Result drawUp(int position){
        return ServerProxy.SINGLETON.drawTrainCardFaceUp(currentGame.getUID(), currentPlayer.getUID(), position);
    }

    public Result drawDown(){
        return ServerProxy.SINGLETON.drawTrainCardFaceDown(currentGame.getUID(),currentPlayer.getUID());
    }

    public Result drawDestination(){
        return ServerProxy.SINGLETON.drawDestinationCard(currentGame.getUID(),currentPlayer.getUID());
    }

    public Result sendMessage(Message message){
        return ServerProxy.SINGLETON.sendMessage(currentGame.getUID(),currentPlayer.getUID(),message);
    }

    public Result endTurn(){
        return ServerProxy.SINGLETON.endTurn(currentGame.getUID(), currentPlayer.getUID());
    }

    // END OF COMMANDS --------------- // ------------------


    public TrainCard[] getUpdeck(){
        return currentDecks.getUpDeck();
    }

    public List<TrainCard> getDownDeck(){
        return currentDecks.getDownDeck();
    }

    public DestinationCardDeck getDestinationDeck(){
        return this.currentDestinationDeck;
    }

    public DestinationCardDeck getMyDestinationDeck(){
        return this.currentMyDestinationDeck;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public List<Player> getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(List<Player> turnOrder) {
        this.turnOrder = turnOrder;
    }

    public List<TrainCard> getMyDeck(){
        return this.currentMyDeck;
    }

    public int getTrainCars(){
        return currentPlayer.getTrainCars();
    }

}
