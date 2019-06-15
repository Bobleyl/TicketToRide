package Shared;

import java.util.List;

public interface IUserDAO {

    public boolean checkUser(String username, String password);
    public boolean registerUser(String username, String password);
    public boolean delete();
}
