package com.example.threedots.findyourguy.Common;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.threedots.findyourguy.Core.Adapters.roomAdapter;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Data.DaoUser;
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
    public ConstraintLayout MyRoomTab,AllRoomsTab,Contact;
    public RecyclerView.LayoutManager layoutManager;
    private User user;
    RecyclerView mRecyclerView;
    RecyclerView recAllRooms;
    private Context context;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Contacts:
                    MyRoomTab.setVisibility(View.GONE);
                    AllRoomsTab.setVisibility(View.GONE);
                    Contact.setVisibility(View.VISIBLE);
                    return true;
                case R.id.My_Rooms:
                    MyRoomTab.setVisibility(View.VISIBLE);
                    AllRoomsTab.setVisibility(View.GONE);
                    Contact.setVisibility(View.GONE);
                    return true;
                case R.id.Explore:
                    MyRoomTab.setVisibility(View.GONE);
                    AllRoomsTab.setVisibility(View.VISIBLE);
                    Contact.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };
    private DaoRoom daoRoom,daoRoomForAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logged);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mAuth = FirebaseAuth.getInstance();

        context=getApplicationContext();
        MyRoomTab=(ConstraintLayout) findViewById(R.id.MyRoom);
        AllRoomsTab=(ConstraintLayout)findViewById(R.id.AllRoom);
        Contact=(ConstraintLayout)findViewById(R.id.Contact);

        ImageView img =(ImageView)findViewById(R.id.imageView3) ;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog();
            }
        });

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
        this.user=user;
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

    private void dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_main_menu);
        Button ButtonUsrProf = (Button) dialog.findViewById(R.id.btn_user_profile);
        Button ButtonSettings = (Button) dialog.findViewById(R.id.btn_settings);
        Button ButtonLogout = (Button) dialog.findViewById(R.id.btn_logout);
        ButtonUsrProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoUser daoUser=new DaoUser(getApplicationContext(),user.getUserId());
                dialog.dismiss();
            }
        });
        ButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Settings not implemented yet" , Toast.LENGTH_SHORT ).show();
                dialog.dismiss();
            }
        });
        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivityLogged.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
}
