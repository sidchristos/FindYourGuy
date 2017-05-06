package com.example.threedots.findyourguy.Common;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    public User user=new User("12345678",3.2,5,"kapoios","ag1","douleuw","kati");
    public RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        layoutManager=new LinearLayoutManager(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupViewPager();
    }

    @OnClick(R.id.btn_Set_Main)
    public void onClick() {
        dialog();
    }

    private void setupViewPager(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
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
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}

