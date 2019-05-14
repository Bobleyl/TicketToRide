package androidteam.cs340.tickettoride.Client;

import java.util.Timer;
import java.util.TimerTask;

public class Poller {
    private String lastCommand;
    private boolean commandRunning;
    private Timer timer;

    public Poller(){
        lastCommand = null;
        commandRunning = false;
        timer = new Timer();
    }

    public void setPollerSwitch(){
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
            }else{
                //run commandPolling here
            }
        }
    };



}
