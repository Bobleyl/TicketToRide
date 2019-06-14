package server.State;

public class Boot implements State {
    public static final Boot SINGLETON = new Boot();

    private Boot() {}

    public void enterBootState(ServerState state){
        //Do logic you want for when in boot state and boot state is called.
    }

    public void doneWithBoot()(ServerState state){
        ServerState.SINGLETON.setState(Ok.SINGLETON);
    }
}