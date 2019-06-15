package server;

import Shared.IFactory;
import Shared.IUserDAO;
import io.javalin.Javalin;

public class ServerCommunicator {
    public static IFactory factory;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.post("/execute", new CommandHandler());

        factory = AbstractFactory.createFactory(args[0]);
    }
}