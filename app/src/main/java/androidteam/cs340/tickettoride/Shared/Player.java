package androidteam.cs340.tickettoride.Shared;


public class Player {
    String color;
    String name;
    String UID;

    public Player(String name, String Color, String UID){
        this.color = color;
        this.name = name;
        this.UID = UID;
    }

    public String getName(){ return name; }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setName(String name){
        this.name = name;
    }

}
