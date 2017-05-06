package com.example.threedots.findyourguy.Common;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.threedots.findyourguy.Ui.MyRoomsFragment;
import com.example.threedots.findyourguy.Ui.RoomsListFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public final MainActivity mainActivity;

    public ViewPagerAdapter(FragmentManager fm, MainActivity mainActivity) {
        super(fm);
        this.mainActivity=mainActivity;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        android.support.v4.app.Fragment selectedFragment;
        switch (position) {
            case 0:
                selectedFragment = new MyRoomsFragment();
                break;
            /*case 1:
                selectedFragment = new ;//TODO tab fragments
                break;*/
            case 2:
                selectedFragment = new RoomsListFragment();
                break;
            default:
                selectedFragment = new MyRoomsFragment();
                break;
        }
        return selectedFragment;
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
