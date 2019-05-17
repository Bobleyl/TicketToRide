package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Lobby {
    String UID;
    private List<Game> gamesList;

    public Lobby(){
        this.gamesList = new ArrayList<>();
        this.UID = null;
    }

    public String getUID(){
        return UID;
    }

    public void setUID(String uid){ this.UID = uid; }

    public List<Game> getGames(){
        return gamesList;
    }

    public void addGame(Game game){ gamesList.add(game); }

    public void updateCurrentGames(List<Game> currentGames){
        this.gamesList = currentGames;
    }

}
