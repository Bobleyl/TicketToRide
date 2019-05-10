package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class Lobby {
    String UID;
    private List<Game> gamesList;

    public Lobby(){
        this.gamesList = new ArrayList<>();
        this.UID = "";
    }
}
