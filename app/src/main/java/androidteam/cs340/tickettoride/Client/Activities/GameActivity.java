package androidteam.cs340.tickettoride.Client.Activities;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidteam.cs340.tickettoride.Client.Fragments.GameListFragment;
import androidteam.cs340.tickettoride.Client.Fragments.PlayerInfoFragment;
import androidteam.cs340.tickettoride.R;

public class GameActivity extends AppCompatActivity implements PlayerInfoFragment.OnFragmentInteractionListener {
    Button mMapButton;
    Button mGameInfoButton;
    Button mChatButton;
    TextView mPlayer1TextView;
    TextView mPlayer2TextView;
    TextView mPlayer3TextView;
    TextView mPlayer4TextView;
    TextView mPlayer5TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        if (findViewById(R.id.main_game_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            PlayerInfoFragment firstFragment = new PlayerInfoFragment();
            firstFragment.setArguments(getIntent().getExtras());
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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO: What should be put here?
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(GameActivity.this, "Where do you think you're going?",Toast.LENGTH_LONG).show();
    }
}