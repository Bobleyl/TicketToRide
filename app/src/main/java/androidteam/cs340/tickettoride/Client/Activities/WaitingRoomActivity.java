package androidteam.cs340.tickettoride.Client.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Presenters.WaitingRoomPresenter;
import androidteam.cs340.tickettoride.R;

public class WaitingRoomActivity extends AppCompatActivity {
    private WaitingRoomPresenter presenter;


    @Override
    protected void onResume(){
        super.onResume();
        ModelFacade.SINGLETON.addPresenter(presenter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        ModelFacade.SINGLETON.removePresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_room);

        presenter = new WaitingRoomPresenter();


    }
}
