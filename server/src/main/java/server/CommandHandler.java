package server;

import com.google.gson.Gson;
import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        Map<String, Object> dto = context.bodyAsClass(Map.class);

        try {

            String commandType = (String)dto.get("command");
            commandType = commandType.substring(0, 1).toUpperCase() + commandType.substring(1);
            Map<String, Object> values = (Map)dto.get("values");

            Class<?> cl = Class.forName("server." + commandType + "Command");

            Constructor<?> constructor = cl.getConstructor(Map.class);

            CommandInterface command = (CommandInterface)constructor.newInstance(values);

            Object result = command.execute();

            storeGame(dto, values, command);

            if (result == null) {
                context.status(204);
            } else {
                context.status(200);
                context.json(result);
            }
        } catch (Exception e) {
            context.status(400);
            context.result(e.getMessage());
        }
    }

    public static void storeGame(Map<String, Object> dto, Map<String, Object> values, CommandInterface command) {

        if (values.get("game_id") != null && !(command instanceof GameCommand || command instanceof JoinGameCommand)) {
            Gson gson = new Gson();
            String gameID = (String)values.get("game_id");

            if (!GameList.SINGLETON.deltas.containsKey(gameID)) {
                GameList.SINGLETON.deltas.put(gameID, new ArrayList<>());
            }

            if ((GameList.SINGLETON.getDeltaCount()-1) == GameList.SINGLETON.deltas.get(gameID).size()) {
                ServerCommunicator.factory.getGameDAO().storeGame(gameID);
                GameList.SINGLETON.deltas.get(gameID).clear();
            } else {

                List<String> deltas = GameList.SINGLETON.deltas.get(gameID);
                deltas.add(gson.toJson(dto));
                GameList.SINGLETON.deltas.put(gameID, deltas);
                ServerCommunicator.factory.getGameDAO().storeDelta(gameID, GameList.SINGLETON.deltas.get(gameID));
            }

        }

    }

}




