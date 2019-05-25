package androidteam.cs340.tickettoride.Shared;

public class Message {
    String playerID;
    String textMessage;

    public Message(String playerID, String textMessage) {
        this.playerID = playerID;
        this.textMessage = textMessage;
    }
}
