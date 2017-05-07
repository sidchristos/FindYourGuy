package com.example.threedots.findyourguy.Common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.threedots.findyourguy.Data.DaoMessage;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MessageActivity extends AppCompatActivity implements View.OnKeyListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView TitleRoom,athorRoom;
    DaoMessage daoMessage;
    EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        TitleRoom=(TextView)findViewById(R.id.tvTitleRoom) ;
        athorRoom=(TextView)findViewById(R.id.tvUserNameRoom) ;
        etMessage=(EditText)findViewById(R.id.etMessage);
        etMessage.setOnKeyListener(this);
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
                        if(UserName!=null && Title!=null){
                            athorRoom.setText(UserName);
                            TitleRoom.setText(Title);
                        }
                        String UIDCreator =(String)b.get("UIDCreator");
                        Room room=new Room(ID,Title,UIDCreator,UserName,false);
                        User user=new User(userFb.getUid(),userFb.getDisplayName());
                        daoMessage=new DaoMessage(room,user,recyclerView,getApplicationContext());
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

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            daoMessage.SendMessage(etMessage.getText().toString());
            etMessage.setText("");
            return true;
        }
        return false;
    }
}
