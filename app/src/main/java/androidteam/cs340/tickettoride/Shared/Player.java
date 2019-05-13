package androidteam.cs340.tickettoride.Shared;

public class Player {
    String color;
    String name;

    public Player(String name, String Color){
        this.color = color;
        this.name = name;
    }

    String getName(){ return name; }

    void setName(String name){
        this.name = name;
    }


}
