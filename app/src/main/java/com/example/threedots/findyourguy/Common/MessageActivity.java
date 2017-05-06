package com.example.threedots.findyourguy.Common;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.threedots.findyourguy.Data.DaoMessage;
import com.example.threedots.findyourguy.Model.Message;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        layoutManager=new LinearLayoutManager(MessageActivity.this);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recMessageHolder);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.requestFocus();
        User user=new User("12345678",3.2,5,"kapoios","ag1","douleuw","kati");
        Room room=new Room("2017131642","KATI","12345678","kapoios",false);
        DaoMessage daoMessage=new DaoMessage(room,user,recyclerView,getApplicationContext());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        daoMessage.SendMessage("yooo");

    }

}
