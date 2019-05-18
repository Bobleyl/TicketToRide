package androidteam.cs340.tickettoride.Shared;

import java.util.List;
import java.util.ArrayList;

public class DestinationCard {
        private String cityA;
        private String cityB;
        private int points;

        DestinationCard(String cityA, String cityB, int points){
            this.cityA = cityA;
            this.cityB = cityB;
            this.points = points;
        }

        //not sure yet if we should check for route completion inside of this class...
}
