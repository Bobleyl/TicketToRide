package server;

import Shared.IFactory;
import io.javalin.Javalin;

public class ServerCommunicator {
    public static IFactory factory;

    public static void main(String[] args) {
        factory = AbstractFactory.createFactory(args[0]);
        GameList.SINGLETON.setDeltaCount(Integer.parseInt(args[1]));
        factory.getGameDAO().retrieveGames();

        Javalin app = Javalin.create().start(7000);
        app.post("/execute", new CommandHandler());
    }
}