package com.example.threedots.findyourguy.RecAdapers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;


public class messageAdapter extends RecyclerView.Adapter<messageAdapter.MessageViewHolder>{
    ArrayList<Message> messageListArray;
    User user;
    Context ctn;
    public messageAdapter(ArrayList<Message> messageListArray, User user, Context ctn) {
        this.messageListArray = messageListArray;
        this.user = user;
        this.ctn=ctn;
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
        holder.tvUID.setText(message.getUIDSender());
        holder.tvMessage.setText(message.getMessage());
        if(user.getUserId().equals(message.getUIDSender())){
            holder.tvUserName.setVisibility(View.GONE);
            holder.messageHolder.setGravity(Gravity.START);
        }
    }


    @Override
    public int getItemCount() {
        return messageListArray.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMessage,tvUserName,tvUID;
        LinearLayout messageHolder;
        public MessageViewHolder(View itemView) {
            super(itemView);
            tvMessage=(TextView) itemView.findViewById(R.id.tvMessage);
            tvUserName=(TextView) itemView.findViewById(R.id.tvUsername);
            tvUID=(TextView) itemView.findViewById(R.id.tvUID);
            messageHolder=(LinearLayout)itemView.findViewById(R.id.messageHolder);
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
