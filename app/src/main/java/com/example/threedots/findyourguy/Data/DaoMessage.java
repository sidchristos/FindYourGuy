package com.example.threedots.findyourguy.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.threedots.findyourguy.Common.MessageActivity;
import com.example.threedots.findyourguy.Core.Adapters.messageAdapter;
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


public class DaoMessage implements ValueEventListener {

    private static final String TAG = MessageActivity.class.getSimpleName();
    Context ctn;
    private final User user;
    private ArrayList<Message> messageListArray=new ArrayList<>();
    private DatabaseReference messagesRef;
    private RecyclerView recyclerView;

    public DaoMessage(Room room,User user,RecyclerView recyclerView,Context ctn){
        this.ctn=ctn;
        this.user=user;
        messagesRef = FirebaseDatabase.getInstance().getReference()
                .child("ChatRooms").child(room.getID()).child("messages");
        messagesRef.addValueEventListener(this);
        this.recyclerView=recyclerView;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot!=null){
            messageListArray.clear();
            Message message;
            String ID;
            String UIDSender;
            String Message,UserName;
            for (DataSnapshot data: dataSnapshot.getChildren()) {
                ID= data.getKey();
                UIDSender=data.child("UIDSender").getValue(String.class);
                Log.d(TAG,"UIDSender:"+UIDSender);
                UserName=data.child("UserName").getValue(String.class);
                Log.d(TAG,"UserName:"+UserName);
                Message=data.child("Message").getValue(String.class);
                Log.d(TAG,"Message:"+Message);
                message=new Message(ID,UserName,UIDSender,Message);
                messageListArray.add(message);
            }
            RecyclerView.Adapter adapter = new messageAdapter(messageListArray, user, ctn);
            recyclerView.setAdapter(adapter);
            recyclerView.scrollToPosition(messageListArray.size()-1);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {}

    public void SendMessage(String message) {
        SimpleDateFormat sdf;
        Date now = new Date();
        sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
        String key = sdf.format(now);
        messagesRef.child(key).child("UIDSender").setValue(user.getUserId());
        messagesRef.child(key).child("UserName").setValue(user.getName());
        messagesRef.child(key).child("Message").setValue(message);
    }
}
