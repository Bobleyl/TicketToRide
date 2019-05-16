package androidteam.cs340.tickettoride.Client.ServerPoller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;

public class ParseResults {

    private ParseResults() {}

    public static ParseResults SINGLETON = new ParseResults();

    public String parseSingleString(Result result) {
        assert result != null;
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

        assert result != null;
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<Player> players = new ArrayList<Player>();

        JsonElement jsonElement = new JsonParser().parse(result.getData());
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for(JsonElement jsonElement1 : jsonArray) {
            int numPlayersToStart = 0;
            String gameID = "";
            JsonObject userObj = jsonElement1.getAsJsonObject();
            numPlayersToStart = userObj.get("numPlayersToStart").getAsInt();
            gameID = userObj.get("gameID").getAsString();
            JsonArray jsonArray1 = userObj.getAsJsonArray("playerIDs");
            for (JsonElement jsonElement2 : jsonArray1) {
                String playerID = "";
                playerID = jsonElement2.getAsString();
                Player player = new Player(playerID);
                players.add(player);
            }
            Game game = new Game(numPlayersToStart);
            game.setPlayersList(players);
            game.setUID(gameID);
            games.add(game);
        }
        return games;
    }

}
