package androidteam.cs340.tickettoride.Client.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;

public class GameListFragment extends Fragment {
    private RecyclerView mGameRecyclerView;
    private GameAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        mGameRecyclerView = (RecyclerView) view
                .findViewById(R.id.game_list_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }



    private void updateUI() {
        /*
        * Normally, this list of games would be called using ModelFacade.SINGLETON.getLobbyGames()
        * But for testing the games are just being created here.
        * */
        //List<Game> games = ModelFacade.SINGLETON.getLobbyGames();

        // TODO: Delete this code once we have ModelFacade.SINGLETON.getLobbyGames() working.
        List<Game> games = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Game toAdd = new Game(5);
            toAdd.setUID("Game " + i);
            games.add(toAdd);
        }

        mAdapter = new GameAdapter(games);
        mGameRecyclerView.setAdapter(mAdapter);
    }




    private class GameHolder extends RecyclerView.ViewHolder {
        public GameHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game, parent, false));
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

        }

        @Override
        public int getItemCount() {
            return mGames.size();
        }
    }

}
