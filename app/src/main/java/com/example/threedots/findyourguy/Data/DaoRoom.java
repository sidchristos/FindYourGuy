package com.example.threedots.findyourguy.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.threedots.findyourguy.Common.MainActivity;
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
    private ArrayList<Room> rooms=new ArrayList<>();
    private Context context;
    private User user;
    private Boolean ShowOnMine;
    private RecyclerView recyclerView;
    private DatabaseReference roomsRef;
    private static final String TAG = MainActivity.class.getSimpleName();
    public DaoRoom(User user,RecyclerView recyclerView, Context context,Boolean ShowOnMine){
        this.context=context;
        this.user=user;
        this.ShowOnMine=ShowOnMine;
        roomsRef = FirebaseDatabase.getInstance().getReference()
                .child("ChatRooms");
        this.recyclerView=recyclerView;
        roomsRef.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            ArrayList<Room> temp=new ArrayList<>();
            rooms.clear();
            Room room;
            String ID,Title,UIDCreator,UserName,Password;
            boolean IsPrivate;
            for(DataSnapshot data:dataSnapshot.getChildren()){
                ID=data.getKey();
                Title=data.child("Title").getValue(String.class);
                UIDCreator=data.child("UIDCreator").getValue(String.class);
                UserName=data.child("UserName").getValue(String.class);
                IsPrivate=data.child("IsPrivate").getValue(Boolean.class);
                if(IsPrivate){
                    Password=data.child("Password").getValue(String.class);
                    room=new Room(ID,Title,UIDCreator,UserName, true,Password);
                }else{
                    room=new Room(ID,Title,UIDCreator,UserName, false," ");
                }
                temp.add(room);
            }
            int i;
            for(i=temp.size()-1;i<-1;i=i-1){
                if(ShowOnMine&& temp.get(i).getUIDCreator().equals(user.getUserId())){
                    rooms.add(temp.get(i));
                }else if(!ShowOnMine) {
                    rooms.add(temp.get(i));
                }
            }
            RecyclerView.Adapter adapter = new roomAdapter(rooms, user, context,this);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(0);
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
        roomsRef.child(ID).child("IsPrivate").setValue(IsPrivate);
        if(IsPrivate)
            roomsRef.child(ID).child("Password").setValue(Password);
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
