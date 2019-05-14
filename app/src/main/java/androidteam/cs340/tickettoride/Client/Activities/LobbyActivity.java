package androidteam.cs340.tickettoride.Client.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidteam.cs340.tickettoride.Client.Fragments.GameListFragment;
import androidteam.cs340.tickettoride.R;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.game_list);

        if (fragment == null) {
            fragment = new GameListFragment();
            fm.beginTransaction()
                    .add(R.id.game_list, fragment)
                    .commit();
        }

    }
}
