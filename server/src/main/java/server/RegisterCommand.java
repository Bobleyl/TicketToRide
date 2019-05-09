package server;

import java.util.HashMap;

public class RegisterCommand implements CommandInterface {
    private String username;
    private String password;

    public RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Object execute() {
        HashMap<String, String> result = new HashMap<>();
        result.put("message", "success");
        result.put("username", username);
        return result;
    }
}
