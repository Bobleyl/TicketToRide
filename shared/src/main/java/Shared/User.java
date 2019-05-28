package Shared;

public class User {
    String UID;
    private String username;
    private String password;

    public User(String username, String password){
        this.UID = null;
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getUID(){
        return UID;
    }

}
