package androidteam.cs340.tickettoride.Shared;

public class Player {
    String gameUID;
    String UID;
    String color;
    String name;

    public Player(){
        this.gameUID = null;
        this.color = "";
        this.name = "";
        this.UID = null;
    }

    String getUID(){
        return UID;
    }

    String getGameUID(){
        return gameUID;
    }

    String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }


}
