package androidteam.cs340.tickettoride.Client.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.EndGame;
import androidteam.cs340.tickettoride.Shared.Player;

public class EndGameActivity extends AppCompatActivity {
    TextView mplayer1;
    TextView mplayer2;
    TextView mplayer3;
    TextView mplayer4;
    TextView mplayer5;
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
    ImageView mp1Longest;
    ImageView mp2Longest;
    ImageView mp3Longest;
    ImageView mp4Longest;
    ImageView mp5Longest;
    TextView p1Total;
    TextView p2Total;
    TextView p3Total;
    TextView p4Total;
    TextView p5Total;
    TextView mWinner;
    List<TextView> PlayersViews = new ArrayList<>();
    List<TextView> PlayersTotals = new ArrayList<>();
    List<ImageView> playerLongestView = new ArrayList<>();
    List<TextView> DestinationViews = new ArrayList<>();
    List<TextView> FailedDestinationViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        mWinner = findViewById(R.id.winnerBar);
        mWinner.setText("Player " + EndGame.SINGLETON.getFirstPlace().substring(0,4));
        mplayer1 = findViewById(R.id.player1);
        mplayer2 = findViewById(R.id.player2);
        mplayer3 = findViewById(R.id.player3);
        mplayer4 = findViewById(R.id.player4);
        mplayer5 = findViewById(R.id.player5);
        mp1Longest = findViewById(R.id.p1Longest);
        mp2Longest = findViewById(R.id.p2Longest);
        mp3Longest = findViewById(R.id.p3Longest);
        mp4Longest = findViewById(R.id.p4Longest);
        mp5Longest = findViewById(R.id.p5Longest);
        playerLongestView.add(mp1Longest);
        playerLongestView.add(mp2Longest);
        playerLongestView.add(mp3Longest);
        playerLongestView.add(mp4Longest);
        playerLongestView.add(mp5Longest);
        mp1Destination = findViewById(R.id.p1DestinationPoints);
        mp2Destination = findViewById(R.id.p2DestinationPoints);
        mp3Destination = findViewById(R.id.p3DestinationPoints);
        mp4Destination = findViewById(R.id.p4DestinationPoints);
        mp5Destination = findViewById(R.id.p5DestinationPoints);
        mp1DestinationFail = findViewById(R.id.p1DestinationPointsFail2);
        mp2DestinationFail = findViewById(R.id.p2DestinationPointsFail);
        mp3DestinationFail = findViewById(R.id.p3DestinationPointsFail);
        mp4DestinationFail = findViewById(R.id.p4DestinationPointsFail);
        mp5DestinationFail = findViewById(R.id.p5DestinationPointsFail);
        p1Total = findViewById(R.id.p1Total);
        p2Total = findViewById(R.id.p2Total);
        p3Total = findViewById(R.id.p3Total);
        p4Total = findViewById(R.id.p4Total);
        p5Total = findViewById(R.id.p5Total);
        PlayersTotals.add(p1Total);
        PlayersTotals.add(p2Total);
        PlayersTotals.add(p3Total);
        PlayersTotals.add(p4Total);
        PlayersTotals.add(p5Total);
        PlayersViews.add(mplayer1);
        PlayersViews.add(mplayer2);
        PlayersViews.add(mplayer3);
        PlayersViews.add(mplayer4);
        PlayersViews.add(mplayer5);
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
        updateFields();
    }

    public void updateFields(){
        int i = 0;
        for(Player player : EndGame.SINGLETON.getPlayers()){
            TextView score = PlayersTotals.get(i);
            TextView destinationPoints = DestinationViews.get(i);
            TextView failedPoints = FailedDestinationViews.get(i);
            TextView names = PlayersViews.get(i);
            names.setText("Player " + player.getUID().substring(0,4));
            destinationPoints.setText(player.getDestinationsFoundPoints());
            failedPoints.setText("-" + player.getDestinationsNotFoundPoints());
            score.setText(player.getScore());
            for(String longestPlayer : EndGame.SINGLETON.getLongestRoadPlayers()){
                if(longestPlayer.equals(player.getUID())){ //then current player position won it
                    ImageView temp4 = playerLongestView.get(i);
                    temp4.setImageResource(R.drawable.check);
                }
            }
            i++;
        }
    }
}
