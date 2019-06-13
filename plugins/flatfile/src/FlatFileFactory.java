import Shared.IFactory;
import Shared.IGameDAO;
import Shared.IUserDAO;

public class FlatFileFactory implements IFactory {
    @Override
    public IUserDAO getUserDAO() {
        return new UserDAO();
    }

    @Override
    public IGameDAO getGameDAO() {
        return new GameDAO();
    }
}
