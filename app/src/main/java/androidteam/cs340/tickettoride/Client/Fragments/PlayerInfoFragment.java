package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Button;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.GameActivity;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.State.EndTurn;
import androidteam.cs340.tickettoride.Client.State.State;
import androidteam.cs340.tickettoride.Client.State.TurnState;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.TrainCard;


public class PlayerInfoFragment extends Fragment implements IPresenter {

    private ImageButton mDestinationDeck;
    private ImageButton mDownDeck;
    private ImageButton mUpDeck1;
    private ImageButton mUpDeck2;
    private ImageButton mUpDeck3;
    private ImageButton mUpDeck4;
    private ImageButton mUpDeck5;
    private boolean up1;
    private boolean up2;
    private boolean up3;
    private boolean up4;
    private boolean up5;
    private boolean endDeck = false;
    private ImageButton mDestinationCards;
    private TextView mWhoseTurn;
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
    private TextView mDestinationCount;
    private TextView mDownCount;
    private String mDestinationStringSelected;
    private Spinner mDestinationSpinner;
    private int DestSize;

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

    private void updateUI() {
        updateCount(Phase2Facade.SINGLETON.getMyDeck()); // UPDATES THE COUNT OF THE CARDS IN PLAYERS HAND
        mTrainCount.setText(String.valueOf(Phase2Facade.SINGLETON.getTrainCars())); // UPDATES THE COUNT OF TRAIN CARS IN PLAYERS POSSESSION.
        updateUpDeck(Phase2Facade.SINGLETON.getUpdeck()); // UPDATES THE FACE UP CARDS
        updateSpinner();
        mDestinationDeck.setEnabled(isMyTurn());
        mDownDeck.setEnabled(isMyTurn());
        if(!endDeck){
            up1 = isMyTurn();
            up2 = isMyTurn();
            up3 = isMyTurn();
            up4 = isMyTurn();
            up5 = isMyTurn();
        }
        mUpDeck1.setEnabled(up1);
        mUpDeck2.setEnabled(up2);
        mUpDeck3.setEnabled(up3);
        mUpDeck4.setEnabled(up4);
        mUpDeck5.setEnabled(up5);
//        for(Player player : Phase2Facade.SINGLETON.getCurrentGame().getPlayersList()){
//            if(player.getUID().equals(Phase2Facade.SINGLETON.getCurrentPlayer().getUID())){
//                if(DestSize == player.getDestinationHand().size()){
//                    break;
//                }else if(DestSize == 0){
//                    DestSize = player.getDestinationHand().size();
//                    break;
//                }else{
//                    DestSize = player.getDestinationHand().size();
//                    TurnState.SINGLETON.drawDestinationCard();
//                }
//            }
//        }

        if (Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getDownDeck().isEmpty() && Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck().length == 0 && TurnState.SINGLETON.isDownNotWildState()) {
            TurnState.SINGLETON.drawFaceDown();
        }
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
        mWhoseTurn = view.findViewById(R.id.whoseTurn);
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
        mDestinationCount = view.findViewById(R.id.destinationCount);
        mDownCount = view.findViewById(R.id.downCount);
        mDestinationSpinner = view.findViewById(R.id.destinationSpinner);

        String[] items = new String[]{"Values"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, items);
        mDestinationSpinner.setAdapter(adapter);


        mDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDestinationStringSelected = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mDownDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownDeck.setEnabled(false);
                if(!TurnState.SINGLETON.isEndState()) {
                    if(chooseDownCard()) {
                        TurnState.SINGLETON.drawFaceDown();
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        int i = 1;
        for (Player player : Phase2Facade.SINGLETON.getCurrentGame().getPlayersList()){
            if(Phase2Facade.SINGLETON.getCurrentPlayer().getUID().equals(player.getUID())){
                mWhoseTurn.setText("You are Player " + i);
            }
            i++;
        }

        mDestinationDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TurnState.SINGLETON.isAnythingState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getDestinationCardDeck().getDeck().size() == 0){
                        Toast.makeText(getActivity(), "Deck is Empty...", Toast.LENGTH_SHORT).show();
                    }else{
                        chooseDestinationCard();
                        TurnState.SINGLETON.drawDestinationCard();
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUpDeck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpDeck1.setEnabled(false);
                if(TurnState.SINGLETON.isAnythingState()) {
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0].equals(TrainCard.Locomotive)){
                        if (chooseUpCard(0)) {
                            TurnState.SINGLETON.drawFaceUpWild();
                        }
                    }else{
                        if (chooseUpCard(0)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else if(TurnState.SINGLETON.isDownNotWildState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0].equals(TrainCard.Locomotive)){
                        System.out.println("VALUE OF CARD CLICKED: " + Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0]);
                        Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                    }else{
                        if (chooseUpCard(0)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUpDeck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpDeck2.setEnabled(false);
                if(TurnState.SINGLETON.isAnythingState()) {
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[1].equals(TrainCard.Locomotive)){
                        if (chooseUpCard(1)) {
                            TurnState.SINGLETON.drawFaceUpWild();
                        }
                    }else{
                        if (chooseUpCard(1)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else if(TurnState.SINGLETON.isDownNotWildState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[1].equals(TrainCard.Locomotive)){
                        System.out.println("VALUE OF CARD CLICKED: " + Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0]);
                        Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                    }else{
                        if (chooseUpCard(1)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUpDeck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpDeck3.setEnabled(false);
                if(TurnState.SINGLETON.isAnythingState()) {
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[2].equals(TrainCard.Locomotive)){
                        if (chooseUpCard(2)) {
                            TurnState.SINGLETON.drawFaceUpWild();
                        }
                    }else{
                        if (chooseUpCard(2)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else if(TurnState.SINGLETON.isDownNotWildState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[2].equals(TrainCard.Locomotive)){
                        System.out.println("VALUE OF CARD CLICKED: " + Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0]);
                        Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                    }else{
                        if (chooseUpCard(2)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUpDeck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpDeck4.setEnabled(false);
                if(TurnState.SINGLETON.isAnythingState()) {
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[3].equals(TrainCard.Locomotive)){
                        if (chooseUpCard(3)) {
                            TurnState.SINGLETON.drawFaceUpWild();
                        }
                    }else{
                        if (chooseUpCard(3)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else if(TurnState.SINGLETON.isDownNotWildState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[3].equals(TrainCard.Locomotive)){
                        System.out.println("VALUE OF CARD CLICKED: " + Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0]);
                        Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                    }else{
                        if (chooseUpCard(3)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mUpDeck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUpDeck5.setEnabled(false);
                if(TurnState.SINGLETON.isAnythingState()) {
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[4].equals(TrainCard.Locomotive)){
                        if (chooseUpCard(4)) {
                            TurnState.SINGLETON.drawFaceUpWild();
                        }
                    }else{
                        if (chooseUpCard(4)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else if(TurnState.SINGLETON.isDownNotWildState()){
                    if(Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[4].equals(TrainCard.Locomotive)){
                        System.out.println("VALUE OF CARD CLICKED: " + Phase2Facade.SINGLETON.getCurrentGame().getTrainCardDeck().getUpDeck()[0]);
                        Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                    }else{
                        if (chooseUpCard(4)) {
                            TurnState.SINGLETON.drawFaceUpNonWild();
                        }
                    }
                }else{
                    Toast.makeText(getActivity(), "Not Allowed..." , Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    public boolean isMyTurn(){
        System.out.println("PLAYER UID: " + Phase2Facade.SINGLETON.getCurrentPlayer().getUID());
        System.out.println("TURN UID: " + Phase2Facade.SINGLETON.getCurrentGame().getPlayerTurn());
        if(Phase2Facade.SINGLETON.getCurrentPlayer().getUID().equals(Phase2Facade.SINGLETON.getCurrentGame().getPlayerTurn())){
            return true;
        }else{
            return false;
        }
    }

    private void updateSpinner(){
        int size = Phase2Facade.SINGLETON.getMyDestinationDeck().size();
        String[] items = new String[size];

        int i = 0;
        for(DestinationCard card : Phase2Facade.SINGLETON.getMyDestinationDeck()){
            items[i] = (card.cityA + " to " + card.cityB + " worth " + card.points + " points");
            i++;
        }

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, items);
            mDestinationSpinner.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println(e.toString());
        }


        mDestinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDestinationStringSelected = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateUpDeck(TrainCard[] cards){
        int count = 0;

        for(TrainCard card : cards){
            if (card != null) {
                if (card.color.equals("black")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.black_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.black_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.black_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.black_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.black_train_card);
                    }
                }
                if (card.color.equals("blue")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.blue_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.blue_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.blue_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.blue_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.blue_train_card);
                    }
                }
                if (card.color.equals("white")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.white_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.white_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.white_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.white_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.white_train_card);
                    }
                }
                if (card.color.equals("orange")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.orange_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.orange_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.orange_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.orange_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.orange_train_card);
                    }
                }
                if (card.color.equals("yellow")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.yellow_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.yellow_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.yellow_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.yellow_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.yellow_train_card);
                    }
                }
                if (card.color.equals("green")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.green_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.green_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.green_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.green_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.green_train_card);
                    }
                }
                if (card.color.equals("wild")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.wild_train_car);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.wild_train_car);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.wild_train_car);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.wild_train_car);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.wild_train_car);
                    }
                }
                if (card.color.equals("red")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.red_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.red_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.red_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.red_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.red_train_card);
                    }
                }
                if (card.color.equals("pink")) {
                    if (count == 0) {
                        mUpDeck1.setImageResource(R.drawable.purple_train_card);
                    }
                    if (count == 1) {
                        mUpDeck2.setImageResource(R.drawable.purple_train_card);
                    }
                    if (count == 2) {
                        mUpDeck3.setImageResource(R.drawable.purple_train_card);
                    }
                    if (count == 3) {
                        mUpDeck4.setImageResource(R.drawable.purple_train_card);
                    }
                    if (count == 4) {
                        mUpDeck5.setImageResource(R.drawable.purple_train_card);
                    }
                }
            } else {
                if (count == 0) { // zero cards
                    mUpDeck1.setImageResource(R.drawable.blank_card);
                    up1 = false;
                    mUpDeck2.setImageResource(R.drawable.blank_card);
                    mUpDeck3.setImageResource(R.drawable.blank_card);
                    mUpDeck4.setImageResource(R.drawable.blank_card);
                    mUpDeck5.setImageResource(R.drawable.blank_card);
                    endDeck = true;
                }

                if (count == 1) {
                    mUpDeck2.setImageResource(R.drawable.blank_card);
                    up1 = isMyTurn();
                    up2 = false;
                    mUpDeck3.setImageResource(R.drawable.blank_card);
                    mUpDeck4.setImageResource(R.drawable.blank_card);
                    mUpDeck5.setImageResource(R.drawable.blank_card);
                    endDeck = true;
                }

                if (count == 2) {
                    mUpDeck3.setImageResource(R.drawable.blank_card);
                    up1 = isMyTurn();
                    up2 = isMyTurn();
                    up3 = false;
                    mUpDeck4.setImageResource(R.drawable.blank_card);
                    mUpDeck5.setImageResource(R.drawable.blank_card);
                    endDeck = true;
                }

                if (count == 3) {
                    mUpDeck4.setImageResource(R.drawable.blank_card);
                    up1 = isMyTurn();
                    up2 = isMyTurn();
                    up3 = isMyTurn();
                    up4 = false;
                    mUpDeck5.setImageResource(R.drawable.blank_card);
                    endDeck = true;
                }

                if (count == 4) {
                    mUpDeck5.setImageResource(R.drawable.blank_card);
                    up1 = isMyTurn();
                    up2 = isMyTurn();
                    up3 = isMyTurn();
                    up4 = isMyTurn();
                    up5 = false;
                    endDeck = true;
                }
            }
            count++;
        }
    }

    //count through personal card deck and update counts for each color
    private void updateCount(List<TrainCard> cards){
        System.out.println("UPDATING PLAYERINFO");
        System.out.println(cards);
        int orange = 0;
        int white = 0;
        int red = 0;
        int wild = 0;
        int blue = 0;
        int green = 0;
        int purple = 0;
        int black = 0;
        int yellow = 0;
        mDestinationCount.setText("" + Phase2Facade.SINGLETON.destinationSize());
        mDownCount.setText("" + Phase2Facade.SINGLETON.getDownDeck().size());
        for(TrainCard card : cards){
            System.out.println("TYPE OF CARD DRAWN::");
            System.out.println(card);
            if(card.color.equals("black")){
                black = black + 1;
                mBlackCount.setText("" + black);
            }
            if(card.color.equals("white")){
                white = white + 1;
                mWhiteCount.setText("" + white);
            }
            if(card.color.equals("blue")){
                blue = blue + 1;
                mBlueCount.setText("" + blue);
            }
            if(card.color.equals("orange")){
                orange = orange + 1;
                mOrangeCount.setText("" + orange);
            }
            if(card.color.equals("red")){
                red = red + 1;
                mRedCount.setText("" + red);
            }
            if(card.color.equals("wild")){
                wild = wild + 1;
                mWildCount.setText("" + wild);
            }
            if(card.color.equals("yellow")){
                yellow = yellow + 1;
                mYellowCount.setText("" + yellow);
            }
            if(card.color.equals("green")){
                green = green + 1;
                mGreenCount.setText(""  + green);
            }
            if(card.color.equals("pink")){
                purple = purple + 1;
                mPinkCount.setText("" + purple);
            }
        }
    }

    private boolean chooseDownCard(){
        Result result = Phase2Facade.SINGLETON.drawDown();
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Toast.makeText(getActivity(), "Card Drawn..." , Toast.LENGTH_SHORT).show();
            return true;
            //TODO: DISPLAY TYPE OF CARD DRAWN.
        } else {
            Toast.makeText(getActivity(), "Unsuccessful Draw" , Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean chooseUpCard(int position){
        Toast.makeText(getActivity(), "Drawing Card..." , Toast.LENGTH_SHORT).show();
        Result result = Phase2Facade.SINGLETON.drawUp(position);
        if(result.getStatusCode() == HttpURLConnection.HTTP_OK) {
            Toast.makeText(getActivity(), "Card Drawn..." , Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(getActivity(), "Unsuccessful Draw" , Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean chooseDestinationCard(){

        Result resultGetTemp = Phase2Facade.SINGLETON.updateTempDestinationHand();
        if (resultGetTemp.getStatusCode() == 200) {
            ((GameActivity)getActivity()).drawDestinationCards();
            return true;
        } else {
            Toast.makeText(getActivity(), "Error: " + resultGetTemp.getStatusCode(),Toast.LENGTH_LONG).show();
            return false;
        }

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
}
