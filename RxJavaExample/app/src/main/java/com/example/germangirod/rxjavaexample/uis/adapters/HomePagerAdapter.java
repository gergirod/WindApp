package com.example.germangirod.rxjavaexample.uis.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.germangirod.rxjavaexample.uis.fragments.CurrentLocationWeatherFragment;
import com.example.germangirod.rxjavaexample.uis.fragments.MyLocationsWeatherFragment;

/**
 * Created by germangirod on 10/15/15.
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs=2;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment= null;
        if(position==0){
            fragment = CurrentLocationWeatherFragment.getInstance();
        }else{
            fragment = MyLocationsWeatherFragment.getInstance();
        }
        return fragment;
    }

    @Override public int getCount() {
        return numOfTabs;
    }
}
