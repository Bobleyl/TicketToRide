package androidteam.cs340.tickettoride.Shared;

import java.util.ArrayList;

public interface IServer {

    Result login(User user);
    Result register(User user);
    Result createGame(String playerID, int size);
    Result joinGame(String playerID, String gameID);
    Result lobby();
    /*Result startGame();
    Result returnDestinationCard(ArrayList<DestinationCard> destinationCards);
    Result drawTrainCardFaceDown();
    Result drawTrainCardFaceUp();
    Result sendMessage();
    Result claimRoute(Routes routes);*/

}
