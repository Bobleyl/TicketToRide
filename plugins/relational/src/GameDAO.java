import Shared.Command;
import Shared.GameModel;
import Shared.IGameDAO;

import java.util.List;

public class GameDAO implements IGameDAO {
    @Override
    public void setModel(GameModel model) {

    }

    @Override
    public void addCommand(List<Command> comm) {

    }

    @Override
    public void clearCommands() {

    }

    @Override
    public GameModel getModel() {
        return null;
    }

    @Override
    public List<Command> getCommands() {
        return null;
    }
}
