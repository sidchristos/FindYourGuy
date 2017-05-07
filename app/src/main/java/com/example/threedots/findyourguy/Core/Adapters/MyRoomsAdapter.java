package com.example.threedots.findyourguy.Core.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRoomsAdapter extends RecyclerView.Adapter< MyRoomsAdapter.ViewHolder> {
    private  List<Room> mRooms;//todo room/message
    private  Context mContext;
    private User mUser;

    public MyRoomsAdapter(List<Room> rooms, Context context){
        mRooms=rooms;
        mContext=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_room,parent,false);
        ViewHolder viewHolder=new ViewHolder(rowView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mRooms!=null) {
            final Room roomm = mRooms.get(position);
            holder.Title.setText(roomm.getTitle());
            holder.Creator.setText(roomm.getUserName());
            if (roomm.getIsPrivate())
                holder.Prive.setText("Private");
            else
                holder.Prive.setText("Public");
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToMessages(roomm);
                }
            });
        }
    }

    private void goToMessages(Room room) {
        MainActivity mainActivity=(MainActivity)mContext;
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
            mContext.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        if(mRooms!=null)
            return mRooms.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.roomContainer)        LinearLayout container;
        @BindView(R.id.tvTitle)              TextView Title;
        @BindView(R.id.tvUserCreator)        TextView Creator;
        @BindView(R.id.tvPrivate)            TextView Prive;
        @BindView(R.id.abc)                  CardView abc;
        @BindView(R.id.cba)                  CardView cba;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
