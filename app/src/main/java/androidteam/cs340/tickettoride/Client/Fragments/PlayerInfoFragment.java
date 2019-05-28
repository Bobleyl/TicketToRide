package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Dialog;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.TrainCard;


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
    private TextView mPinkCount;
    private TextView mBlackCount;
    private TextView mYellowCount;
    private TextView mTrainCount;

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
        this.ID = UUID.randomUUID().toString();
        Phase2Facade.SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        Phase2Facade.SINGLETON.removePresenter(this);
    }

    private void updateUI() { // TODO: ONLY UPDATE SPECIFIC THINGS WHEN THERE IS A CHANGE TO THEM
        updateCount(Phase2Facade.SINGLETON.getMyDeck()); // UPDATES THE COUNT OF THE CARDS IN PLAYERS HAND
        mTrainCount.setText(String.valueOf(Phase2Facade.SINGLETON.getTrainCars())); // UPDATES THE COUNT OF TRAIN CARS IN PLAYERS POSSESSION.
        updateUpDeck(Phase2Facade.SINGLETON.getUpdeck()); // UPDATES THE FACE UP CARDS
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
        mPinkCount = view.findViewById(R.id.pinkCount);
        mYellowCount = view.findViewById(R.id.yellowCount);
        mTrainCount = view.findViewById(R.id.trainCount);


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
                chooseUpCard(0);
            }
        });

        mUpDeck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(1);
            }
        });

        mUpDeck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(2);
            }
        });

        mUpDeck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(3);
            }
        });

        mUpDeck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpCard(4);
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

    private void updateUpDeck(TrainCard[] cards){
        int count = 0;
        for(TrainCard card : cards){
            if(card.color == "black"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.black_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.black_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.black_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.black_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.black_train_card);
                }
            }
            if(card.color == "blue"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.blue_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.blue_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.blue_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.blue_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.blue_train_card);
                }
            }
            if(card.color == "white"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.white_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.white_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.white_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.white_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.white_train_card);
                }
            }
            if(card.color == "orange"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.orange_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.orange_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.orange_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.orange_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.orange_train_card);
                }
            }
            if(card.color == "yellow"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.yellow_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.yellow_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.yellow_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.yellow_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.yellow_train_card);
                }
            }
            if(card.color == "green"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.green_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.green_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.green_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.green_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.green_train_card);
                }
            }
            if(card.color == "wild"){ // TODO: ADD wild_train_card TO DRAWABLE
                if(count == 0){
                    //mUpDeck1.setBackgroundResource(R.drawable.wild_train_card);
                }
                if(count == 1){
                    //mUpDeck2.setBackgroundResource(R.drawable.wild_train_card);
                }
                if(count == 2){
                    //mUpDeck3.setBackgroundResource(R.drawable.wild_train_card);
                }
                if(count == 3){
                    //mUpDeck4.setBackgroundResource(R.drawable.wild_train_card);
                }
                if(count == 4){
                    //mUpDeck5.setBackgroundResource(R.drawable.wild_train_card);
                }
            }
            if(card.color == "red"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.red_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.red_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.red_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.red_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.red_train_card);
                }
            }
            if(card.color == "pink"){
                if(count == 0){
                    mUpDeck1.setBackgroundResource(R.drawable.purple_train_card);
                }
                if(count == 1){
                    mUpDeck2.setBackgroundResource(R.drawable.purple_train_card);
                }
                if(count == 2){
                    mUpDeck3.setBackgroundResource(R.drawable.purple_train_card);
                }
                if(count == 3){
                    mUpDeck4.setBackgroundResource(R.drawable.purple_train_card);
                }
                if(count == 4){
                    mUpDeck5.setBackgroundResource(R.drawable.purple_train_card);
                }
            }
            count++;
        }
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
                mPinkCount.setText(purple);
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
    }

    private void chooseUpCard(int position){
        Toast.makeText(getActivity(), "Drawing Card..." , Toast.LENGTH_SHORT).show();
        Result result = Phase2Facade.SINGLETON.drawUp(position);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Toast.makeText(getActivity(), "Card Drawn..." , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Unsuccessful Draw" , Toast.LENGTH_SHORT).show();
        }
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
    //TODO: ADD TRAIN CARD LIST TO RECYCLER VIEW IN POP UP.
}
