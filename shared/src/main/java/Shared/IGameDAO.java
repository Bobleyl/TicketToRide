package Shared;

import java.util.List;

public interface IGameDAO {

    void setModel(GameModel model);
    void addCommand(List<Command> comm);
    void clearCommands();
    GameModel getModel();
    List<Command> getCommands();
}
