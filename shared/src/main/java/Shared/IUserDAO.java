package Shared;

import java.util.List;

public interface IUserDAO {

    List<User> getUsers();
    void addUser(User user);
}
