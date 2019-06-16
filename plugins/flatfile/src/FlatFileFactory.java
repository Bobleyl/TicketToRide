import Shared.IFactory;
import Shared.IGameDAO;
import Shared.IUserDAO;

import java.io.File;

public class FlatFileFactory implements IFactory {
    public static final String FLATFILE_DIR = "flatfile";

    public FlatFileFactory() {
        File flatFileDir = new File(FLATFILE_DIR);

        if (!flatFileDir.exists()) {
            flatFileDir.mkdir();
        }
    }

    @Override
    public IUserDAO getUserDAO() {
        return new UserDAO();
    }

    @Override
    public IGameDAO getGameDAO() {
        return new GameDAO();
    }
}
