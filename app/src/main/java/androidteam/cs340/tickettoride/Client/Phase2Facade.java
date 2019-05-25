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
import androidteam.cs340.tickettoride.Shared.Colors;
import androidteam.cs340.tickettoride.Shared.Routes;

public class Phase2Facade {

    private Player currentPlayer;
    private Game currentGame;
    private List<Player> turnOrder;
    private List<IPresenter> presenters;
    private TrainCardDeck currentDecks;
    private DestinationCardDeck currentDestinationDeck;

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

//    public Result returnDestination(List<DestinationCard> cards) {
//        return ServerProxy.SINGLETON.returnDestination(cards);
//    }

//    public Result claimRoute(Routes route, List<DestinationCard> cards, Player player){
//        return ServerProxy.SINGLETON.claimRoute(route, cards, player);
//    }

//    public Result drawUp(String cardID, Player player){
//        return ServerProxy.SINGLETON.drawUp(cardID, player);
//    }

//    public Result drawDown(Player player){
//        return ServerProxy.SINGLETON.drawDown(player);
//    }

//    public Result drawDestination(DestinationCard card, Player player){
//        return ServerProxy.SINGLETON.drawDown(player);
//    }

//    public Result sendMessage(Message message){
//        return ServerProxy.SINGLETON.sendMessage(message);
//    }

    public TrainCard[] getUpdeck(){
        return currentDecks.getUpDeck();
    }

    public List<TrainCard> getDownDeck(){
        return currentDecks.getDownDeck();
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

}
