package Shared;

public class Message {
    String playerID;
    String textMessage;

    public Message(String playerID, String textMessage) {
        this.playerID = playerID;
        this.textMessage = textMessage;
    }

    public void setPlayerID(String playerID) {this.playerID = playerID;}

    public void setTextMessage(String textMessage) {this.textMessage = textMessage;}

    public String getPlayerID() { return this.playerID; }

    public String getTextMessage() { return this.textMessage; }
}

