package androidteam.cs340.tickettoride.Shared;

import androidteam.cs340.tickettoride.Shared.Colors;

public class Routes {
    private String cityA;
    private String cityB;
    private Colors color;
    private int length;
    private int points;
    private Player owner;
    private boolean isSecondaryRoute;

    Routes(String cityA, String cityB, Colors color, int lenth, int points, boolean routeType){
        this.cityA = cityA;
        this.cityB = cityB;
        this.color = color;
        this.length = length;
        this.points = points;
        this.owner = null;
        this.isSecondaryRoute = routeType;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}
