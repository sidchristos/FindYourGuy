package com.example.threedots.findyourguy.Data;

import android.content.Context;
import android.content.Intent;

import com.example.threedots.findyourguy.Common.ProfileActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Cake on 7/5/2017.
 */

public class DaoUser implements ValueEventListener {
    private final Context context;
    private final String userId;
    public DaoUser(Context context, String userId) {
        this.context=context;
        this.userId=userId;
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Intent i=new Intent(context, ProfileActivity.class);
        String Username= dataSnapshot.child("UserName").getValue(String.class);
        String Occupation= dataSnapshot.child("Occupation").getValue(String.class);
        String Address= dataSnapshot.child("Address").getValue(String.class);
        String Desc= dataSnapshot.child("Desc").getValue(String.class);
        long CRating= dataSnapshot.child("CRating").getValue(long.class);
        double AvgRating= dataSnapshot.child("AvgRating").getValue(double.class );

        i.putExtra("UserId",userId);
        i.putExtra("UserName",Username);
        i.putExtra("Occupation",Occupation);
        i.putExtra("Address",Address);
        i.putExtra("Desc",Desc);
        i.putExtra("CRating", String.valueOf(CRating));
        i.putExtra("AvgRating",Double.toString(AvgRating));
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
