package server.State

public class ServerState {

    private State state;

    private ServerState() {
        state = Ok.SINGLETON;
    }

    public void enterBootState(){
        state.enterBootState(this);
    }

    public void doneWithBoot(){
        state.doneWithBoot(this);
    }

    public boolean isBootState(){
        return state instanceof Boot;
    }

    public boolean isOkState(){
        return state instanceof Ok;
    }

    public void setState(State state){
        this.state = state;
    }
}