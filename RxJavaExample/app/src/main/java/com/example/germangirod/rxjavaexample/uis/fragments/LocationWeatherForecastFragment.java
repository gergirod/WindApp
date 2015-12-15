package com.example.germangirod.rxjavaexample.uis.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.Forecast;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.data.presenters.ForecastPresenter;
import com.example.germangirod.rxjavaexample.data.presenters.ForecastWeatherData;
import com.example.germangirod.rxjavaexample.uis.adapters.ForecastListAdapter;
import com.example.germangirod.rxjavaexample.uis.widget.ArrowView;
import com.example.germangirod.rxjavaexample.util.WeatherImageUtil;
import org.parceler.Parcels;

/**
 * Created by germangirod on 10/19/15.
 */
public class LocationWeatherForecastFragment extends Fragment implements ForecastPresenter {

    @InjectView(R.id.my_recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.loading) ProgressBar loading;
    @InjectView(R.id.card_view) CardView cardView;
    @InjectView(R.id.forecast_day) TextView forecastDay;
    @InjectView(R.id.forecast_hours) TextView forecastHours;
    @InjectView(R.id.forecast_arrow_wind) ArrowView forecastArrow;
    @InjectView(R.id.forecast_temperature) TextView forecastTemperature;
    @InjectView(R.id.forecast_weather_image) ImageView forecastWeatherImage;
    @InjectView(R.id.forecast_wind_speed) TextView forecastWindSpeed;
    @InjectView(R.id.forecast_wind_dg) TextView forecastWindDg;
    private ForecastWeatherData forecastWeatherData;
    private ForecastListAdapter forecastListAdapter;
    private WeatherResponse cityWeatherResponse;

    public static Fragment getInstance(WeatherResponse weatherResponse) {

        Fragment locationWeatherForecastFragment = new LocationWeatherForecastFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable("weather_response", Parcels.wrap(weatherResponse));
        locationWeatherForecastFragment.setArguments(arguments);

        return locationWeatherForecastFragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forecast_fragment, container, false);
        ButterKnife.inject(this, v);

        getBundleData();
        setToolbar();
        prepareList();
        getForecast(String.valueOf(cityWeatherResponse.getId()));

        return v;
    }

    private void getBundleData() {
        cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        Bundle arguments = getArguments();
        if (arguments != null) {
            cityWeatherResponse = Parcels.unwrap(arguments.getParcelable("weather_response"));
            setNowForecast();

        }
    }

    private void setNowForecast(){

        forecastWindSpeed.setText(cityWeatherResponse.getWind().getSpeed());
        forecastWindDg.setText(cityWeatherResponse.getWind().degToString());
        forecastArrow.setAngleRotation(cityWeatherResponse.getWind().getDeg());
        forecastTemperature.setText(cityWeatherResponse.getMain().getTemp());
        forecastDay.setText(cityWeatherResponse.getDay());
        forecastHours.setText(cityWeatherResponse.getHours());

        WeatherImageUtil weatherImageUtil = new WeatherImageUtil(cityWeatherResponse);

        forecastWeatherImage.setImageResource(weatherImageUtil.setWeatherImage());

    }

    private void prepareList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setToolbar(){

        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar =  ((ActionBarActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

    }

    private void getForecast(String cityId) {

        forecastWeatherData = new ForecastWeatherData();
        forecastWeatherData.setView(this);
        forecastWeatherData.getForecastByCityId(cityId);
    }

    @Override public void getForecastByCityId(Forecast forecast) {
        collapsingToolbarLayout.setTitle(forecast.getCity().getName()+", "+forecast.getCity().getCountry());
        forecastListAdapter = new ForecastListAdapter(getActivity(), forecast.getWeatherResponses(), cityWeatherResponse);
        recyclerView.setAdapter(forecastListAdapter);

        loading.setVisibility(View.GONE);
    }

    @Override public void onError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }
}
