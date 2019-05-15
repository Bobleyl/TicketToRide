package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class Game {
    String UID;
    private List<Player> playersList;
    int gameSize;

    public Game(int Size){
        this.playersList = new ArrayList<>();
        this.UID = "";
        this.gameSize = Size;
    }

    public void addPlayer(Player player){ playersList.add(player); }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public int getGameSize() {
        return gameSize;
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }
}
