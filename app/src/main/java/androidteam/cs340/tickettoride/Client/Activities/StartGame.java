package androidteam.cs340.tickettoride.Client.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import androidteam.cs340.tickettoride.R;

public class StartGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        System.out.println("Inside Start Game Activity!");
    }
}
