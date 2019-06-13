package Shared;

public interface IFactory {

    IUserDAO getUserDAO();
    IGameDAO getGameDAO();
}
