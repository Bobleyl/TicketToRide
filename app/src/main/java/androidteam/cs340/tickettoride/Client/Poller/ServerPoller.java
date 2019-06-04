package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Shared.Result;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ServerPoller {

    private int frequency;
    private IPollerCommand command;
    private Timer timer;

    public ServerPoller(IPollerCommand command, int frequency) {

        this.command = command;
        this.frequency = frequency;
    }

    private void poll() {
        // call server
        Result result = getServerData();
        // call command object with data
        command.execute(result);
    }

    public synchronized void stop() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public synchronized void start() {

        if (timer == null) {
            timer = new Timer();
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                poll();
            }
        };

        timer.schedule(timerTask, 0, frequency);
    }

    public abstract Result getServerData();
}
