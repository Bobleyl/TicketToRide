package androidteam.cs340.tickettoride.Client.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.State.EndTurn;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.EndGame;
import androidteam.cs340.tickettoride.Shared.Player;

public class EndGameActivity extends AppCompatActivity {
    TextView mp1Destination;
    TextView mp2Destination;
    TextView mp3Destination;
    TextView mp4Destination;
    TextView mp5Destination;
    TextView mp1DestinationFail;
    TextView mp2DestinationFail;
    TextView mp3DestinationFail;
    TextView mp4DestinationFail;
    TextView mp5DestinationFail;
    List<TextView> DestinationViews = new ArrayList<>();
    List<TextView> FailedDestinationViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        mp1Destination = findViewById(R.id.p1DestinationPoints);
        mp2Destination = findViewById(R.id.p2DestinationPoints);
        mp3Destination = findViewById(R.id.p3DestinationPoints);
        mp4Destination = findViewById(R.id.p4DestinationPoints);
        mp5Destination = findViewById(R.id.p5DestinationPoints);
        mp1DestinationFail = findViewById(R.id.p1DestinationPointsFail);
        mp2DestinationFail = findViewById(R.id.p2DestinationPointsFail);
        mp3DestinationFail = findViewById(R.id.p3DestinationPointsFail);
        mp4DestinationFail = findViewById(R.id.p4DestinationPointsFail);
        mp5DestinationFail = findViewById(R.id.p5DestinationPointsFail);
        DestinationViews.add(mp1Destination);
        DestinationViews.add(mp2Destination);
        DestinationViews.add(mp3Destination);
        DestinationViews.add(mp4Destination);
        DestinationViews.add(mp5Destination);
        FailedDestinationViews.add(mp1DestinationFail);
        FailedDestinationViews.add(mp2DestinationFail);
        FailedDestinationViews.add(mp3DestinationFail);
        FailedDestinationViews.add(mp4DestinationFail);
        FailedDestinationViews.add(mp5DestinationFail);
        updatePoints();
    }

    public void updatePoints(){
        int i = 0;
        for(Player player : EndGame.SINGLETON.getPlayers()){
            TextView temp = DestinationViews.remove(i);
            TextView temp2 = FailedDestinationViews.remove(i);
            temp.setText("" + player.getDestinationsFoundPoints());
            temp2.setText("" + player.getDestinationsNotFoundPoints());
            i++;
        }
    }
}
