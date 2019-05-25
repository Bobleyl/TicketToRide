package androidteam.cs340.tickettoride.Client;

import com.google.gson.JsonObject;

import androidteam.cs340.tickettoride.Shared.IServer;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class ServerProxy implements IServer {

    private ServerProxy() {}

    public static ServerProxy SINGLETON = new ServerProxy();

    @Override
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Result lobby() {
        JsonObject root = new JsonObject();
        root.addProperty("command", "lobby");
        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    @Override
    public Result game(String gameID) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "game");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("game_id", gameID);

        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

}
