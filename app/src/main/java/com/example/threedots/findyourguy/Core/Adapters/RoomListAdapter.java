package com.example.threedots.findyourguy.Core.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

    private  List<Room> mRooms;//todo room/message
    private  Context mContext;
    private User mUser;

    public RoomListAdapter(List<Room> rooms,Context context){
        mRooms=rooms;
        mContext=context;
        //mUser=user;//todo
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.res_room,parent,false);
        ViewHolder viewHolder=new ViewHolder(rowView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mRooms!=null) {
            Room room = mRooms.get(position);
            holder.Title.setText(room.getTitle());
            holder.Creator.setText(room.getUserName());
            if (room.getIsPrivate())
                holder.Prive.setText("Private");
            else
                holder.Prive.setText("Public");
        }
    }

    @Override
    public int getItemCount() {
        if(mRooms!=null)
            return mRooms.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tvTitle)              TextView Title;
        @BindView(R.id.tvUserCreator)        TextView Creator;
        @BindView(R.id.tvPrivate)            TextView Prive;
        @BindView(R.id.abc)                  CardView abc;
        @BindView(R.id.cba)                  CardView cba;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
