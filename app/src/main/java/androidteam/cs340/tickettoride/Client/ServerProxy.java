package androidteam.cs340.tickettoride.Client;

import androidteam.cs340.tickettoride.Shared.IServer;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class ServerProxy implements IServer {

    @Override
    public Object login(User user) {

        //Build JSON String
        String jsonInString = "{\n" +
                "\t\"command\":\"login\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"username\": "+ "" +",\n" +
                "\t\t\"password\": "+ "" +"\n" +
                "\t}\n" +
                "}";

        Result result = ClientCommunicator.SINGLETON.send(jsonInString);

        return result;
    }

    @Override
    public Object register(User user) {
        //Build JSON String
        String jsonInString = "{\n" +
                "\t\"command\":\"login\",\n" +
                "\t\"values\": {\n" +
                "\t\t\"username\": "+ "" +",\n" +
                "\t\t\"password\": "+ "" +"\n" +
                "\t}\n" +
                "}";

        Result result = ClientCommunicator.SINGLETON.send(jsonInString);

        return result;

    }

}
