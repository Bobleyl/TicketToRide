package androidteam.cs340.tickettoride.Shared;

public interface IServer {

    Result login(User user);
    Result register(User user);

}
