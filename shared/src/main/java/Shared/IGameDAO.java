package Shared;

import java.util.List;

public interface IGameDAO {

    public void deleteGame(String gameID);
    public void deleteGames();
    public void storeDelta(String gameID, List<String> delta);
    public void storeGame(String gameID);
    public void retrieveGames();
}
