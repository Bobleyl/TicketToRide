package server;

import java.util.HashMap;

public class LoginCommand implements CommandInterface {
    private String username;
    private String password;

    public LoginCommand(HashMap<String, Object> values) {
        this.username = (String)values.get("username");
        this.password = (String)values.get("password");
    }

    @Override
    public Object execute() throws Exception {
        boolean success = DataFacade.SINGLETON.checkUser(username, password);

        if(success) {
            System.out.println("User logged in!");
        } else {
            System.out.println("User does not exist");
            throw new Exception("Invalid login");
        }

        return null;
    }
}
