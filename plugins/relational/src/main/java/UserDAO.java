import Shared.IUserDAO;
import Shared.User;

import java.util.List;

public class UserDAO implements IUserDAO {
    @Override
    public boolean checkUser(String username, String password) {
        return false;
    }

    @Override
    public boolean registerUser(String username, String password) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
