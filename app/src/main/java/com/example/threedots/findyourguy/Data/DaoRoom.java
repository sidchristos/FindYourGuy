package com.example.threedots.findyourguy.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.Core.Adapters.roomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Cake on 6/5/2017.
 */

public class DaoRoom implements ValueEventListener {
    private final RecyclerView recyclerView;
    private ArrayList<Room> rooms=new ArrayList<>();
    private ArrayList<Room> temp=new ArrayList<>();
    private Context context;
    private User user;
    private Boolean ShowOnMine;
    private DatabaseReference roomsRef;
    Room room;
    String ID,Title,UIDCreator,UserName;
    public DaoRoom(User user,RecyclerView recyclerView, Context context,Boolean ShowOnMine){
        this.context=context;
        this.user=user;
        this.ShowOnMine=ShowOnMine;
        this.recyclerView=recyclerView;
        roomsRef = FirebaseDatabase.getInstance().getReference()
                .child("ChatRooms");
        roomsRef.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            temp.clear();
            rooms.clear();
            for(DataSnapshot data:dataSnapshot.getChildren()){

                ID=data.getKey();
                Title=data.child("Title").getValue(String.class);
                UIDCreator=data.child("UIDCreator").getValue(String.class);
                UserName=data.child("UserName").getValue(String.class);
                room=new Room(ID,Title,UIDCreator,UserName, false," ");
                if(!ShowOnMine)
                    rooms.add(room);
                else if(UIDCreator.equals(user.getUserId()))
                    rooms.add(room);
            }
            RecyclerView.Adapter ad=new roomAdapter(rooms,user,context,this);
            recyclerView.setAdapter(ad);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void createRoom(String Title,Boolean IsPrivate,String Password){
        SimpleDateFormat sdf;
        Date now = new Date();
        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
        String ID = sdf.format(now);
        roomsRef.child(ID).child("UIDCreator").setValue(user.getUserId());
        roomsRef.child(ID).child("UserName").setValue(user.getName());
        roomsRef.child(ID).child("Title").setValue(Title);
    }

    public void DeleteRoom(final String Key){
        roomsRef.child(Key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                if(data!=null&&data.child("UIDCreator").getValue(String.class).equals(user.getUserId())){
                    data.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
