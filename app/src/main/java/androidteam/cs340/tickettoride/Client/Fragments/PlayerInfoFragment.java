package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
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

import java.util.List;

import androidteam.cs340.tickettoride.Client.ModelFacade;
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
        ModelFacade.SINGLETON.addPresenter(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        ModelFacade.SINGLETON.removePresenter(this);
    }

    private void updateUI() {
        //TODO: UPDATE COUNT OF CARDS
        //TODO: UPDATE CARD DECKS
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

        mDestinationDeck = (ImageButton) view.findViewById(R.id.destinationDeck);
        mDestinationCards = (ImageButton) view.findViewById(R.id.destinationCards);
        mDownDeck = (ImageButton) view.findViewById(R.id.downDeck);
        mUpDeck1 = (ImageButton) view.findViewById(R.id.upDeck1);
        mUpDeck2 = (ImageButton) view.findViewById(R.id.upDeck2);
        mUpDeck3 = (ImageButton) view.findViewById(R.id.upDeck3);
        mUpDeck4 = (ImageButton) view.findViewById(R.id.upDeck4);
        mUpDeck5 = (ImageButton) view.findViewById(R.id.upDeck5);
        mOrangeCount = (TextView) view.findViewById(R.id.orangeCount);
        mRedCount = (TextView) view.findViewById(R.id.redCount);
        mWildCount = (TextView) view.findViewById(R.id.wildCount);
        mWhiteCount = (TextView) view.findViewById(R.id.whiteCount);
        mBlackCount = (TextView) view.findViewById(R.id.blackCount);
        mBlueCount = (TextView) view.findViewById(R.id.blueCount);
        mGreenCount = (TextView) view.findViewById(R.id.greenCount);
        mPurpleCount = (TextView) view.findViewById(R.id.purpleCount);
        mYellowCount = (TextView) view.findViewById(R.id.yellowCount);

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
        TextView txtClose = (TextView) myDialog.findViewById(R.id.closePopUp);;
        myDialog.setContentView(R.layout.destination_pop_up);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //if X is clicked dismiss dialog
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    private void chooseDownCard(){
        Toast.makeText(getActivity(), "Drawing Card..." , Toast.LENGTH_SHORT).show();
        //Result result = ModelFacade.SINGLETON.drawDown(card);
        //TODO: ADD NEW CARD COUNT TO APPROPRIATE CARD COUNT
    }

    private void chooseUpCard(int position){
        Toast.makeText(getActivity(), "Drawing Card..." , Toast.LENGTH_SHORT).show();
        //Result result = ModelFacade.SINGLETON.drawUp(card);
        //TODO: ADD NEW CARD COUNT TO APPROPRIATE CARD COUNT
    }

    private void chooseDestinationCard(){
        //TODO: OPEN FRAGMENT FOR CHOOSING DESTINATION CARDS, BUT ALLOW RETURNING 0-2 INSTEAD OF 0-1
        //Result result = ModelFacade.SINGLETON.drawDestination(card);
        //TODO: ADD NEW CARDS TO DESTINATION HAND
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
