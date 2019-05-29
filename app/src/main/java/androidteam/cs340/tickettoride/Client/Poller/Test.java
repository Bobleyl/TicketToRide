package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Client.Poller.Game.GamePoller;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePollerCommand;

public class Test {

    public static void main(String[] args) {

        GamePollerCommand command = new GamePollerCommand();
        GamePoller gp = new GamePoller(command, 1000, "f51ca60f-9256-4729-a53b-5165c8f47f33");
        gp.start();

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

