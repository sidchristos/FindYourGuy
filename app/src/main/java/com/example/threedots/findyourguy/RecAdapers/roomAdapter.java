package com.example.threedots.findyourguy.RecAdapers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;

/**
 * Created by Cake on 6/5/2017.
 */

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.roomViewHolder> {
    ArrayList<Room> rooms;
    User user;
    Context context;
    public roomAdapter(ArrayList<Room> rooms, User user, Context context) {
        this.rooms=rooms;
        this.user=user;
        this.context=context;
    }

    @Override
    public roomAdapter.roomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_message,parent,false);
        return new roomAdapter.roomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(roomAdapter.roomViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }
    public static class roomViewHolder extends RecyclerView.ViewHolder{

        public roomViewHolder(View itemView) {
            super(itemView);

        }
    }
}
