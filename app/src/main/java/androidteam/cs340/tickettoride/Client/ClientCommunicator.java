package androidteam.cs340.tickettoride.Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidteam.cs340.tickettoride.Client.ServerPoller.ParseResults;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Lobby;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private ClientCommunicator() {}

    public Result send(String command) {

        String url_ = "http://ec2-18-224-234-208.us-east-2.compute.amazonaws.com:7000/execute";

        System.out.println(command);
        System.out.println(url_);

        try {

            //Open connection
            HttpURLConnection result = null;
            try {
                URL url = new URL(url_);
                result = (HttpURLConnection)url.openConnection();
                result.setRequestMethod("POST");
                result.setDoOutput(true);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Write out to body
            byte[] outByte = command.getBytes("UTF-8");
            assert result != null;
            OutputStream os = result.getOutputStream();
            os.write(outByte);
            os.close();

            //Send connection
            result.connect();
            System.out.println(result.getResponseCode());

            if (result.getResponseCode() == HttpURLConnection.HTTP_OK) {

                //Get the response body
                InputStream in = result.getInputStream();
                String encoding = result.getContentEncoding();
                encoding = encoding == null ? "UTF-8" : encoding;
                String body = IOUtils.toString(in, encoding);
                System.out.println(body);

                return new Result(result.getResponseCode(), body, "");
            } else {
                return new Result(result.getResponseCode(), "", "Received a " + result.getResponseCode()+ " response");
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(400, "error", "Error in ClientCommunicator");
        }
    }

    /*public static void main(String[] args) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "createGame");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("number_players", 2);
        values.addProperty("player_id", "gimbo");
        root.add("values", values);
        Result result = ClientCommunicator.SINGLETON.send(root.toString());
        System.out.println(result.getData());
    }*/

    /*public static void main(String[] args) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "lobby");
        Result result = ClientCommunicator.SINGLETON.send(root.toString());
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
            System.out.println(gameID);
            System.out.println(numPlayersToStart);
            for (JsonElement jsonElement2 : jsonArray1) {
                String playerID = "";
                playerID = jsonElement2.getAsString();
                Player player = new Player(playerID);
                players.add(player);
                System.out.println(playerID);
            }
            Game game = new Game(numPlayersToStart);
            game.setPlayersList(players);
            game.setUID(gameID);
            games.add(game);
            Lobby.SINGLETON.addGame(game);
        }
    }*/

}
