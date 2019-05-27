package androidteam.cs340.tickettoride.Shared;

public enum Colors {
    green("green"), yellow("yellow"), red("red"), blue("blue"), black("black"), wild("wild"),pink("pink"),orange("orange"),white("white"), grey("grey");

    private String color;
    private Colors(String color){
        this.color = color;
    }
    String showColor() { return  color; }

}
