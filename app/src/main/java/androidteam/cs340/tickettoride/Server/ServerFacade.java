package androidteam.cs340.tickettoride.Server;

import androidteam.cs340.tickettoride.Shared.IServer;
import androidteam.cs340.tickettoride.Shared.Result;

public class ServerFacade {

    public static ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade() {
        System.out.println("Initializing Server Facade");
    }

    public static String login(String username, String password) {

        String result = "";
        boolean success = DataAccess.SINGLETON.checkUser(username, password);

        if(success) {
            result = "User logged in";
        } else {
            result =  "User not logged in";
        }
        return result;
    }

    public static String register(String username, String password) {

        String result = "";
        boolean success = DataAccess.SINGLETON.registerUser(username, password);

        if(success) {
            result = "User registered";
        } else {
            result = "User not registered";
        }
        return result;
    }

}
