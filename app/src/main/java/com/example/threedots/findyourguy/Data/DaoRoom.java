package com.example.threedots.findyourguy.Data;

import android.content.Context;

import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Cake on 6/5/2017.
 */

public class DaoRoom {
    private ArrayList<Room> rooms=new ArrayList<>();
    private ArrayList<Room> myRooms=new ArrayList<>();
    private Context context;
    private User user;
    private DatabaseReference roomsRef;
    public DaoRoom(User user, Context context){
        this.context=context;
        this.user=user;
        roomsRef = FirebaseDatabase.getInstance().getReference()
                .child("ChatRooms");
    }
}
