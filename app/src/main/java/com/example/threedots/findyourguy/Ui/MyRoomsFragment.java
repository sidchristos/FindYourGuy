package com.example.threedots.findyourguy.Ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.threedots.findyourguy.Core.Adapters. MyRoomsAdapter;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.Listeners.ListenerOnFinish;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRoomsFragment extends Fragment {
    private ArrayList<Room> tempList;
    private View mRootView;
    private MyRoomsAdapter mAdapter;

    @BindView(R.id.recMyRooms)
    RecyclerView mRecyclerView;
    @BindView(R.id.btAddRoom)
    Button mAddRoomm;
    @BindView(R.id.EmptyTextView)
    TextView mEmptyTextView;


    public MyRoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my_rooms, container, false);
        ButterKnife.bind(this, mRootView);

        //setup RecyclerView
        ListenerOnFinish listenerOnFinish=new ListenerOnFinish(){
            @Override
            public void OnFinish(ArrayList<Room> rooms) {
                tempList =rooms;
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                mAdapter = new MyRoomsAdapter(tempList, getContext());
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.setLayoutManager(layoutManager);
            }
        };

        DaoRoom daoRoom=new DaoRoom( new User("kati","kati"),listenerOnFinish, getContext(),false);
        return mRootView;
    }



    private void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mEmptyTextView.setVisibility(View.GONE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);
    }
}
