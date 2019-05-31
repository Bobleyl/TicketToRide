package androidteam.cs340.tickettoride.Client.Poller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.GameModel;
import androidteam.cs340.tickettoride.Shared.LobbyGameModel;
import androidteam.cs340.tickettoride.Shared.LobbyModel;
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

        //Check result
        System.out.println(result.getData());

        Gson gson = new Gson();

        //Allow arrayList
        TypeToken<List<LobbyGameModel>> token = new TypeToken<List<LobbyGameModel>>() {};

        //Load values into arrayList
        ArrayList<LobbyGameModel> gameModels = gson.fromJson(result.getData(), token.getType());
        LobbyModel.SINGLETON.setGames(gameModels);

        //Insert info into game
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<Player> players = new ArrayList<Player>();

        for(LobbyGameModel gameModel : gameModels) {

            players = new ArrayList<Player>();

            for(String playerID : gameModel.getPlayerIDs()) {

                Player player = new Player(playerID);
                players.add(player);

            }

            Game game = new Game(gameModel.getNumPlayersToStart());
            game.setPlayersList(players);
            game.setUID(gameModel.getGameID());
            games.add(game);

        }
        return games;
    }

    public GameModel parseGameResult(Result result) {

        Gson gson = new Gson();

        return gson.fromJson(result.getData(), GameModel.class);
    }
}
