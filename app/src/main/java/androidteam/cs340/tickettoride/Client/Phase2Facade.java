package androidteam.cs340.tickettoride.Client;

import android.graphics.ColorSpace;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Poller.ParseResults;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePoller;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePollerCommand;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.GameModel;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.DestinationCardDeck;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.TrainCard;
import androidteam.cs340.tickettoride.Shared.TrainCardDeck;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Message;
import androidteam.cs340.tickettoride.Shared.Route;

public class Phase2Facade {

    private Phase2Facade(){
        //turnOrder = new ArrayList<>();
        //currentPlayer = ModelFacade.SINGLETON.getPlayer();
        //currentGame = new GameModel();
        //gamePoller = new GamePoller(command, 1000, getGameID());
    }

    public static Phase2Facade SINGLETON = new Phase2Facade();

    // TODO: GET RID OF DECKS, CALL ON GAMEMODEL TO GET INFORMATION.
    private Player currentPlayer;
    private GameModel currentGame = new GameModel();
    private List<Player> turnOrder;
    private List<IPresenter> presenters = new ArrayList<>();

    public void setGameID(String id){
        currentGame.setGameID(id);
    }

    public String getGameID(){
        return currentGame.getGameID();
    }

    public void addPresenter(IPresenter toAdd){
        presenters.add(toAdd);
    }

    public void startPoller(){
        Log.d("GAME_ACTIVITY:POLLER", getGameID());
        GamePollerCommand command = new GamePollerCommand();
        GamePoller gp = new GamePoller(command, 1000, getGameID());
        gp.start();

    }

    public void updateCurrentGame(Result result) {
        GameModel gamesModel = new GameModel();
        gamesModel = ParseResults.SINGLETON.parseGameResult(result);
        currentGame = gamesModel;
        currentPlayer = ModelFacade.SINGLETON.getPlayer();

        System.out.println("DATA::: ");
        System.out.println(currentGame);
        updatePresenter();
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
        return ServerProxy.SINGLETON.returnDestinationCard(currentGame.getGameID(), currentPlayer.getUID(),cards);
    }

    public Result claimRoute(Route route){
        return ServerProxy.SINGLETON.claimRoute(currentGame.getGameID(),currentPlayer.getUID(), route);
    }

    public Result drawUp(int position){
        return ServerProxy.SINGLETON.drawTrainCardFaceUp(currentGame.getGameID(), currentPlayer.getUID(), position);
    }

    public Result drawDown(){
        return ServerProxy.SINGLETON.drawTrainCardFaceDown(currentGame.getGameID(),currentPlayer.getUID());
    }

    public Result drawDestination(){
        return ServerProxy.SINGLETON.drawDestinationCard(currentGame.getGameID(),currentPlayer.getUID());
    }

    public Result sendMessage(Message message){
        return ServerProxy.SINGLETON.sendMessage(currentGame.getGameID(),currentPlayer.getUID(),message);
    }

    public Result endTurn(){
        return ServerProxy.SINGLETON.endTurn(currentGame.getGameID(), currentPlayer.getUID());
    }

    // END OF COMMANDS --------------- // ------------------


    public TrainCard[] getUpdeck(){
        return currentGame.getTrainCardDeck().getUpDeck();
    }

    public List<TrainCard> getDownDeck(){
        return currentGame.getTrainCardDeck().getDownDeck();
    }

    public DestinationCardDeck getDestinationDeck(){
        return currentGame.getDestinationCardDeck();
    }

    public List<DestinationCard> getMyDestinationDeck(){
        return currentPlayer.getDestinationHand();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameModel getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(GameModel currentGame) {
        this.currentGame = currentGame;
    }

    public List<Player> getTurnOrder() {
        return turnOrder;
    }

    public void setTurnOrder(List<Player> turnOrder) {
        this.turnOrder = turnOrder;
    }

    public List<TrainCard> getMyDeck(){
        return currentPlayer.getTrainCardsHand();
    }

    public int getTrainCars(){
        return currentPlayer.getTrainCars();
    }

}
