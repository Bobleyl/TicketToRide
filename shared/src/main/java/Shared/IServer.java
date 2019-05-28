package Shared;

import java.util.ArrayList;

public interface IServer {

    Result login(User user);
    Result register(User user);
    Result createGame(String playerID, int size);
    Result joinGame(String playerID, String gameID);
    Result lobby();
    Result game(String gameID);
    Result returnDestinationCard(String gameID, String playerID, ArrayList<DestinationCard> destinationCards);
    Result drawTrainCardFaceDown(String gameID, String playerID);
    Result drawTrainCardFaceUp(String gameID, String playerID, Integer position);
    Result drawDestinationCard(String gameID, String playerID);
    Result sendMessage(String gameID, String playerID, Message message);
    Result claimRoute(String gameID, String playerID, Route route);
    Result endTurn(String gameID, String playerID);
    Result deleteGame(String gameID);

}
