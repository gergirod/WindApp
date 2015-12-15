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
import com.example.germangirod.rxjavaexample.data.api.WeatherForecastLocation;
import com.example.germangirod.rxjavaexample.data.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.data.presenters.MyLocationsCurrentWeatherData;
import com.example.germangirod.rxjavaexample.data.presenters.MyLocationsCurrentWeatherPresenter;
import com.example.germangirod.rxjavaexample.uis.ForecastActivity;
import com.example.germangirod.rxjavaexample.uis.adapters.WeatherListAdapter;

/**
 * Created by germangirod on 5/13/15.
 */
public class MyLocationsWeatherFragment extends Fragment implements MyLocationsCurrentWeatherPresenter {

    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.loading) ProgressBar loading;
    private WeatherForecastLocation api;
    private WeatherListAdapter weatherListAdapter;
    private MyLocationsCurrentWeatherData myLocationsCurrentWeatherData;

    public static Fragment getInstance() {
        MyLocationsWeatherFragment f = new MyLocationsWeatherFragment();
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, v);
        api = new WeatherForecastLocationApi();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getWeatherList();

        return v;
    }

    private void getWeatherList() {
        myLocationsCurrentWeatherData = new MyLocationsCurrentWeatherData();
        myLocationsCurrentWeatherData.setView(this);
        myLocationsCurrentWeatherData.getMyCurrentWeatherList("3435910");
    }

    @Override public void getCurrentWeather(final CurrentWeather currentWeathers) {
        weatherListAdapter = new WeatherListAdapter(getActivity(), currentWeathers.getWeatherResponse());

        recyclerView.setAdapter(weatherListAdapter);
        loading.setVisibility(View.GONE);

        weatherListAdapter.setRowClick(new WeatherListAdapter.onRowClick() {
            @Override public void clickWeatherRow(View v, int i) {
                ForecastActivity.goTo(getActivity(), String.valueOf(currentWeathers.getWeatherResponse().get(i).getId()),
                        currentWeathers.getWeatherResponse().get(i).getWind().getSpeed(), currentWeathers.getWeatherResponse().get(i).getWind().degToString(),
                        currentWeathers.getWeatherResponse().get(i).getMain().getTemp(), currentWeathers.getWeatherResponse().get(i).getWind().getDeg(),
                        currentWeathers.getWeatherResponse().get(i).getDay(), currentWeathers.getWeatherResponse().get(i).getHours());
            }
        });
    }

    @Override public void onError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }
}
