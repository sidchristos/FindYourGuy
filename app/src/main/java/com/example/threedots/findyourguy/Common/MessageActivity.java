package com.example.threedots.findyourguy.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.threedots.findyourguy.Data.DaoMessage;
import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userFb = firebaseAuth.getCurrentUser();
                if (userFb != null) {
                    Intent iin= getIntent();
                    Bundle b = iin.getExtras();
                    if(b!=null)
                    {
                        RecyclerView.LayoutManager layoutManager;
                        layoutManager=new LinearLayoutManager(MessageActivity.this);
                        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recMessageHolder);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.requestFocus();
                        String ID =(String)b.get("ID");
                        String Title =(String)b.get("Title");
                        String UserName =(String)b.get("UserName");
                        String UIDCreator =(String)b.get("UIDCreator");
                        Room room=new Room(ID,Title,UIDCreator,UserName,false);
                        User user=new User(userFb.getUid(),userFb.getDisplayName());
                        DaoMessage daoMessage=new DaoMessage(room,user,recyclerView,getApplicationContext());
                    }else{
                        finish();
                    }
                } else {
                    finish();
                }
            }
        };

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
