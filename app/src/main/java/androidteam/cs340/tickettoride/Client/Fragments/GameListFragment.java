package androidteam.cs340.tickettoride.Client.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;

public class GameListFragment extends Fragment implements IPresenter {
    private RecyclerView mGameRecyclerView;
    private GameAdapter mAdapter;
    private Button mCreateGameButton;

    @Override
    public void Update() {
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        // Add this presenter to our ModelFacade so it can be updated.
        ModelFacade.SINGLETON.addPresenter(this);

        mGameRecyclerView = (RecyclerView) view.findViewById(R.id.game_list_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCreateGameButton = (Button) view.findViewById(R.id.create_game_button);

        mCreateGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Creating Your Game..." , Toast.LENGTH_SHORT).show();
                //TODO: Create a game with 5 players and start WaitingRoomActivity.
                ModelFacade.SINGLETON.createGame(ModelFacade.SINGLETON.getPlayer().getUID(), 2);

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

        private Game mGame;

        public GameHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game, parent, false));

            mGameTitleTextView = (TextView) itemView.findViewById(R.id.game_name_view);
            mNumberOfPlayersTextView = (TextView) itemView.findViewById(R.id.game_number_of_players_view);
        }

        public void bind(Game game) {
            mGame = game;
            mGameTitleTextView.setText(game.getUID());
            mNumberOfPlayersTextView.setText(" " + game.getPlayersList().size() + "/5");
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

}
