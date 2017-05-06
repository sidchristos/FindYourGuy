package com.example.threedots.findyourguy.Data;

import android.support.v7.widget.RecyclerView;

import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
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
 * Created by Stef on 06-May-17.
 */

public class DaoMessage implements ValueEventListener {
    private final Room room;
    private final User user;
    private ArrayList<Message> messageListArray;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference messagesRef;
    private DatabaseReference roomRef;
    private RecyclerView recyclerView;

    DaoMessage(Room room,User user){
        messageListArray=new ArrayList<>();
        this.room=room;
        this.user=user;
        roomRef = database.getReference().child(Long.toString(room.getID()));
        messagesRef = roomRef.child("messages");
        messagesRef.addValueEventListener(this);
        this.recyclerView=recyclerView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            messageListArray.clear();
            Message message;
            long ID;
            long UIDSender;
            String Message,UserName;
            for (DataSnapshot data: dataSnapshot.getChildren()) {
                ID= Long.valueOf(data.getKey()).longValue();
                UIDSender=data.child("UIDSender").getValue(Long.class);
                UserName=data.child("UserName").getValue(String.class);
                Message=data.child("Message").getValue(String.class);
                message=new Message(ID,UserName,UIDSender,Message);
                messageListArray.add(message);
            }
            
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {}

    public void SendMessage(Message message) {
        SimpleDateFormat sdf;
        Date now = new Date();
        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
        String key = sdf.format(now);
        messagesRef.child(key).child("UIDSender").setValue(user.getUserId());
        messagesRef.child(key).child("UserName").setValue(user.getName());
        messagesRef.child(key).child("Message").setValue(message);
    }
}
