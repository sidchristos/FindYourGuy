package com.example.threedots.findyourguy.Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.threedots.findyourguy.Common.MainActivity;
import com.example.threedots.findyourguy.Common.MessageActivity;
import com.example.threedots.findyourguy.Common.ViewPagerAdapter;
import com.example.threedots.findyourguy.Data.DaoRoom;
import com.example.threedots.findyourguy.R;


public class RoomsListFragment extends Fragment {
    private View mRootView;


    public RoomsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_rooms_list, container, false);
        MainActivity mainActivity=(MainActivity)getActivity();
        RecyclerView recyclerView=(RecyclerView) container.findViewById(R.id.recAllRooms);
        recyclerView.setLayoutManager(mainActivity.layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.requestFocus();
        DaoRoom daoRoom=new DaoRoom(mainActivity.user,recyclerView,mainActivity.getApplicationContext(),false);

        return mRootView;
    }

}
