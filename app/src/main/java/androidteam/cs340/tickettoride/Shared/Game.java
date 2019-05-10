package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class Game {
    String UID;
    private List<Player> playersList;
    int gameSize;

    public Game(){
        this.playersList = new ArrayList<>();
        this.UID = "";
        this.gameSize = 5;
    }


}
