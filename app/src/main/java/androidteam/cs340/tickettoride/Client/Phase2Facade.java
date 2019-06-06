package androidteam.cs340.tickettoride.Client;

import android.graphics.ColorSpace;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Poller.ParseResults;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePoller;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePollerCommand;
import androidteam.cs340.tickettoride.Client.State.DoAnything;
import androidteam.cs340.tickettoride.Client.State.TurnState;
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
        currentPlayer = ModelFacade.SINGLETON.getPlayer();
        //currentGame = new GameModel();
        //gamePoller = new GamePoller(command, 1000, getGameID());
    }

    public static Phase2Facade SINGLETON = new Phase2Facade();

    // TODO: GET RID OF DECKS, CALL ON GAMEMODEL TO GET INFORMATION.
    private String lastPlayerPolled = "";
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

        if (gamesModel != null) {
            currentGame = gamesModel;

            if (!currentGame.getPlayerTurn().equals(lastPlayerPolled)) {
                lastPlayerPolled = currentGame.getPlayerTurn();
                TurnState.SINGLETON.setState(DoAnything.SINGLETON);
            }

            System.out.println("DATA::: ");
            System.out.println(currentGame);

            System.out.println("PLAYERS::: ");
            System.out.println(currentGame.getPlayersList());

            System.out.println("ID:::");
            System.out.println(currentGame.getGameID());

            System.out.println("PLAYER DECK: " + currentGame.getDestinationCardDeck().getDeck().size());
            for (Player player : currentGame.getPlayersList()) {
                if (player.getUID().equals(ModelFacade.SINGLETON.getPlayer().getUID())) {
                    currentPlayer = player;
                }
            }
            updatePresenter();
        }
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

    public Result claimRoute(Route route, ArrayList<TrainCard> cards){
        return ServerProxy.SINGLETON.claimRoute(currentGame.getGameID(), currentPlayer.getUID(), route, cards);
    }

    public Result drawUp(int position){
        return ServerProxy.SINGLETON.drawTrainCardFaceUp(currentGame.getGameID(), currentPlayer.getUID(), position);
    }

    public Result drawDown(){
        return ServerProxy.SINGLETON.drawTrainCardFaceDown(currentGame.getGameID(),currentPlayer.getUID());
    }

    public Result sendMessage(Message message){
        return ServerProxy.SINGLETON.sendMessage(currentGame.getGameID(),currentPlayer.getUID(),message);
    }

    public Result endTurn() {

        //Here we will check if the currentPlayer lastTurn is true..
        //If it is that means the GAME IS OVER! and go to the end game screen

        if (currentPlayer.getTrainCars() <= 2) {
            ServerProxy.SINGLETON.lastTurn(currentGame.getGameID(),currentPlayer.getUID());
        }

        return ServerProxy.SINGLETON.endTurn(currentGame.getGameID(), currentPlayer.getUID());
    }

    // END OF COMMANDS --------------- // ------------------

    public List<Message> getMessages() { return currentGame.getMessages(); }

    public TrainCard[] getUpdeck(){
        return currentGame.getTrainCardDeck().getUpDeck();
    }

    public List<TrainCard> getDownDeck(){
        return currentGame.getTrainCardDeck().getDownDeck();
    }

    public DestinationCardDeck getDestinationDeck(){
        return currentGame.getDestinationCardDeck();
    }

    public int destinationSize(){
        return currentGame.getDestinationCardDeck().getDeck().size();
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

    public List<DestinationCard> getPlayerDestinationCards(){
        return currentPlayer.getDestinationHand();
    }

    public Result updateTempDestinationHand() {
        Log.d("GAME_ACTIVITY:G_ID", currentGame.getGameID());
        Log.d("GAME_ACTIVITY:P_ID:", currentPlayer.getUID());
        return ServerProxy.SINGLETON.drawDestinationCard(currentGame.getGameID(), currentPlayer.getUID());
    }

    public List<DestinationCard> getTempDestinationCards(){
        return currentPlayer.getTempDestinationCard();
    }

    public String getPlayerTurn(){
        return currentGame.getPlayerTurn();
    }

}
