package server;

import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class CommandHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, Object> dto = context.bodyAsClass(HashMap.class);

        try {

            String commandType = (String)dto.get("command");
            commandType = commandType.substring(0, 1).toUpperCase() + commandType.substring(1).toLowerCase();
            HashMap<String, Object> values = (HashMap)dto.get("values");

            Class<?> cl = Class.forName(commandType + "Command");

            Constructor<?> constructor = cl.getConstructor(HashMap.class);

            CommandInterface command = (CommandInterface)constructor.newInstance(values);

            Object result = command.execute();

            if (result == null) {
                context.status(204);
            } else {
                context.json(result);
            }
        } catch (Exception e) {
            context.status(400);
            context.result(e.getMessage());
        }
    }

}

