package com.example.threedots.findyourguy.Common;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.threedots.findyourguy.Ui.MyRoomsFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public final MainActivity mainActivity;

    public ViewPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
        this.mainActivity=mainActivity;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                mainActivity.MyRoomTab.setVisibility(View.VISIBLE);
                mainActivity.AllRoomsTab.setVisibility(View.GONE);
                mainActivity.Contact.setVisibility(View.GONE);
                break;
            case 1:
                mainActivity.MyRoomTab.setVisibility(View.GONE);
                mainActivity.AllRoomsTab.setVisibility(View.GONE);
                mainActivity.Contact.setVisibility(View.VISIBLE);
                break;
            case 2:
                mainActivity.MyRoomTab.setVisibility(View.GONE);
                mainActivity.AllRoomsTab.setVisibility(View.VISIBLE);
                mainActivity.Contact.setVisibility(View.GONE);
                break;
            default:
                mainActivity.MyRoomTab.setVisibility(View.VISIBLE);
                mainActivity.AllRoomsTab.setVisibility(View.GONE);
                mainActivity.Contact.setVisibility(View.GONE);
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "My rooms";
                break;
            case 1:
                title = "Contacts";
                break;
            case 2:
                title = "Explore";
                break;
        }
        return title;
    }
}
