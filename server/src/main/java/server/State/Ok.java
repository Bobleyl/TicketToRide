package server.State;

public class Ok implements State {
    public static final Ok SINGLETON = new Ok();

    private Ok() {}

    public void enterBootState(ServerState state){
        ServerState.SINGLETON.setState(Boot.SINGLETON);
    }

    public void doneWithBoot()(ServerState state){
        // Should not call this on Ok state ever
    }
}