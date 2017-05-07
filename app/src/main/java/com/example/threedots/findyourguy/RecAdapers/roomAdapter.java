package com.example.threedots.findyourguy.RecAdapers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.threedots.findyourguy.Common.MainActivity;
import com.example.threedots.findyourguy.Common.MessageActivity;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;

/**
 * Created by Cake on 6/5/2017.
 */

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.roomViewHolder> {
    private final DaoRoom daoRoom;
    ArrayList<Room> rooms;
    User user;
    Context context;
    public roomAdapter(ArrayList<Room> rooms, User user, Context context, DaoRoom daoRoom) {
        this.rooms=rooms;
        this.user=user;
        this.context=context;
        this.daoRoom=daoRoom;
    }

    @Override
    public roomAdapter.roomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_message,parent,false);
        return new roomAdapter.roomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(roomAdapter.roomViewHolder holder, int position) {
        final Room room=rooms.get(position);
        holder.Title.setText(room.getTitle());
        holder.UserName.setText(room.getUserName());
        if(room.getIsPrivate())
            holder.Status.setText("Private");
        else
            holder.Status.setText("Public");
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMessages(room);
            }
        });
        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                daoRoom.DeleteRoom(room.getID());
                return true;
            }
        });

    }

    private void goToMessages(Room room) {
        MainActivity mainActivity=(MainActivity)context;
        String ID= room.getID();
        String Title = room.getTitle();
        String UserName = room.getUserName();
        String UIDCreator = room.getUIDCreator();
        if(!room.getIsPrivate()){
            Intent intent=new Intent(mainActivity, MessageActivity.class);
            intent.putExtra("ID", ID);
            intent.putExtra("Title", Title);
            intent.putExtra("UserName", UserName);
            intent.putExtra("UIDCreator", UIDCreator);
            context.startActivity(intent);
        }

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class roomViewHolder extends RecyclerView.ViewHolder{
        TextView Title;
        TextView UserName;
        TextView Status;
        LinearLayout container;
        public roomViewHolder(View itemView) {
            super(itemView);
            Title=(TextView) itemView.findViewById(R.id.tvTitle);
            UserName=(TextView)itemView.findViewById(R.id.tvUserCreator);
            Status=(TextView)itemView.findViewById(R.id.tvPrivate);
            container=(LinearLayout)itemView.findViewById(R.id.roomContainer);
        }
    }
}
