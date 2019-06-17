package server.State;

public interface State {

    public void enterBootState(ServerState state);
    public void doneWithBoot(ServerState state);

}