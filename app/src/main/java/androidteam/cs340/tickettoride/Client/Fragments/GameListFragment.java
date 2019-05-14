package androidteam.cs340.tickettoride.Client.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidteam.cs340.tickettoride.R;

public class GameListFragment extends Fragment {
    private RecyclerView mGameRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);

        mGameRecyclerView = (RecyclerView) view
                .findViewById(R.id.game_list_recycler_view);
        mGameRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private class GameHolder extends RecyclerView.ViewHolder {
        public GameHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_game, parent, false));
        }
    }

}
