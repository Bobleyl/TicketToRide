package androidteam.cs340.tickettoride.Client.ServerPoller;

import androidteam.cs340.tickettoride.Shared.Result;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ServerPoller {

    private int frequency;
    private IPollerCommand command;
    private Timer timer;

    ServerPoller(IPollerCommand command, int frequency) {

        this.command = command;
        this.frequency = frequency;
        timer = new Timer();
    }

    private void poll() {
        // call server
        Result result = getServerData();
        // call command object with data
        command.execute(result);
    }

    public void stop() {
        timer.cancel();
    }

    public synchronized void start() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                poll();
            }
        };

        timer.schedule(timerTask, 0, frequency);
    }

    abstract Result getServerData();
}
