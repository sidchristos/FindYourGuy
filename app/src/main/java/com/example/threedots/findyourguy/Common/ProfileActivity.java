package com.example.threedots.findyourguy.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.threedots.findyourguy.R;

public class ProfileActivity extends AppCompatActivity {
    RatingBar Rb;
    EditText etUsername,etAddressP,etOccupation,etDesc;
    Button exit,save;//Save htan gia edit k save alla xronos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        exit=(Button)findViewById(R.id.BtBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null){
            String userId=(String)b.get("UserId");
            String Username=(String) b.get("UserName");
            String Occupation=(String)b.get("Occupation");
            String Address=(String)b.get("Address");
            String Desc=(String)b.get("Desc");
            long CRating=Long.valueOf((String)b.get("CRating"));
            double  AvgRating=Double.parseDouble((String) b.get("AvgRating"));
            etUsername=(EditText)findViewById(R.id.etUserNameP);
            etAddressP=(EditText)findViewById(R.id.etAddressP);
            etOccupation=(EditText)findViewById(R.id.etOccupationP);
            etDesc=(EditText)findViewById(R.id.etDesc);
            Rb=(RatingBar)findViewById(R.id.ratingBar);
            etUsername.setText(Username);
            etAddressP.setText(Address);
            etOccupation.setText(Occupation);
            etDesc.setText(Desc);

            Rb.setRating((float) (AvgRating/CRating));
        }else{
            finish();
        }
    }


}
