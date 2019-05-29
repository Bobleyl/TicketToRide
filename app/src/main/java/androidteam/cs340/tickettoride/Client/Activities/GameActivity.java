package androidteam.cs340.tickettoride.Client.Activities;

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

import java.util.List;

import androidteam.cs340.tickettoride.Client.Fragments.DestinationCardFragment;
import androidteam.cs340.tickettoride.Client.Fragments.GameListFragment;
import androidteam.cs340.tickettoride.Client.Fragments.MapFragment;
import androidteam.cs340.tickettoride.Client.Fragments.MessageFragment;
import androidteam.cs340.tickettoride.Client.Fragments.PlayerInfoFragment;
import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.GameActivityPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.Player;


public class GameActivity extends AppCompatActivity implements
        MapFragment.OnFragmentInteractionListener,
        MessageFragment.OnFragmentInteractionListener,
        PlayerInfoFragment.OnFragmentInteractionListener {

    Button mMapButton;
    Button mGameInfoButton;
    Button mChatButton;
    TextView mPlayer1TextView;
    TextView mPlayer2TextView;
    TextView mPlayer3TextView;
    TextView mPlayer4TextView;
    TextView mPlayer5TextView;

    GameActivityPresenter mGameActivityPresenter;

    public void upDatePlayerTextViews(final List<Player> players) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI(players);
            }
        });
    }

    private void updateUI(List<Player> players) {
        StringBuilder playerText = new StringBuilder();
        if(players.size() >= 1 && players.get(0) != null) {
            Player currentPlayer = players.get(0);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size());
            mPlayer1TextView.setText(playerText.toString());
        }
        if(players.size() >= 2 && players.get(1) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(1);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size());
            mPlayer2TextView.setText(playerText.toString());
        }
        if(players.size() >= 3 && players.get(2) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(2);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size());
            mPlayer3TextView.setText(playerText.toString());
        }
        if(players.size() >= 4 && players.get(3) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(3);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size());
            mPlayer4TextView.setText(playerText.toString());
        }
        if(players.size() >= 5 && players.get(4) != null) {
            playerText = new StringBuilder();
            Player currentPlayer = players.get(4);
            playerText.append(currentPlayer.getName() + "\n" + currentPlayer.getScore() + "/" +
                    currentPlayer.getTrainCardsHand().size());
            mPlayer5TextView.setText(playerText.toString());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGameActivityPresenter = new GameActivityPresenter(this);

        Phase2Facade.SINGLETON.setGameID(ModelFacade.SINGLETON.getGameID());
        ModelFacade.SINGLETON.stopPoller();
        Log.d("GAME_ACTIVITY:GAME_ID", Phase2Facade.SINGLETON.getGameID());
        Phase2Facade.SINGLETON.startPoller();

        //TODO: Make this activity start on the correct fragent.
        if (findViewById(R.id.main_game_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            DestinationCardFragment firstFragment = new DestinationCardFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isStart", true);
            firstFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_game_fragment_container, firstFragment).commit();
        }

        mMapButton = (Button) findViewById(R.id.map_button);
        mGameInfoButton = (Button) findViewById(R.id.game_info_button);
        mChatButton = (Button) findViewById(R.id.chat_button);
        mPlayer1TextView = (TextView) findViewById(R.id.player1_text_view);
        mPlayer2TextView = (TextView) findViewById(R.id.player2_text_view);
        mPlayer3TextView = (TextView) findViewById(R.id.player3_text_view);
        mPlayer4TextView = (TextView) findViewById(R.id.player4_text_view);
        mPlayer5TextView = (TextView) findViewById(R.id.player5_text_view);

        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        //TODO:Add call to returnDestCard function
        startNewFragment(new PlayerInfoFragment());
    }

    public void drawDestinationCards(){
        Bundle args = new Bundle();
        args.putBoolean("isStart",false);
        DestinationCardFragment fragment = new DestinationCardFragment();
        fragment.setArguments(args);
        startNewFragment(fragment);
    }
}
