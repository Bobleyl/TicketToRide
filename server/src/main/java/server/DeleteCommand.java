package server;

import java.util.HashMap;

public class DeleteCommand implements CommandInterface {

    public DeleteCommand(HashMap<String, Object> values) {}

    @Override
    public Object execute() throws Exception {

        boolean success = UserDAO.SINGLETON.delete();

        if(success) {
            LobbyModel.SINGLETON.emptyGames();
            System.out.println("Deleted data");
        } else {
            System.out.println("Did not delete data");
            throw new Exception("Delete failed");
        }

        return null;
    }


}