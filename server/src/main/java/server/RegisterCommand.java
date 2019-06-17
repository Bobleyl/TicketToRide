package server;

import Shared.CommandInterface;

import java.util.Map;

public class RegisterCommand implements CommandInterface {
    private String username;
    private String password;

    public RegisterCommand(Map<String, Object> values) {
        this.username = (String)values.get("username");
        this.password = (String)values.get("password");
    }

    @Override
    public Object execute() throws Exception {
        boolean success = ServerCommunicator.factory.getUserDAO().registerUser(username, password);

        if(success) {
            System.out.println("User was created");
        } else {
            System.out.println("User failed to create");
            throw new Exception("User failed to create");
        }

        return null;
    }
}
