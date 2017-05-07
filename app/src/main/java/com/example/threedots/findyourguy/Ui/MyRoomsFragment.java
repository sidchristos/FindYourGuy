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

import com.example.threedots.findyourguy.Common.MainActivity;
import com.example.threedots.findyourguy.Core.Adapters. MyRoomsAdapter;
import com.example.threedots.findyourguy.Core.Adapters.roomAdapter;
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
    private ArrayList<Room> tempList=new ArrayList<>();
    private View mRootView;
    private RecyclerView.Adapter mAdapter;
    DaoRoom daoRoom;
    @BindView(R.id.btAddRoom)
    Button mAddRoomm;
    @BindView(R.id.EmptyTextView)
    TextView mEmptyTextView;
    @BindView(R.id.recMyRooms)
    RecyclerView mRecyclerView;

    public MyRoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_my_rooms, container, false);
        ButterKnife.bind(this, mRootView);
/*

        daoRoom=new DaoRoom( new User("kati","kati"),new ListenerOnFinish(){
            @Override
            public void OnFinish(ArrayList<Room> rooms) {
                tempList =rooms;
                mAdapter = new roomAdapter(tempList, new User("kati","kati"),getContext(),daoRoom);
                MainActivity mainActivity=(MainActivity)getActivity();
                mainActivity.setAdapter(mAdapter);
            }
        }, getContext(),false);*/
        return mRootView;
    }
}
