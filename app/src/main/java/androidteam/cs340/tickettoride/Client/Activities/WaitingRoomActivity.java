package androidteam.cs340.tickettoride.Client.Activities;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Presenters.WaitingRoomPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Player;

public class WaitingRoomActivity extends AppCompatActivity {
    private WaitingRoomPresenter presenter;

    private TextView mPlayerCount;
    private TextView mPlayer1;
    private TextView mPlayer2;
    private TextView mPlayer3;
    private TextView mPlayer4;
    private TextView mPlayer5;
    private List<TextView> playerList;

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

        presenter = new WaitingRoomPresenter(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mPlayerCount = (TextView) findViewById(R.id.player_count);
        mPlayer1 = (TextView) findViewById(R.id.player1);
        mPlayer2 = (TextView) findViewById(R.id.player2);
        mPlayer3 = (TextView) findViewById(R.id.player3);
        mPlayer4 = (TextView) findViewById(R.id.player4);
        mPlayer5 = (TextView) findViewById(R.id.player5);

        playerList = new ArrayList<>();
        playerList.add(mPlayer1);
        playerList.add(mPlayer2);
        playerList.add(mPlayer3);
        playerList.add(mPlayer4);
        playerList.add(mPlayer5);

        populatePlayerList();
    }

    public void updateUI(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populatePlayerList();
            }
        });
    }

    private void populatePlayerList(){
        Game game = ModelFacade.SINGLETON.getGame();
        List<Player> players = game.getPlayersList();

        mPlayerCount.setText(players.size() + " / " + game.getGameSize());

        for(int i = 0;i < players.size(); i++){
            if(i < playerList.size()) {
                playerList.get(i).setText(players.get(i).getUID());
            }
        }
    }
}