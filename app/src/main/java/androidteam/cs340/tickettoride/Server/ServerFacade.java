package androidteam.cs340.tickettoride.Server;

public class ServerFacade {

    public static ServerFacade SINGLETON = new ServerFacade();

    private ServerFacade() {
        System.out.println("Initializing Server Facade");
    }

    public static void login(String username, String password) {
        SINGLETON._login(username, password);
    }

    public static void register(String username, String password) {
        SINGLETON._register(username, password);
    }

    private void _login(String username, String password) {
        boolean success = DataAccess.SINGLETON.checkUser(username, password);

        if(success) {
            System.out.println("User logged in!");
        } else {
            System.out.println("User does not exist");
        }
    }

    private void _register(String username, String password) {

        boolean success = DataAccess.SINGLETON.registerUser(username, password);

        if(success) {
            System.out.println("User was created");
        } else {
            System.out.println("User failed to create");
        }
    }

}
