package com.example.germangirod.rxjavaexample.uis.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;

/**
 * Created by germangirod on 10/19/15.
 */
public class LocationWeatherForecastFragment extends Fragment {

    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.loading) ProgressBar loading;

    public static Fragment getInstance(String placeId){

        Fragment locationWeatherForecastFragment = new LocationWeatherForecastFragment();

        Bundle arguments = new Bundle();
        arguments.putString("place_id", placeId);
        locationWeatherForecastFragment.setArguments(arguments);

        return locationWeatherForecastFragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, v);

        Bundle arguments = getArguments();
        if(arguments != null)
        {
            //handleArguments(arguments);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
