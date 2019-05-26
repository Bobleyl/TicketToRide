package androidteam.cs340.tickettoride.Shared;

public class Message {
    private String playerID;
    private String playerName;
    private String textMessage;

    public Message(String playerID, String player, String textMessage) {
        this.playerID = playerID;
        this.playerName = player;
        this.textMessage = textMessage;
    }
}
