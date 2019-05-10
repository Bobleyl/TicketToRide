package androidteam.cs340.tickettoride.Shared;

import java.util.ArrayList;
import java.util.List;

public class GenericCommandRun {

    public static void main(String[] args) {

        Command register = new GenericCommand("androidteam.cs340.tickettoride.Server.ServerFacade", "register",
                new Class<?>[]{ String.class, String.class },
                new Object[] { "brent", "kleinman" });

        Command login = new GenericCommand("androidteam.cs340.tickettoride.Server.ServerFacade", "login",
                new Class<?>[]{ String.class, String.class },
                new Object[] { "brent", "kleinman" });


        List<Command> commands = new ArrayList<>();
        //commands.add(register);
        commands.add(login);

        runCommands(commands);
    }

    private static void runCommands(List<Command> commands) {
        for (Command c : commands) {
            c.execute();
        }
    }
}
