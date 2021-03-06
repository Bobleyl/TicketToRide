package androidteam.cs340.tickettoride.Client.Activities;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.HttpURLConnection;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Presenters.LoginRegisterPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.User;

public class LoginRegisterActivity extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mRegisterButton;

    private LoginRegisterPresenter presenter;

    private StringBuilder username;
    private StringBuilder password;

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
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        presenter = new LoginRegisterPresenter();

        mUsernameField = (EditText) findViewById(R.id.userNameField);
        mUsernameField.addTextChangedListener(loginTextWatcher);

        mPasswordField = (EditText) findViewById(R.id.passwordField);
        mPasswordField.addTextChangedListener(loginTextWatcher);

        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginButton.setEnabled(false);
        mRegisterButton = (Button) findViewById(R.id.registerButton);
        mRegisterButton.setEnabled(false);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Logging you in...",Toast.LENGTH_SHORT).show();
                User user = new User(username.toString(), password.toString());
                Result result = presenter.login(user);

                // Switch activity to LobbyActivity
                if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    Intent intent = new Intent(LoginRegisterActivity.this, LobbyActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Invalid username or password" , Toast.LENGTH_SHORT).show();
                }

            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Registering...",Toast.LENGTH_SHORT).show();
                User user = new User(username.toString(), password.toString());
                Result result = presenter.register(user);

                //Currently a 204 is being returned.. might switch this to a 200..
                if(result.getStatusCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                    Toast.makeText(getBaseContext(), "Successful registration" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "User already exists" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            username =  new StringBuilder(mUsernameField.getText());
            password = new StringBuilder(mPasswordField.getText());

            mLoginButton.setEnabled(!username.toString().isEmpty() && !password.toString().isEmpty());
            mRegisterButton.setEnabled(!username.toString().isEmpty() && !password.toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };


}
