package androidteam.cs340.tickettoride.Shared;

public class User {
    String UID;
    private String username;
    private String password;

    public User(String username, String password){
        this.UID = null;
        this.username = username;
        this.password = password;
    }

    String getUsername() { return username; }

    String getPassword() { return password; }

    String getUID(){
        return UID;
    }

}
