package androidteam.cs340.tickettoride.Client.ServerPoller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Result;

public class ParseResults {

    private ParseResults() {}

    public static ParseResults SINGLETON = new ParseResults();

    public String parseSingleString(Result result) {
        JSONObject root = null;
        try {
            root = new JSONObject(result.getData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (root != null) {
            try {
                return root.getString("PlayerID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public ArrayList<Game> parseLobbyResult(Result result) {
        ArrayList<Game> games = new ArrayList<Game>();
        return games;
    }

}
