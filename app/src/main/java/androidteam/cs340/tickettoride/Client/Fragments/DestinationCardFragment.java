package androidteam.cs340.tickettoride.Client.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Activities.GameActivity;
import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.Client.State.TurnState;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.Player;

import static androidteam.cs340.tickettoride.Client.Phase2Facade.SINGLETON;

public class DestinationCardFragment extends Fragment implements IPresenter {
    private GameActivity context;
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

    private Button mSubmitButton;

    private DestinationCard destCard1;
    private DestinationCard destCard2;
    private DestinationCard destCard3;


    public void updateUI(){
        List<DestinationCard> destCards = Phase2Facade.SINGLETON.getTempDestinationCards();
        populateCards(destCards);
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

    public DestinationCardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Generate a new UUID for the presenter
        ID = UUID.randomUUID().toString();
    }

    private class BoxListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            LinearLayout box = (LinearLayout) v;
            if(box == mCard1Box){
                switchStatus(mCard1Box, mCard1Status);
            }
            else if(box == mCard2Box){
                switchStatus(mCard2Box, mCard2Status);
            }
            else{
                switchStatus(mCard3Box, mCard3Status);
            }

            updateButtonStatus();
        }
    }

    private class DestCardSubmitListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            List<DestinationCard> destCards = new ArrayList<DestinationCard>();
            if(!isKeeping(mCard1Status)){
                destCards.add(destCard1);
            }
            if(!isKeeping(mCard2Status)){
                if(destCard2 == null){

                }else{
                    destCards.add(destCard2);
                }
            }
            if(!isKeeping(mCard3Status)){
                if(destCard3 == null){

                }else{
                    destCards.add(destCard3);
                }
            }
            ((GameActivity)getActivity()).submitDestinationCards(destCards);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Grab the arguments to the fragment
        isStart = getArguments().getBoolean("isStart");
//        Toast.makeText(getContext(), isStart.toString(), Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination_card, container, false);

        mCard1Text = (TextView) view.findViewById(R.id.dest_card_1_text);
        mCard1Points = (TextView) view.findViewById(R.id.dest_card_1_points);
        mCard1Status = (TextView) view.findViewById(R.id.dest_card_1_status);
        mCard1Box = (LinearLayout) view.findViewById(R.id.dest_card_1_box);
        mCard1Box.setOnClickListener(new BoxListener());

        mCard2Text = (TextView) view.findViewById(R.id.dest_card_2_text);
        mCard2Points = (TextView) view.findViewById(R.id.dest_card_2_points);
        mCard2Status = (TextView) view.findViewById(R.id.dest_card_2_status);
        mCard2Box = (LinearLayout) view.findViewById(R.id.dest_card_2_box);
        mCard2Box.setOnClickListener(new BoxListener());


        mCard3Text = (TextView) view.findViewById(R.id.dest_card_3_text);
        mCard3Points = (TextView) view.findViewById(R.id.dest_card_3_points);
        mCard3Status = (TextView) view.findViewById(R.id.dest_card_3_status);
        mCard3Box = (LinearLayout) view.findViewById(R.id.dest_card_3_box);
        mCard3Box.setOnClickListener(new BoxListener());

        // Inflate the submit button and initalize it
        mSubmitButton = (Button) view.findViewById(R.id.dest_card_submit);
        mSubmitButton.setOnClickListener(new DestCardSubmitListener());
        mSubmitButton.setEnabled(false);
        mSubmitButton.setText("Submit");

        //Get the destination cards and initialize the cards
        List<DestinationCard> cards = new ArrayList<>(Arrays.asList(DestinationCard.values()));

        if(cards.size() < 3){
            Toast.makeText(getActivity(), "Too Few Cards" , Toast.LENGTH_SHORT).show();
        }
        else{
            toReturning(mCard1Box,mCard1Status);
            toReturning(mCard2Box,mCard2Status);
            toReturning(mCard3Box,mCard3Status);
        }

        return view;
    }

    private void toKeep(LinearLayout layout, TextView txt) {
        layout.setBackgroundColor(Color.parseColor("#00FF00"));
        txt.setText("KEEPING");
    }

    private void toReturning(LinearLayout layout, TextView txt){
        layout.setBackgroundColor(Color.parseColor("#FF0000"));
        txt.setText("RETURNING");
    }

    private void populateCards(List<DestinationCard> destCards){

        if (destCards != null) {
            if (destCards.size() == 3) {
                destCard1 = destCards.get(0);
                destCard2 = destCards.get(1);
                destCard3 = destCards.get(2);

                mCard1Text.setText(destCard1.cityA + " --> " + destCard1.cityB);
                mCard1Points.setText(destCard1.points + "");


                mCard2Text.setText(destCard2.cityA + " --> " + destCard2.cityB);
                mCard2Points.setText(destCard2.points + "");

                mCard3Text.setText(destCard3.cityA + " --> " + destCard3.cityB);
                mCard3Points.setText(destCard3.points + "");
            }
            else if(destCards.size() == 2) {
                destCard1 = destCards.get(0);
                destCard2 = destCards.get(1);

                mCard1Text.setText(destCard1.cityA + " --> " + destCard1.cityB);
                mCard1Points.setText(destCard1.points + "");

                mCard2Text.setText(destCard2.cityA + " --> " + destCard2.cityB);
                mCard2Points.setText(destCard2.points + "");

            }
            else if(destCards.size() == 1) {
                destCard1 = destCards.get(0);

                mCard1Text.setText(destCard1.cityA + " --> " + destCard1.cityB);
                mCard1Points.setText(destCard1.points + "");

            }
        }
    }

    private boolean isKeeping(TextView txt){
        if(txt.getText().equals("KEEPING")){
            return true;
        }
        else{
            return false;
        }
    }

    private void switchStatus(LinearLayout layout, TextView txt){
        if(txt.getText().equals("Waiting...")){
            return;
        }

        if(txt.getText().equals("KEEPING")){
            toReturning(layout, txt);
        }
        else{
            toKeep(layout,txt);
        }
    }

    private void updateButtonStatus() {
        int maxKeeping;
        if(isStart){
            maxKeeping = 2;
        }
        else{
            maxKeeping = 1;
        }

        int currentKeeping = 0;

        if(isKeeping(mCard1Status)){
            currentKeeping++;
        }
        if(isKeeping(mCard2Status)){
            currentKeeping++;
        }
        if(isKeeping(mCard3Status)){
            currentKeeping++;
        }

        if(currentKeeping >= maxKeeping){
            mSubmitButton.setEnabled(true);
        }
        else{
            mSubmitButton.setEnabled(false);
        }
    }
}
