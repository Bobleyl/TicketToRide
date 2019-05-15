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


    /*

    public Result pollLobby(String lastCommand) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "pollLobby");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("lastCommand", lastCommand);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }

    public Result pollCommand(String lastCommand, String gameID) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "pollCommand");

        // Create Inner JSON Object
        JsonObject values = new JsonObject();
        values.addProperty("lastCommand", lastCommand);
        values.addProperty("gameID", gameID);
        root.add("values", values);

        return ClientCommunicator.SINGLETON.send(root.toString());
    }
    */

}
