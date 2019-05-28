package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.ModelFacade;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.DestinationCard;

import static androidteam.cs340.tickettoride.Client.Phase2Facade.SINGLETON;

public class DestinationCardFragment extends Fragment implements IPresenter {
    private String ID;
    private Boolean isStart;

    private TextView mCard1Text;
    private TextView mCard1Points;
    private TextView mCard1Status;
    private LinearLayout mCard1Box;

    private TextView mCard2Text;
    private TextView mCard2Points;
    private TextView mCard2Status;
    private LinearLayout mCard2Box;

    private TextView mCard3Text;
    private TextView mCard3Points;
    private TextView mCard3Status;
    private LinearLayout mCard3Box;


    public void updateUI(){
        List<DestinationCard> destCards = Phase2Facade.SINGLETON.getPlayerDestinationCards();
        if(destCards.size() < 3){
            return;
        }
        else{
            populateCards(destCards);
        }
    }

    @Override
    public String getID(){
        return ID;
    }


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
        SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        SINGLETON.removePresenter(this);
    }

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    public DestinationCardFragment() {
        // Required empty public constructor
    }

    public static DestinationCardFragment newInstance(Boolean isStart) {
        DestinationCardFragment fragment = new DestinationCardFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isStart);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Generate a new UUID for the presenter
        ID = UUID.randomUUID().toString();

        //Figure out if we're doing the start of game destination card selection or not
        if (getArguments() != null) {
            isStart = getArguments().getBoolean(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Grab the arguments to the fragment
        isStart = getArguments().getBoolean("isStart");
        Toast.makeText(getContext(), isStart.toString(), Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination_card, container, false);

        mCard1Text = (TextView) view.findViewById(R.id.dest_card_1_text);
        mCard1Points = (TextView) view.findViewById(R.id.dest_card_1_points);
        mCard1Status = (TextView) view.findViewById(R.id.dest_card_1_status);
        mCard1Box = (LinearLayout) view.findViewById(R.id.dest_card_1_box); 

        mCard2Text = (TextView) view.findViewById(R.id.dest_card_2_text);
        mCard2Points = (TextView) view.findViewById(R.id.dest_card_2_points);
        mCard2Status = (TextView) view.findViewById(R.id.dest_card_2_status);
        mCard2Box = (LinearLayout) view.findViewById(R.id.dest_card_2_box);

        mCard3Text = (TextView) view.findViewById(R.id.dest_card_3_text);
        mCard3Points = (TextView) view.findViewById(R.id.dest_card_3_points);
        mCard3Status = (TextView) view.findViewById(R.id.dest_card_3_status);
        mCard3Box = (LinearLayout) view.findViewById(R.id.dest_card_3_box);

        List<DestinationCard> cards = new ArrayList<>(Arrays.asList(DestinationCard.values()));

        if(cards.size() < 3){
            Toast.makeText(getActivity(), "Too Few Cards" , Toast.LENGTH_SHORT).show();
        }
        else{
            populateCards(cards);
            toKeep(mCard1Box,mCard1Status);
            toKeep(mCard2Box,mCard2Status);
            toKeep(mCard3Box,mCard3Status);
        }

        return view;
    }

    private void toKeep(LinearLayout layout, TextView txt) {
        layout.setBackgroundColor(Color.green(15));
        txt.setText("KEEPING");
    }

    private void toReturn(LinearLayout layout, TextView txt){
        layout.setBackgroundColor(Color.blue(15));
        txt.setText("RETURNING");
    }

    private void populateCards(List<DestinationCard> destCards){
        DestinationCard card1 = destCards.get(0);
        DestinationCard card2 = destCards.get(1);
        DestinationCard card3 = destCards.get(2);

        mCard1Text.setText(card1.cityA + " --> " + card1.cityB);
        mCard1Points.setText(card1.points + "");


        mCard2Text.setText(card2.cityA + " --> " + card2.cityB);
        mCard2Points.setText(card2.points + "");

        mCard3Text.setText(card3.cityA + " --> " + card3.cityB);
        mCard3Points.setText(card3.points + "");
    }
}
