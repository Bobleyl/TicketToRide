package androidteam.cs340.tickettoride.Client.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import androidteam.cs340.tickettoride.Client.Phase2Facade;
import androidteam.cs340.tickettoride.R;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.Message;


public class MessageFragment extends Fragment {
    private RecyclerView mMessageRecyclerView;
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        mMessageRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_message);
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
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

    private class MessageHolder extends RecyclerView.ViewHolder {
        private TextView mMessageTitle;
        private TextView mMessageText;
        private Message mMessage;

        public MessageHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_message, parent, false));
            mMessageTitle = (TextView) itemView.findViewById(R.id.message_title);
            mMessageText = (TextView) itemView.findViewById(R.id.message_text);
        }

        public void bind(Message message) {
            mMessage = message;
            //TODO: SET ATTRIBUTES FOR SPECIFIC CELLS
        }
    }




    private class MessageAdapter extends RecyclerView.Adapter<MessageFragment.MessageHolder> {
        private List<Message> mMessage;

        public MessageAdapter(List<Message> message) {
            mMessage = message;
        }

        @NonNull
        @Override
        public MessageFragment.MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MessageFragment.MessageHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MessageFragment.MessageHolder messageHolder, int i) {
            Message message = mMessage.get(i);
            messageHolder.bind(message);
        }

        @Override
        public int getItemCount() {
            return mMessage.size();
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
