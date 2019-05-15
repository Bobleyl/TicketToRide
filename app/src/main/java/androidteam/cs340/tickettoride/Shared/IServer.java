package androidteam.cs340.tickettoride.Shared;

public interface IServer {

    Result login(User user);
    Result register(User user);
    Result createGame(String playerID, int size);
    Result joinGame(String playerID, String gameID);
    Result lobby();

}
