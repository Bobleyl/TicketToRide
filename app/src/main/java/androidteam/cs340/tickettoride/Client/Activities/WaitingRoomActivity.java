package androidteam.cs340.tickettoride.Client.Activities;

import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.WaitingRoomPresenter;
import androidteam.cs340.tickettoride.Client.ServerProxy;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;

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

    private void populatePlayerList() {

        Game game = ModelFacade.SINGLETON.getGame();
        //System.out.println(game.getUID());
        List<Game> games = ModelFacade.SINGLETON.getLobbyGames();

        List<Player> players = game.getPlayersList();

        boolean found = false;

        Log.d("WAITING_ROOM", game.getUID());
        Log.d("WAITING_ROOM", Integer.toString(game.getGameSize()));
        Log.d("WAITING_ROOM", Integer.toString(players.size()));

        if (players.size() == game.getGameSize()) {
            Result result = ServerProxy.SINGLETON.deleteGame(game.getUID());
            Log.d("WAITING_ROOM", "Deleted game...");
            Log.d("WAITING_ROOM", game.getUID());
            Log.d("WAITING_ROOM", Integer.toString(result.getStatusCode()));
        }

        for(Game game1 : games) {
            if(game.getUID().equals(game1.getUID())) {
                found = true;
            }
        }

        Log.d("WAITING_ROOM", Boolean.toString(found));

        if(!found && players.size() == game.getGameSize()){
            System.out.println("GAME IS FULL, LET'S PLAY");
            Phase2Facade.SINGLETON.setGameID(ModelFacade.SINGLETON.getGameID());
            ModelFacade.SINGLETON.stopPoller();
            Phase2Facade.SINGLETON.startPoller();
            Intent intent = new Intent(WaitingRoomActivity.this, GameActivity.class);
            startActivity(intent);
        }

        mPlayerCount.setText(players.size() + " / " + game.getGameSize());

        for(int i = 0;i < players.size(); i++){
            if(i < playerList.size()) {
                playerList.get(i).setText("Player " + players.get(i).getUID().substring(0, 4));
            }
        }
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(WaitingRoomActivity.this, "Sorry, you can't leave the game.",Toast.LENGTH_LONG).show();
    }

}
