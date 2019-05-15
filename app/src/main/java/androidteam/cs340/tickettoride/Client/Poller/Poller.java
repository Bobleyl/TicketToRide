package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Shared.Result;

public abstract class Poller {

    private int frequency;
    private IPollerCommand command;
    private boolean stop = false;
    private boolean started = false;

    Poller(IPollerCommand command, int frequency) {

        this.command = command;
        this.frequency = frequency;
    }

    private void poll() {
        while (!stop) {
            // call server
            Result result = getServerData();
            // call command object with data
            command.execute(result);
            // sleep for frequency
            try {
                Thread.sleep(frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stop = true;
        started = false;
    }

    public synchronized void start() {

        if (started) {
            return; // only allow one thread at a time
        }

        stop = false;
        started = true;

        Thread pollerThread = new Thread(new Runnable() {
            public void run() {
                poll();
            }
        });

        pollerThread.start();
    }

    abstract Result getServerData();
}
