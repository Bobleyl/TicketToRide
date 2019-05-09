package server;

import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, Object> dto = context.bodyAsClass(HashMap.class);

        try {
            CommandInterface command = null;

            String commandType = (String)dto.get("command");
            HashMap<String, String> values = (HashMap)dto.get("values");

            switch (commandType) {
                case "register":
                    if (values.get("username") == null || values.get("password") == null) {
                        throw new Exception("Invalid request");
                    }
                    command = new RegisterCommand(values.get("username"), values.get("password"));
                    break;
            }

            context.json(execute(command));
        } catch (Exception e) {
            context.status(400);
            context.result(e.getMessage());
        }
    }

    private Object execute(CommandInterface command) {
        return command.execute();
    }
}
