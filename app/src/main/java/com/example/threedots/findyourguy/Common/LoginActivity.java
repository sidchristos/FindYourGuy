package com.example.threedots.findyourguy.Common;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.threedots.findyourguy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.password;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Boolean FirstTime;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ConstraintLayout initialMenu,loginMenu,SignUp;
    EditText LoginEmail,LoginPassword,etNewUserName,etNewEmail,etPassword,etOccupation,etAddress;
    Button btSignUP,btLogin,RegisterNext,LoginNext;
    ImageView BackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        FirstTime=false;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if(FirstTime){
                        DatabaseReference myRefUser = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child(user.getUid());
                        myRefUser.child("UserName").setValue(etNewUserName.getText().toString());
                        myRefUser.child("Occupation").setValue(etOccupation.getText().toString());
                        myRefUser.child("Address").setValue(etAddress.getText().toString());
                        myRefUser.child("CRating").setValue(0);
                        myRefUser.child("AvgRating").setValue(0);
                    }
                    goToMain(user.getUid(),etNewUserName.getText().toString());
                }
            }
        };

        BackButton=(ImageView)findViewById(R.id.imageView2);
        initialMenu=(ConstraintLayout)findViewById(R.id.InitialMenu);
        SignUp=(ConstraintLayout)findViewById(R.id.SignUpMenu);
        loginMenu=(ConstraintLayout)findViewById(R.id.LoginMenu);

        btSignUP=(Button)findViewById(R.id.btSignUp) ;
        btLogin=(Button)findViewById(R.id.btLogin) ;
        RegisterNext=(Button)findViewById(R.id.RegisterNext);
        LoginNext=(Button)findViewById(R.id.LoginNext);

        LoginEmail=(EditText)findViewById(R.id.LoginEmail);
        LoginPassword=(EditText)findViewById(R.id.LoginPassword);

        etNewUserName=(EditText)findViewById(R.id.etNewUserName);
        etNewEmail=(EditText)findViewById(R.id.etNewEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etOccupation=(EditText)findViewById(R.id.etOccupation);
        etAddress=(EditText)findViewById(R.id.etAddress);

        init();

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        btSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackButton.setVisibility(View.VISIBLE);
                SignUp.setVisibility(View.VISIBLE);
                initialMenu.setVisibility(View.GONE);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackButton.setVisibility(View.VISIBLE);
                loginMenu.setVisibility(View.VISIBLE);
                initialMenu.setVisibility(View.GONE);
            }
        });

        LoginNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstTime=false;
                mAuth.signInWithEmailAndPassword(LoginEmail.getText().toString(),LoginPassword.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,"Auth failed",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    init();
                            }
                        });
            }
        });

        RegisterNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstTime=true;
                mAuth.createUserWithEmailAndPassword(etNewEmail.getText().toString(), etPassword.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,"Auth failed",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    init();
                            }
                        });
            }
        });

    }

    private void goToMain(String uid, String s) {
        Intent i=new Intent(LoginActivity.this,MainActivity.class);
        i.putExtra("UID",uid);
        i.putExtra("UserName",s);
        startActivity(i);
    }

    private void init(){
        SignUp.setVisibility(View.GONE);
        loginMenu.setVisibility(View.GONE);
        initialMenu.setVisibility(View.VISIBLE);
        BackButton.setVisibility(View.GONE);

        LoginEmail.setText("");
        LoginPassword.setText("");
        etNewUserName.setText("");
        etNewEmail.setText("");
        etPassword.setText("");
        etOccupation.setText("");
        etAddress.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
