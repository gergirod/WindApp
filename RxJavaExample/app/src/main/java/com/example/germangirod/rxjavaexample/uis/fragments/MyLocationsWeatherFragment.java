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
import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.data.presenters.MyLocationsCurrentWeatherData;
import com.example.germangirod.rxjavaexample.data.presenters.MyLocationsCurrentWeatherPresenter;
import com.example.germangirod.rxjavaexample.data.storage.MyLocationDBManager;
import com.example.germangirod.rxjavaexample.uis.ForecastActivity;
import com.example.germangirod.rxjavaexample.uis.adapters.WeatherListAdapter;
import org.parceler.Parcels;

/**
 * Created by germangirod on 5/13/15.
 */
public class MyLocationsWeatherFragment extends Fragment implements MyLocationsCurrentWeatherPresenter, WeatherListAdapter.onRowClick {

    private static final String SAVE_CURRENT_WEATHER_RESPONSE_STATE = "current_weather_response_state";
    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.loading) ProgressBar loading;
    private WeatherListAdapter weatherListAdapter;
    private MyLocationsCurrentWeatherData myLocationsCurrentWeatherData;
    private CurrentWeather currentWeather;

    public static Fragment getInstance() {
        MyLocationsWeatherFragment f = new MyLocationsWeatherFragment();
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, v);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            currentWeather = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_CURRENT_WEATHER_RESPONSE_STATE));

            getCurrentWeather(currentWeather);
        }


        return v;
    }

    @Override public void onResume() {
        super.onResume();
        getWeatherList();
    }

    private void getWeatherList() {
        myLocationsCurrentWeatherData = new MyLocationsCurrentWeatherData();
        myLocationsCurrentWeatherData.setView(this);
        MyLocationDBManager myLocationDBManager = new MyLocationDBManager(getActivity());
        String citiesIds= myLocationDBManager.getCityIds();
        myLocationsCurrentWeatherData.getMyCurrentWeatherList(citiesIds);
    }

    @Override public void getCurrentWeather(final CurrentWeather currentWeathers) {
        currentWeather = currentWeathers;
        weatherListAdapter = new WeatherListAdapter(getActivity(), currentWeather.getWeatherResponse());
        weatherListAdapter.setRowClick(this);

        recyclerView.setAdapter(weatherListAdapter);
        loading.setVisibility(View.GONE);
    }

    @Override public void onError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVE_CURRENT_WEATHER_RESPONSE_STATE, Parcels.wrap(currentWeather));
    }

    @Override public void clickWeatherRow(View v, int i) {
        ForecastActivity.goTo(getActivity(), currentWeather.getWeatherResponse().get(i));
    }
}
