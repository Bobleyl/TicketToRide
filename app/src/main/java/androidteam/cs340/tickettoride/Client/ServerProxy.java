package androidteam.cs340.tickettoride.Client;

import androidteam.cs340.tickettoride.Shared.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerProxy implements IServer {

    private ServerProxy() {}

    public static ServerProxy SINGLETON = new ServerProxy();

    public Result login(User user) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "login");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("username", user.getUsername());
        values.addProperty("password", user.getPassword());
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result register(User user) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "register");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("username", user.getUsername());
        values.addProperty("password", user.getPassword());
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result createGame(String playerID, int size) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "createGame");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("number_players", size);
        values.addProperty("player_id", playerID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result joinGame(String playerID, String gameID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "joinGame");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result deleteGame(String gameID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "deleteGame");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result lobby() {
        JsonObject root = new JsonObject();
        root.addProperty("command", "lobby");
        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result game(String gameID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "game");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result returnDestinationCard(String gameID, String playerID, ArrayList<DestinationCard> destinationCards) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "returnDestinationCard");

        Gson gson = new Gson();

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        values.addProperty("destination_card", gson.toJson(destinationCards));
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }


    public Result drawTrainCardFaceDown(String gameID, String playerID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "drawFaceDown");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result drawTrainCardFaceUp(String gameID, String playerID, Integer position) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "drawFaceUp");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        values.addProperty("position", position);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result drawDestinationCard(String gameID, String playerID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "drawDestinationCard");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result sendMessage(String gameID, String playerID, Message message) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "sendMessage");

        Gson gson = new Gson();

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("message", gson.toJson(message));
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result claimRoute(String gameID, String playerID, Route route) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "claimRoute");

        Gson gson = new Gson();

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        values.addProperty("route", gson.toJson(route));
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result endTurn(String gameID, String playerID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "endTurn");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);
        values.addProperty("player_id", playerID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

}
