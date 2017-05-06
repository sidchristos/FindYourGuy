package com.example.threedots.findyourguy.RecAdapers;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;

public class messageAdapter extends RecyclerView.Adapter<messageAdapter.MessageViewHolder>{
    ArrayList<Message> messageListArray;

    public messageAdapter(ArrayList<Message> messageListArray) {
        this.messageListArray = messageListArray;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_message,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message=messageListArray.get(position);
        holder.tvUserName.setText(message.getUserName());
        holder.tvUID.setText(Long.toString(message.getUIDSender()));
        holder.tvMessage.setText(message.getMessage());
        
    }


    @Override
    public int getItemCount() {
        return 0;
    }
    public static class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMessage,tvUserName,tvUID;
        CardView messageHolder;

        public MessageViewHolder(View itemView) {
            super(itemView);
            tvMessage=(TextView) itemView.findViewById(R.id.tvMessage);
            tvUserName=(TextView) itemView.findViewById(R.id.tvUsername);
            tvUID=(TextView) itemView.findViewById(R.id.tvUID);
            messageHolder=(CardView)itemView.findViewById(R.id.messageHolder);
            messageHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            functionToOpenClickedUserProfile(tvUID.getText().toString());
        }

        private void functionToOpenClickedUserProfile(String UID) {
        }
    }
}
