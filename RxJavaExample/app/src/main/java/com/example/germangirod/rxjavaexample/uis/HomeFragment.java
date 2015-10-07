package com.example.germangirod.rxjavaexample.uis;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.WeatherForcastLocation;
import com.example.germangirod.rxjavaexample.api.WeatherForcastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.api.presenters.CurrentWeatherData;
import com.example.germangirod.rxjavaexample.api.presenters.CurrentWeatherPresenter;
import java.util.List;

/**
 * Created by germangirod on 5/13/15.
 */
public class HomeFragment extends LocationFragment implements CurrentWeatherPresenter {

    private WeatherForcastLocation api;
    private HomeAdapter homeAdapter;
    private CurrentWeatherData currentWeatherData;
    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, v);
        api = new WeatherForcastLocationApi();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        homeAdapter = new HomeAdapter(getActivity(), null);

        return v;
    }

    @Override public void onLocationChanged(Location location) {
        currentWeatherData = new CurrentWeatherData();
        currentWeatherData.setView(this);
        currentWeatherData.getCurrentWeather(location);
    }

    @Override public void getCurrentWeather(final List<CurrentWeather> currentWeathers) {

        homeAdapter = new HomeAdapter(getActivity(), currentWeathers);

        recyclerView.setAdapter(homeAdapter);

        homeAdapter.setRowClick(new HomeAdapter.onRowClick() {
            @Override public void clickWeatherRow(View v, int i) {
                HomeDetail.goTo(getActivity(), currentWeathers.get(i));
            }
        });

    }

    @Override public void onError(Throwable throwable) {

    }
}
