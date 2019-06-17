package server;


import Shared.CommandInterface;
import Shared.GameList;
import Shared.GameModel;
import Shared.Message;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class SendMessageCommand implements CommandInterface {

    String gameID;
    String playerID;
    Message message;

    public SendMessageCommand(Map<String, Object> values) {

        Gson gson = new Gson();

        this.gameID = (String)values.get("game_id");
        this.message = gson.fromJson((String) values.get("message"), Message.class);
    }

    @Override
    public Object execute() throws Exception {

        boolean successfulExecute = false;
        ReturnMessage returnMessage = new ReturnMessage();

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                System.out.println("Found game for sending message");
                List<Message> messages = game.getMessages();
                messages.add(message);
                game.setMessages(messages);
                successfulExecute = true;
                System.out.println("Set to true");
                returnMessage.setReponseMessage("success");
            }
        }

        System.out.println(successfulExecute);

        if (successfulExecute) {
            return returnMessage;
        } else {
            throw new Exception("Message failed to come through");
        }
    }
}
