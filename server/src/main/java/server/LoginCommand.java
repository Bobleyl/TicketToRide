package server;

import Shared.CommandInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginCommand implements CommandInterface {
    private String username;
    private String password;

    public LoginCommand(Map<String, Object> values) {
        this.username = (String)values.get("username");
        this.password = (String)values.get("password");
    }

    @Override
    public Object execute() throws Exception {
        boolean success = UserDAO.SINGLETON.checkUser(username, password);
        HashMap<String, String> result= new HashMap<>();

        result.put("PlayerID", UUID.randomUUID().toString());

        if(success) {
            System.out.println("User logged in!");
        } else {
            System.out.println("User does not exist");
            throw new Exception("Invalid login");
        }

        return result;
    }
}
