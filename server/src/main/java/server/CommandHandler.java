package server;

import com.google.gson.Gson;
import io.javalin.Context;
import io.javalin.Handler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) {
        HashMap<String, Object> dto = context.bodyAsClass(HashMap.class);

        try {

            String commandType = (String)dto.get("command");
            commandType = commandType.substring(0, 1).toUpperCase() + commandType.substring(1);
            HashMap<String, Object> values = (HashMap)dto.get("values");

            Class<?> cl = Class.forName("server." + commandType + "Command");

            Constructor<?> constructor = cl.getConstructor(HashMap.class);

            CommandInterface command = (CommandInterface)constructor.newInstance(values);

            Object result = command.execute();

            if (result == null) {
                storeGame(dto, values);
                context.status(204);
            } else {
                storeGame(dto, values);
                context.status(200);
                context.json(result);
            }
        } catch (Exception e) {
            context.status(400);
            context.result(e.getMessage());
        }
    }

    public static void storeGame(HashMap<String, Object> dto, HashMap<String, Object> values) {

        if (values.get("game_id") != null) {
            Gson gson = new Gson();
            String gameID = (String)values.get("game_id");

            if (GameList.SINGLETON.deltas.containsKey(gameID)) {

                if ((GameList.SINGLETON.getDeltaCount()-1) == GameList.SINGLETON.deltas.get(gameID).size()) {
                    GameDAO.SINGLETON.storeGame(gameID);
                    GameList.SINGLETON.deltas.get(gameID).clear();
                } else {

                    List<String> deltas = GameList.SINGLETON.deltas.get(gameID);
                    deltas.add(gson.toJson(dto));
                    GameList.SINGLETON.deltas.put(gameID, deltas);
                    GameDAO.SINGLETON.storeDelta(gameID, GameList.SINGLETON.deltas.get(gameID));
                }
            } else {
                List<String> deltas = new ArrayList<>();
                deltas.add(gson.toJson(dto));
                GameList.SINGLETON.deltas.put(gameID, deltas);
                GameDAO.SINGLETON.storeDelta(gameID, GameList.SINGLETON.deltas.get(gameID));
            }

        }

    }

}




