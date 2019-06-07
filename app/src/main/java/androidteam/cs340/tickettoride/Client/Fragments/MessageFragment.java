package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.net.Uri;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.Client.Presenters.IPresenter;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Message;


public class MessageFragment extends Fragment implements IPresenter {
    private OnFragmentInteractionListener mListener;
    private String ID;
    private MediaPlayer mediaPlayer;
    private EditText mMessageEditText;
    private RecyclerView mMessageRecyclerView;
    private Button mSendMessageButton;
    private MessageAdapter mAdapter;
    private int messageListSize = 0;
    private VideoView mVideoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);


        mMessageRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_message);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessageRecyclerView.bringToFront();

        mVideoView = view.findViewById(R.id.videoView);

        mMessageEditText = (EditText) view.findViewById(R.id.messageField);
        mMessageEditText.bringToFront();
        mSendMessageButton = (Button) view.findViewById(R.id.message_add_button);
        mSendMessageButton.bringToFront();

        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mMessageEditText.getText().toString());
            }
        });

        updateUI();

        return view;
    }

    private class MessageHolder extends RecyclerView.ViewHolder {
        private TextView mMessageTitle;
        private TextView mMessageText;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_message, parent, false));
            mMessageTitle = (TextView) itemView.findViewById(R.id.message_title);
            mMessageText = (TextView) itemView.findViewById(R.id.message_text);
        }

        public void bind(Message message) {
            String playerID = message.getPlayerID().substring( 0, 4);
            mMessageTitle.setText("Player " + playerID);
            mMessageText.setText(message.getTextMessage());
        }
    }

    private class MessageAdapter extends RecyclerView.Adapter<MessageFragment.MessageHolder> {
        private List<Message> mMessages;

        public MessageAdapter(List<Message> message) {
            mMessages = message;
        }

        @Override
        public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MessageHolder holder, int i) {
            Message message = mMessages.get(i);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return mMessages.size();
        }
    }

    private void updateUI() {
        List<Message> messages = Phase2Facade.SINGLETON.getMessages();
        if(messageListSize != messages.size()) {
            messageListSize = messages.size();
            Collections.reverse(messages);
            mAdapter = new MessageAdapter(messages);
            mMessageRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void Update() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateUI();
            }
        });
    }

    public MessageFragment() {
        this.ID = UUID.randomUUID().toString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public String getID() {
        return ID;
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




    private void sendMessage(String message) {
        Message toSend = new Message(Phase2Facade.SINGLETON.getCurrentPlayer().getUID(), message);
        Phase2Facade.SINGLETON.sendMessage(toSend);
        String search = "hi";
        if(message.toLowerCase().indexOf(search.toLowerCase()) != -1){
            if(Phase2Facade.SINGLETON.getMusic() == true){
                Phase2Facade.SINGLETON.setMusic(false);
            }
            System.out.println("Message is : " + message);
            mVideoView.bringToFront();
            mVideoView.setVisibility(View.VISIBLE);
            String uriPath = "android.resource://androidteam.cs340.tickettoride/"+R.raw.rick_roll_vid;
            Uri uri = Uri.parse(uriPath);
            mVideoView.setVideoURI(uri);
            mVideoView.requestFocus();
            mVideoView.start();
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
