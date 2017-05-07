package com.example.threedots.findyourguy.Ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.threedots.findyourguy.Core.Adapters.RoomListAdapter;
import com.example.threedots.findyourguy.Model.Room;
import com.example.threedots.findyourguy.Model.User;
import com.example.threedots.findyourguy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyRoomsFragment extends Fragment {

    private View mRootView;
    private RoomListAdapter mAdapter;

    @BindView(R.id.recMyRooms) RecyclerView mRecyclerView;
    @BindView(R.id.btAddRoom)  Button mAddRoomn;


    public MyRoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my_rooms, container, false);
        ButterKnife.bind(this, mRootView);

        //setup RecyclerView
        List<Room> tempList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new RoomListAdapter(tempList, getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        if (tempList.size()<1)
            showEmptyTextMessage();
        else
            hideEmptyTextMessage();
        return mRootView;
    }

    private void hideEmptyTextMessage() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showEmptyTextMessage() {
        mRecyclerView.setVisibility(View.GONE);
    }
}
