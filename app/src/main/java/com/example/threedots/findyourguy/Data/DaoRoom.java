package com.example.threedots.findyourguy.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Cake on 6/5/2017.
 */

public class DaoRoom implements ValueEventListener {
    private ArrayList<Room> rooms=new ArrayList<>();
    private Context context;
    private User user;
    private int Code;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private DatabaseReference roomsRef;

    public DaoRoom(User user,RecyclerView recyclerView, Context context,int Code){
        this.context=context;
        this.user=user;
        this.Code=Code;
        roomsRef = FirebaseDatabase.getInstance().getReference()
                .child("ChatRooms");
        if(Code>=0&&Code<3){
            this.recyclerView=recyclerView;
            roomsRef.addValueEventListener(this);
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            rooms.clear();
            Room room;
            String ID,Title,UIDCreator,UserName;
            boolean IsPrivate;
            for(DataSnapshot data:dataSnapshot.getChildren()){
                ID=data.getKey();
                Title=data.child("").getValue(String.class);
            }
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
