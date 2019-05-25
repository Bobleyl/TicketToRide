package androidteam.cs340.tickettoride.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.GameModel;
import androidteam.cs340.tickettoride.Shared.LobbyGameModel;
import androidteam.cs340.tickettoride.Shared.LobbyModel;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private ClientCommunicator() {}

    public Result send(String command) {

        String url_ = "http://ec2-18-224-234-208.us-east-2.compute.amazonaws.com:7000/execute";
        //String url_ = "http://localhost:7001/execute";

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

    //Example of gson to class
    /*public static void main(String[] args) {

        Game game = new Game(3);
        Player player1 = new Player("test1");
        Player player2 = new Player("test2");
        Player player3 = new Player("test3");
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        Game game1 = new Game(2);
        try {
            Gson gson = new Gson();
            String gameString = gson.toJson(game);

            Game game2 = gson.fromJson(gameString, Game.class);

            for (Player player : game2.getPlayersList()) {
                System.out.println(player.getUID());
            }
            System.out.println(gameString);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }*/

    //Grabbing games from the LobbyGameModel
    /*public static void main(String[] args) {
        //Send command
        JsonObject root = new JsonObject();
        root.addProperty("command", "lobby");
        Result result = ClientCommunicator.SINGLETON.send(root.toString());

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
    }*/

    //Add players to a game until it is full, so the game will start
    //Grab the game model and store it on the client to test functionality
    public static void main(String[] args) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "delete");
        Result deleteResult = ClientCommunicator.SINGLETON.send(root.toString());
        System.out.println("Status code of delete: " + deleteResult.getStatusCode());

        String gameID = "";
        String player1 = "test1";
        String player2 = "test2";
        String player3 = "test3";
        String player4 = "test4";
        Result resultCreateGame = ServerProxy.SINGLETON.createGame(player1, 4);
        System.out.println("Result body of createGame: " + resultCreateGame.getData());

        gameID = resultCreateGame.getData();
        gameID = gameID.replace("\"", "");

        Result joinGamePlayer2 = ServerProxy.SINGLETON.joinGame(player2, gameID);
        System.out.println("Result body of joinGamePlayer2: " + joinGamePlayer2.getStatusCode());

        Result joinGamePlayer3 = ServerProxy.SINGLETON.joinGame(player3, gameID);
        System.out.println("Result body of joinGamePlayer3: " + joinGamePlayer3.getStatusCode());

        Result joinGamePlayer4 = ServerProxy.SINGLETON.joinGame(player4, gameID);
        System.out.println("Result body of joinGamePlayer4: " + joinGamePlayer4.getStatusCode());

        Result lobby = ServerProxy.SINGLETON.lobby();
        System.out.println("Lobby: " + lobby.getData());

        Result game = ServerProxy.SINGLETON.game(gameID);
        System.out.println("Game: " + game.getData());

        Gson gson = new Gson();
        GameModel gameModel = gson.fromJson(game.getData(), GameModel.class);

        System.out.println(gameModel.getGameSize());


    }

}
