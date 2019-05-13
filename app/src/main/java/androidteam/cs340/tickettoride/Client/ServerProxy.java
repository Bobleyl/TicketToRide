package androidteam.cs340.tickettoride.Client;

import androidteam.cs340.tickettoride.Shared.IServer;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class ServerProxy implements IServer {

    private ServerProxy() {}

    public static ServerProxy SINGLETON = new ServerProxy();

    @Override
    public Result login(User user) {

        //Build JSON String
        String jsonInString = "{\n" +
                "\t\"command\":\"login\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"username\": "+ "\"" + user.getUsername() + "\"" +",\n" +
                "\t\t\"password\": "+ "\"" + user.getPassword() + "\"" +"\n" +
                "\t}\n" +
                "}";

        System.out.println(jsonInString);

        return ClientCommunicator.SINGLETON.send(jsonInString);
    }

    @Override
    public Result register(User user) {

        //Build JSON String
        String jsonInString = "{\n" +
                "\t\"command\":\"register\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"username\": "+ "\"" + user.getUsername() + "\"" +",\n" +
                "\t\t\"password\": "+ "\"" + user.getPassword() + "\"" +"\n" +
                "\t}\n" +
                "}";

        return ClientCommunicator.SINGLETON.send(jsonInString);

    }

}
