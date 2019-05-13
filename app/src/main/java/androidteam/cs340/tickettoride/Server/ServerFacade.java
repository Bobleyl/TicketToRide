package androidteam.cs340.tickettoride.Server;

import androidteam.cs340.tickettoride.Shared.Result;

public class ServerFacade {

    public static ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade() {
        System.out.println("Initializing Server Facade");
    }

    public static String login(String username, String password) {
        Result result = SINGLETON._login(username, password);
        return result.getData();
    }

    public static String register(String username, String password) {
        Result result = SINGLETON._register(username, password);
        return result.getData();
    }

    private Result _login(String username, String password) {
        boolean success = DataAccess.SINGLETON.checkUser(username, password);

        if(success) {
            return new Result(true, "User logged in", "success");
        } else {
            return new Result(false, "User not logged in", "fail");
        }
    }

    private Result _register(String username, String password) {

        boolean success = DataAccess.SINGLETON.registerUser(username, password);

        if(success) {
            return new Result(true, "User registered", "success");
        } else {
            return new Result(false, "User not registered", "fail");
        }
    }

}
