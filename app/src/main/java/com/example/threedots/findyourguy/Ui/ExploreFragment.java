package com.example.threedots.findyourguy.Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.threedots.findyourguy.Common.MainActivity;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.R;

import butterknife.BindView;


public class ExploreFragment extends Fragment {
    private View mRootView;

    @BindView(R.id.recMyRooms) RecyclerView mRecyclerView;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_rooms_list, container, false);

        return mRootView;
    }

}
