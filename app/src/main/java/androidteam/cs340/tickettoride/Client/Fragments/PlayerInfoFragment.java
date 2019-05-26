package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Dialog;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Client.Activities.LobbyActivity;
import androidteam.cs340.tickettoride.Client.Activities.LoginRegisterActivity;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.TrainCard;
import androidteam.cs340.tickettoride.Shared.DestinationCard;


public class PlayerInfoFragment extends Fragment implements IPresenter {
    Dialog myDialog; //used for pop up

    private ImageButton mDestinationDeck;
    private ImageButton mDownDeck;
    private ImageButton mUpDeck1;
    private ImageButton mUpDeck2;
    private ImageButton mUpDeck3;
    private ImageButton mUpDeck4;
    private ImageButton mUpDeck5;
    private ImageButton mDestinationCards;
    private TextView mOrangeCount;
    private TextView mWhiteCount;
    private TextView mRedCount;
    private TextView mWildCount;
    private TextView mBlueCount;
    private TextView mGreenCount;
    private TextView mPurpleCount;
    private TextView mBlackCount;
    private TextView mYellowCount;

    private String ID;
    private OnFragmentInteractionListener mListener;

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
        Phase2Facade.SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Phase2Facade.SINGLETON.removePresenter(this);
    }

    private void updateUI() {
        //UPDATE COUNT OF CARDS
        updateCount(Phase2Facade.SINGLETON.getMyDeck());
        //TODO: UPDATE UP CARD DECK, DOWN DECK CAN SIMPLY BE REFERENCED WHEN CLICKED.
        //TODO: UPDATE DESTINATION CARDS IN HAND
    }

    public String getID(){
        return ID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_player_info, container, false);

        mDestinationDeck = view.findViewById(R.id.destinationDeck);
        mDestinationCards = view.findViewById(R.id.destinationCards);
        mDownDeck = view.findViewById(R.id.downDeck);
        mUpDeck1 = view.findViewById(R.id.upDeck1);
        mUpDeck2 = view.findViewById(R.id.upDeck2);
        mUpDeck3 = view.findViewById(R.id.upDeck3);
        mUpDeck4 = view.findViewById(R.id.upDeck4);
        mUpDeck5 = view.findViewById(R.id.upDeck5);
        mOrangeCount = view.findViewById(R.id.orangeCount);
        mRedCount = view.findViewById(R.id.redCount);
        mWildCount = view.findViewById(R.id.wildCount);
        mWhiteCount = view.findViewById(R.id.whiteCount);
        mBlackCount = view.findViewById(R.id.blackCount);
        mBlueCount = view.findViewById(R.id.blueCount);
        mGreenCount = view.findViewById(R.id.greenCount);
        mPurpleCount = view.findViewById(R.id.purpleCount);
        mYellowCount = view.findViewById(R.id.yellowCount);

        mDownDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDownCard();
            }
        });

        mDestinationDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDestinationCard();
            }
        });

        mUpDeck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(1);
            }
        });

        mUpDeck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(2);
            }
        });

        mUpDeck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(3);
            }
        });

        mUpDeck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(4);
            }
        });

        mUpDeck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(5);
            }
        });

        mDestinationCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });

        return view;
    }


    public void showPopUp(View view){ //should allow opening and closing of destination card pop up.
        TextView txtClose = myDialog.findViewById(R.id.closePopUp);;
        myDialog.setContentView(R.layout.destination_pop_up);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //if X is clicked dismiss dialog
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    //count through personal card deck and update counts for each color
    private void updateCount(List<TrainCard> cards){
        int orange = 0;
        int white = 0;
        int red = 0;
        int wild = 0;
        int blue = 0;
        int green = 0;
        int purple = 0;
        int black = 0;
        int yellow = 0;
        for(TrainCard card : cards){
            if(card.color == "black"){
                black = black + 1;
                mBlackCount.setText(black);
            }
            if(card.color == "white"){
                white = white + 1;
                mWhiteCount.setText(white);
            }
            if(card.color == "blue"){
                blue = blue + 1;
                mBlueCount.setText(blue);
            }
            if(card.color == "orange"){
                orange = orange + 1;
                mOrangeCount.setText(orange);
            }
            if(card.color == "red"){
                red = red + 1;
                mRedCount.setText(red);
            }
            if(card.color == "wild"){
                wild = wild + 1;
                mWildCount.setText(wild);
            }
            if(card.color == "yellow"){
                yellow = yellow + 1;
                mYellowCount.setText(yellow);
            }
            if(card.color == "green"){
                green = green + 1;
                mGreenCount.setText(green);
            }
            if(card.color == "purple"){
                purple = purple + 1;
                mPurpleCount.setText(purple);
            }
        }
    }

    private void chooseDownCard(){
        Result result = Phase2Facade.SINGLETON.drawDown();
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Toast.makeText(getActivity(), "Card Drawn..." , Toast.LENGTH_SHORT).show();
            //TODO: DISPLAY TYPE OF CARD DRAWN.
        } else {
            Toast.makeText(getActivity(), "Unsuccessful Draw" , Toast.LENGTH_SHORT).show();
        }
        //TODO: ADD NEW CARD COUNT TO APPROPRIATE CARD COUNT
    }

    private void chooseUpCard(int position){
        Toast.makeText(getActivity(), "Drawing Card..." , Toast.LENGTH_SHORT).show();
        Result result = Phase2Facade.SINGLETON.drawUp(position);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Toast.makeText(getActivity(), "Card Drawn..." , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Unsuccessful Draw" , Toast.LENGTH_SHORT).show();
        }
        //TODO: ADD NEW CARD COUNT TO APPROPRIATE CARD COUNT
    }

    private void chooseDestinationCard(){
        //TODO: OPEN FRAGMENT FOR CHOOSING DESTINATION CARDS, BUT ALLOW RETURNING 0-2 INSTEAD OF 0-1
        //Result result = Phase2Facade.SINGLETON.drawDestination(card);
        //TODO: PASS RESULT TO NEW FRAGMENT
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    //TODO: LOGIC AND METHODS FOR RECYCLER VIEW INSIDE OF THE POP UP.
    //TODO: CALL ON FRAGMENT FOR CHOOSING DESTINATION CARDS WHEN THE DECK IS CLICKED
}
