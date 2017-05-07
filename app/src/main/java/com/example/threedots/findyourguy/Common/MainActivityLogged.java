package com.example.threedots.findyourguy.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.threedots.findyourguy.Core.Adapters.roomAdapter;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Listeners.ListenerOnFinish;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivityLogged extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView mTextMessage;
    public ConstraintLayout MyRoomTab,AllRoomsTab,Contact;
    public RecyclerView.LayoutManager layoutManager;
    private TextView textViewUsername;
    private User user;
    RecyclerView mRecyclerView;
    RecyclerView recAllRooms;
    private DaoRoom daoRoom;
    private DaoRoom daoRoomForAll;
    private ArrayList<Room> tempList;
    private Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MyRoomTab.setVisibility(View.GONE);
                    AllRoomsTab.setVisibility(View.GONE);
                    Contact.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    MyRoomTab.setVisibility(View.VISIBLE);
                    AllRoomsTab.setVisibility(View.GONE);
                    Contact.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    MyRoomTab.setVisibility(View.GONE);
                    AllRoomsTab.setVisibility(View.VISIBLE);
                    Contact.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logged);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mAuth = FirebaseAuth.getInstance();

        context=getApplicationContext();
        textViewUsername=(TextView)findViewById(R.id.textViewUsername);
        MyRoomTab=(ConstraintLayout) findViewById(R.id.MyRoom);
        AllRoomsTab=(ConstraintLayout)findViewById(R.id.AllRoom);
        Contact=(ConstraintLayout)findViewById(R.id.Contact);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    init(new User(user.getUid(),user.getDisplayName()));
                } else {
                    finish();
                }
            }
        };
    }

    private void init(User user) {
        textViewUsername.setText("Logged as:"+user.getName());
        mRecyclerView=(RecyclerView)findViewById(R.id.recAllRooms);
        recAllRooms=(RecyclerView)findViewById(R.id.recMyRooms);

        layoutManager=new LinearLayoutManager(MainActivityLogged.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        daoRoom=new DaoRoom(user,mRecyclerView,context,false);

        layoutManager=new LinearLayoutManager(MainActivityLogged.this);
        recAllRooms.setLayoutManager(layoutManager);
        recAllRooms.setHasFixedSize(true);
        daoRoomForAll=new DaoRoom(user,recAllRooms,context,true);
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
