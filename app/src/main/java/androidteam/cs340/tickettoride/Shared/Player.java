package androidteam.cs340.tickettoride.Shared;

public class Player {
    String gameUID;
    String UID;
    String color;
    String name;

    public Player(String name, String Color, String GameUID, String PlayerUID){
        this.gameUID = GameUID;
        this.color = color;
        this.name = name;
        this.UID = PlayerUID;
    }

    String getUID(){ return UID; }

    String getGameUID(){
        return gameUID;
    }

    String getName(){ return name; }

    void setName(String name){
        this.name = name;
    }


}
