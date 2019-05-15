package androidteam.cs340.tickettoride.Client.ServerPoller;

import java.util.ArrayList;

import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Result;

public class ParseResults {

    public String parseSingleString(Result result) {
        result.getData();
        return "";
    }

    public ArrayList<Game> parseLobbyResult(Result result) {
        ArrayList<Game> games = new ArrayList<Game>();
        return games;
    }

}
