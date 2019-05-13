package androidteam.cs340.tickettoride.Shared;

public enum Colors {
    green("green"), yellow("yellow"), red("red"), blue("blue"), black("black");

    private String color;
    private Colors(String color){
        this.color = color;
    }

}
