import Shared.GameModel;
import Shared.IGameDAO;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import server.CommandInterface;
import server.GameList;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameDAO implements IGameDAO {
    private static final String FLATFILE_DIR = FlatFileFactory.FLATFILE_DIR;
    private static final String GAME_FILENAME = FLATFILE_DIR + "/game-{0}.txt";
    private static final String DELTAS_FILENAME = FLATFILE_DIR + "/deltas-{0}.txt";

    @Override
    public void deleteGame(String gameID) {
        new File(MessageFormat.format(GAME_FILENAME, gameID)).delete();
        new File(MessageFormat.format(DELTAS_FILENAME, gameID)).delete();
    }

    @Override
    public void deleteGames() {

        File flatFileDir = new File(FLATFILE_DIR);

        if (!flatFileDir.exists()) {
            return;
        }

        File[] files = flatFileDir.listFiles();

        for (File file : files) {

            if (file.getName().startsWith("game-") || file.getName().startsWith("deltas-")) {

                file.delete();
            }
        }
    }

    @Override
    public void storeDelta(String gameID, List<String> delta) {

        Gson gson = new Gson();
        String deltas = gson.toJson(delta);

        try {
            PrintWriter pw = new PrintWriter(MessageFormat.format(DELTAS_FILENAME, gameID));
            pw.print(deltas);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @Override
    public void storeGame(String gameID) {

        GameModel gameModel = null;

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                gameModel = game;
            }
        }

        if (gameModel != null) {

            Gson gson = new Gson();
            String gameModelString = gson.toJson(gameModel);

            try {
                PrintWriter pw = new PrintWriter(MessageFormat.format(GAME_FILENAME, gameModel.getGameID()));
                pw.print(gameModelString);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            };

            File deltasFile = new File(MessageFormat.format(DELTAS_FILENAME, gameModel.getGameID()));
            if (deltasFile.exists()) {
                deltasFile.delete();
            }
        }
    }

    @Override
    public void retrieveGames() {

        Gson gson = new Gson();

        File flatFileDir = new File(FLATFILE_DIR);

        if (!flatFileDir.exists()) {
            return;
        }

        File[] files = flatFileDir.listFiles();

        for (File file : files) {

            if (file.getName().startsWith("game-")) {

                try {
                    byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
                    String contents = new String(encoded, StandardCharsets.UTF_8);

                    GameModel gameModel = gson.fromJson(contents, GameModel.class);
                    GameList.SINGLETON.addGame(gameModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (File file : files) {

            if (file.getName().startsWith("deltas-")) {

                try {
                    byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
                    String contents = new String(encoded, StandardCharsets.UTF_8);

                    List<String> deltas = gson.fromJson(contents, List.class);

                    for (String s : deltas) {
                        Map<String, Object> dto = gson.fromJson(s, HashMap.class);
                        String commandType = (String)dto.get("command");
                        commandType = commandType.substring(0, 1).toUpperCase() + commandType.substring(1);
                        Map<String, Object> values = (Map)dto.get("values");

                        Class<?> cl = Class.forName("server." + commandType + "Command");

                        Constructor<?> constructor = cl.getConstructor(Map.class);

                        CommandInterface command = (CommandInterface)constructor.newInstance(values);

                        command.execute();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
