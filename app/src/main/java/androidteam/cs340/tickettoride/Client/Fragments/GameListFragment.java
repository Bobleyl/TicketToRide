package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.LobbyActivity;
import androidteam.cs340.tickettoride.Client.Activities.LoginRegisterActivity;
import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Result;

public class GameListFragment extends Fragment implements IPresenter {
    private RecyclerView mGameRecyclerView;
    private GameAdapter mAdapter;
    private Button mCreateGameButton;
    private Spinner mNumberOfPlayersSpinner;
    private int mSpinnerNumberSelected;

    private String ID;

    public void Update() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        ModelFacade.SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        ModelFacade.SINGLETON.removePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        //Generate a new UUID for the presenter
        ID = UUID.randomUUID().toString();


        // Set views for buttons, recyclerView and Spinner.
        mGameRecyclerView = (RecyclerView) view.findViewById(R.id.game_list_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCreateGameButton = (Button) view.findViewById(R.id.create_game_button);
        mNumberOfPlayersSpinner = (Spinner) view.findViewById(R.id.number_of_players_spinner);


        // Set adapter for Spinner to fill values.
        Integer[] items = new Integer[]{2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item, items);
        mNumberOfPlayersSpinner.setAdapter(adapter);


        mNumberOfPlayersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerNumberSelected = (int) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Listener for Create Game Button
        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGame();
            }
        });


        updateUI();

        return view;
    }



    private void updateUI() {
        List<Game> games = ModelFacade.SINGLETON.getLobbyGames();

        mAdapter = new GameAdapter(games);
        mGameRecyclerView.setAdapter(mAdapter);
    }




    private class GameHolder extends RecyclerView.ViewHolder {
        private TextView mGameTitleTextView;
        private TextView mNumberOfPlayersTextView;
        private Button mJoinButton;

        private Game mGame;

        public GameHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game, parent, false));

            mGameTitleTextView = (TextView) itemView.findViewById(R.id.game_name_view);
            mNumberOfPlayersTextView = (TextView) itemView.findViewById(R.id.game_number_of_players_view);
            mJoinButton = (Button) itemView.findViewById(R.id.join_game_button);
        }

        public void bind(Game game) {
            mGame = game;
            mGameTitleTextView.setText(game.getUID());
            mNumberOfPlayersTextView.setText(" " + game.getPlayersList().size() + "/" + game.getGameSize());

            //Set onclickListener for joingame button
            mJoinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Joining Game", Toast.LENGTH_SHORT).show();
                    ModelFacade.SINGLETON.joinGame(ModelFacade.SINGLETON.getPlayer().getUID(), ModelFacade.SINGLETON.getGameID());
                }
            });
        }
    }




    private class GameAdapter extends RecyclerView.Adapter<GameHolder> {
        private List<Game> mGames;

        public GameAdapter(List<Game> games) {
            mGames = games;
        }

        @NonNull
        @Override
        public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new GameHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull GameHolder gameHolder, int i) {
            Game game = mGames.get(i);
            gameHolder.bind(game);
        }

        @Override
        public int getItemCount() {
            return mGames.size();
        }
    }


    //getID method needed for IPresenters
    public String getID(){
        return ID;
    }


    private void createGame(){
        Toast.makeText(getActivity(), "Creating Your Game..." , Toast.LENGTH_SHORT).show();
        Result result = ModelFacade.SINGLETON.createGame(ModelFacade.SINGLETON.getPlayer().getUID(), mSpinnerNumberSelected);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK){
            joinGame(result.getData());
        }
        else{
            Toast.makeText(getActivity(), result.getErrorInfo() , Toast.LENGTH_SHORT).show();
        }
    }

    private void joinGame(String gameID){
        ModelFacade.SINGLETON.setGame(gameID);
    }
}
