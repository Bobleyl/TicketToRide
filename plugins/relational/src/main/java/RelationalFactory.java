import Shared.IFactory;
import Shared.IGameDAO;
import Shared.IUserDAO;

public class RelationalFactory implements IFactory {
    @Override
    public IUserDAO getUserDAO() {
        return RelationalUserDAO.SINGLETON;
    }

    @Override
    public IGameDAO getGameDAO() {
        return RelationalGameDAO.SINGLETON;
    }
}
