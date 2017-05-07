package com.example.threedots.findyourguy.Data;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Cake on 7/5/2017.
 */

public class DaoUser {
    private final Context context;
    private final String userId;
    public DaoUser(Context context, String userId) {
        this.context=context;
        this.userId=userId;
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    dataSnapshot.child("UserName").getValue(String.class);
                    dataSnapshot.child("Occupation").getValue(String.class);
                    dataSnapshot.child("Address").getValue(String.class);
                    dataSnapshot.child("Desc").getValue(String.class);
                    dataSnapshot.child("CRating").getValue(long.class);
                    dataSnapshot.child("AvgRating").getValue(double.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
