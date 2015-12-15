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
import com.example.germangirod.rxjavaexample.data.presenters.ForecastPresenter;
import com.example.germangirod.rxjavaexample.data.presenters.ForecastWeatherData;
import com.example.germangirod.rxjavaexample.uis.adapters.ForecastListAdapter;
import com.example.germangirod.rxjavaexample.uis.widget.ArrowView;

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
    private String cityId;
    private String windSpeed;
    private String windDegree;
    private String temperature;
    private float degrees;
    private String day;
    private String hours;
    private ForecastListAdapter forecastListAdapter;

    public static Fragment getInstance(String placeId, String windSpeed, String windDregrees, String temperature, float degrees, String day, String hours ) {

        Fragment locationWeatherForecastFragment = new LocationWeatherForecastFragment();

        Bundle arguments = new Bundle();
        arguments.putString("place_id", placeId);
        arguments.putString("wind_speed", windSpeed);
        arguments.putString("wind_degress", windDregrees);
        arguments.putString("temperature", temperature);
        arguments.putFloat("degress", degrees);
        arguments.putString("day", day);
        arguments.putString("hours", hours);
        locationWeatherForecastFragment.setArguments(arguments);

        return locationWeatherForecastFragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forecast_fragment, container, false);
        ButterKnife.inject(this, v);

        getBundleData();
        setToolbar();
        prepareList();
        getForecast(cityId);

        return v;
    }

    private void getBundleData() {
        cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        Bundle arguments = getArguments();
        if (arguments != null) {
            cityId = arguments.getString("place_id");
            windSpeed= arguments.getString("wind_speed");
            windDegree= arguments.getString("wind_degress");
            temperature= arguments.getString("temperature");
            degrees= arguments.getFloat("degress");
            day = arguments.getString("day");
            hours= arguments.getString("hours");

            setNowForecast();

        }
    }

    private void setNowForecast(){

        forecastWindSpeed.setText(windSpeed);
        forecastWindDg.setText(windDegree);
        forecastArrow.setAngleRotation(degrees);
        forecastTemperature.setText(temperature);
        forecastDay.setText(day);
        forecastHours.setText(hours);
        forecastWeatherImage.setImageResource(R.drawable.moon);

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
        forecastListAdapter = new ForecastListAdapter(getActivity(), forecast.getWeatherResponses());
        recyclerView.setAdapter(forecastListAdapter);

        loading.setVisibility(View.GONE);
    }

    @Override public void onError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }
}
