package androidteam.cs340.tickettoride.Client.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Fragments.DestinationCardFragment;
import androidteam.cs340.tickettoride.Client.Fragments.MapFragment;
import androidteam.cs340.tickettoride.Client.Fragments.MessageFragment;
import androidteam.cs340.tickettoride.Client.Fragments.PlayerInfoFragment;
import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.GameActivityPresenter;
import androidteam.cs340.tickettoride.Client.ServerProxy;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.EndGame;
import androidteam.cs340.tickettoride.Shared.Player;


public class GameActivity extends AppCompatActivity implements
        MapFragment.OnFragmentInteractionListener,
        MessageFragment.OnFragmentInteractionListener,
        PlayerInfoFragment.OnFragmentInteractionListener {

    MediaPlayer ring;
    Button mMapButton;
    Button mGameInfoButton;
    Button mChatButton;
    TextView mPlayer1TextView;
    TextView mPlayer2TextView;
    TextView mPlayer3TextView;
    TextView mPlayer4TextView;
    TextView mPlayer5TextView;

    GameActivityPresenter mGameActivityPresenter;

    public void upDatePlayerTextViews(final List<Player> players, final String turnPlayer) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI(players, turnPlayer);
                if(!(Phase2Facade.SINGLETON.getFirstPlace() == "")){
                    Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void updateUI(List<Player> players, String turnPlayer) {
//        if(Phase2Facade.SINGLETON.getMusic() == false){
//            ring.pause();
//        }else{
//            ring.start();
//        }
        StringBuilder playerText = new StringBuilder();
        if(players.size() >= 1 && players.get(0) != null) {
            Player currentPlayer = players.get(0);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size() + "/" + currentPlayer.getDestinationHand().size() + "/" + currentPlayer.getTrainCars());
            mPlayer1TextView.setText(playerText.toString());
            if(currentPlayer.getUID().equals(turnPlayer)) {
                mPlayer1TextView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
            else{
                mPlayer1TextView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(players.size() >= 2 && players.get(1) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(1);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size() + "/" + currentPlayer.getDestinationHand().size() + "/" + currentPlayer.getTrainCars());
            mPlayer2TextView.setText(playerText.toString());
            if(currentPlayer.getUID().equals(turnPlayer)) {
                mPlayer2TextView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
            else{
                mPlayer2TextView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if(players.size() >= 3 && players.get(2) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(2);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size() + "/" + currentPlayer.getDestinationHand().size() + "/" + currentPlayer.getTrainCars());
            mPlayer3TextView.setText(playerText.toString());
            if(currentPlayer.getUID().equals(turnPlayer)) {
                mPlayer3TextView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
            else{
                mPlayer3TextView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        else{
            mPlayer3TextView.setText("");
        }
        if(players.size() >= 4 && players.get(3) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(3);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size() + "/" + currentPlayer.getDestinationHand().size() + "/" + currentPlayer.getTrainCars());
            mPlayer4TextView.setText(playerText.toString());
            if(currentPlayer.getUID().equals(turnPlayer)) {
                mPlayer4TextView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
            else{
                mPlayer4TextView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        else{
            mPlayer4TextView.setText("");
        }
        if(players.size() >= 5 && players.get(4) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(4);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size() + "/" + currentPlayer.getDestinationHand().size() + "/" + currentPlayer.getTrainCars());
            mPlayer5TextView.setText(playerText.toString());
            if(currentPlayer.getUID().equals(turnPlayer)) {
                mPlayer5TextView.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }
            else{
                mPlayer5TextView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        else{
            mPlayer5TextView.setText("");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameActivityPresenter = new GameActivityPresenter(this);

        ModelFacade.SINGLETON.stopPoller();

        ServerProxy.SINGLETON.deleteGame(ModelFacade.SINGLETON.getGameID());

        //ring = MediaPlayer.create(GameActivity.this,R.raw.victory_music);
        //ring.start();
        Phase2Facade.SINGLETON.setMusic(true);

        mMapButton = (Button) findViewById(R.id.map_button);
        mGameInfoButton = (Button) findViewById(R.id.game_info_button);
        mChatButton = (Button) findViewById(R.id.chat_button);
        mPlayer1TextView = (TextView) findViewById(R.id.player1_text_view);
        mPlayer2TextView = (TextView) findViewById(R.id.player2_text_view);
        mPlayer3TextView = (TextView) findViewById(R.id.player3_text_view);
        mPlayer4TextView = (TextView) findViewById(R.id.player4_text_view);
        mPlayer5TextView = (TextView) findViewById(R.id.player5_text_view);

        if (findViewById(R.id.main_game_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            mMapButton.setEnabled(false);
            mGameInfoButton.setEnabled(false);
            mChatButton.setEnabled(false);
            DestinationCardFragment firstFragment = new DestinationCardFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isStart", true);
            firstFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_game_fragment_container, firstFragment).commit();
        }

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phase2Facade.SINGLETON.setMusic(true);
                MapFragment mapFragment = new MapFragment();
                startNewFragment(mapFragment);
            }
        });

        mChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageFragment messageFragment = new MessageFragment();
                startNewFragment(messageFragment);
            }
        });

        mGameInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Phase2Facade.SINGLETON.setMusic(true);
                PlayerInfoFragment playerInfoFragment = new PlayerInfoFragment();
                startNewFragment(playerInfoFragment);
            }
        });

    }

    private void startNewFragment(Fragment toStart) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_game_fragment_container, toStart).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Needs to be implemented, but not sure what it does.
    }


    @Override
    public void onBackPressed(){
        Toast.makeText(GameActivity.this, "Where do you think you're going?",Toast.LENGTH_LONG).show();
    }

    public void submitDestinationCards(List<DestinationCard> destCards){
        Toast.makeText(this, "Submitting Cards", Toast.LENGTH_SHORT).show();
        Phase2Facade.SINGLETON.returnDestination((ArrayList<DestinationCard>)destCards);
        startNewFragment(new PlayerInfoFragment());
        mMapButton.setEnabled(true);
        mGameInfoButton.setEnabled(true);
        mChatButton.setEnabled(true);
    }

    public void drawDestinationCards(){
        Bundle args = new Bundle();
        args.putBoolean("isStart",false);
        mMapButton.setEnabled(false);
        mGameInfoButton.setEnabled(false);
        mChatButton.setEnabled(false);
        DestinationCardFragment fragment = new DestinationCardFragment();
        fragment.setArguments(args);
        startNewFragment(fragment);
    }

}
