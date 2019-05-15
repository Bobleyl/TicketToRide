package androidteam.cs340.tickettoride.Client;

import java.util.Timer;
import java.util.TimerTask;

public class Poller {
    private String lastCommand;
    private boolean commandRunning;
    private Timer timer;
    private String gameID;

    public Poller(){
        lastCommand = null;
        commandRunning = false;
        timer = new Timer();
        gameID = null;
    }

    public void setPollerSwitch(String gameID){
        this.gameID = gameID;
        commandRunning = true;
    }

    public void startPolling(){
        timer.schedule(lobbyCommands, 0, 1000);
    }

    public void stopPolling(){
        timer.cancel();
    }

    private TimerTask lobbyCommands = new TimerTask(){
        public void run() {
            if(commandRunning != true) {
                //run lobbyPolling here
                //We need some kind of object to store the received commands here
                //Result result = ServerProxy.SINGLETON.pollLobby(lastCommand);
            }else{
                //run commandPolling here
                //We need some kind of object to store the received commands here
                //Result result = ServerProxy.SINGLETON.pollCommands(lastCommand, gameID);
            }
        }
    };



}
