package androidteam.cs340.tickettoride.Client.Poller;

import androidteam.cs340.tickettoride.Client.Poller.Game.GamePoller;
import androidteam.cs340.tickettoride.Client.Poller.Game.GamePollerCommand;

public class Test {

    public static void main(String[] args) {

        GamePollerCommand command = new GamePollerCommand();
        GamePoller gp = new GamePoller(command, 1000, "d9665f74-e73a-4e14-a0cb-36da981ff3d9");
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

