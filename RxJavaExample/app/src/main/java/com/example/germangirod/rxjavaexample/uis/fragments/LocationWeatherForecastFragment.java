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
import android.view.MenuItem;
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
import com.example.germangirod.rxjavaexample.data.storage.MyLocationDBManager;
import com.example.germangirod.rxjavaexample.data.storage.StorageData;
import com.example.germangirod.rxjavaexample.uis.adapters.ForecastListAdapter;
import com.example.germangirod.rxjavaexample.uis.widget.ArrowView;
import com.example.germangirod.rxjavaexample.util.DialogUtil;
import com.example.germangirod.rxjavaexample.util.WeatherImageUtil;
import com.melnykov.fab.FloatingActionButton;
import org.parceler.Parcels;

/**
 * Created by germangirod on 10/19/15.
 */
public class LocationWeatherForecastFragment extends Fragment implements ForecastPresenter, ForecastListAdapter.onRowClick, StorageData {

    private static final String SAVE_FORECAST_RESPONSE_STATE = "forecast_response_state";
    private static final String SAVE_WEATHER_RESPONSE_STATE = "weather_response_state";
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
    @InjectView(R.id.fab) FloatingActionButton fab;
    private ForecastWeatherData forecastWeatherData;
    private ForecastListAdapter forecastListAdapter;
    private WeatherResponse cityWeatherResponse;
    private Forecast forecast;
    private MyLocationDBManager myLocationDBManager;
    private DialogUtil dialogUtil;

    public static Fragment getInstance(WeatherResponse weatherResponse) {

        Fragment locationWeatherForecastFragment = new LocationWeatherForecastFragment();

        Bundle arguments = new Bundle();
        arguments.putParcelable("weather_response", Parcels.wrap(weatherResponse));
        locationWeatherForecastFragment.setArguments(arguments);

        return locationWeatherForecastFragment;
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forecast_fragment, container, false);
        setHasOptionsMenu(true);
        dialogUtil = new DialogUtil();
        ButterKnife.inject(this, v);

        myLocationDBManager = new MyLocationDBManager(getActivity());
        getBundleData();
        setFabButton();
        setToolbar();
        prepareList();
        if (savedInstanceState != null) {

            forecast = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_FORECAST_RESPONSE_STATE));
            cityWeatherResponse = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_WEATHER_RESPONSE_STATE));

            getForecastByCityId(forecast);
        }
        getForecast(String.valueOf(cityWeatherResponse.getId()));

        return v;
    }

    private void setFabButton() {

        if (cityWeatherResponse != null) {

            setFabImage(isSaved(String.valueOf(cityWeatherResponse.getId())));
            fab.setColorPressed(getResources().getColor(R.color.colorPrimaryDark));
            fab.setColorNormal(getResources().getColor(R.color.colorPrimary));
            fab.attachToRecyclerView(recyclerView);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    onFabPress(cityWeatherResponse.getName(), String.valueOf(cityWeatherResponse.getId()));
                }
            });
        }
    }

    private void setFabImage(boolean isSaved) {
        if (isSaved) {
            fab.setImageResource(android.R.drawable.ic_menu_delete);
        } else {
            fab.setImageResource(R.mipmap.save);
        }
    }

    private void onFabPress(String cityName, String cityId) {
        if (isSaved(String.valueOf(cityId))) {
            dialogUtil.createDeleteDialog(getActivity(), myLocationDBManager, cityName, cityId, this);
        } else {
            dialogUtil.createSaveDialog(getActivity(), myLocationDBManager, cityName, cityId, this);
        }
    }

    private boolean isSaved(String cityId) {
        return myLocationDBManager.isSaved(String.valueOf(cityId));
    }

    private void getBundleData() {
        cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        Bundle arguments = getArguments();
        if (arguments != null) {
            cityWeatherResponse = Parcels.unwrap(arguments.getParcelable("weather_response"));
            setNowForecast();
        }
    }

    private void setNowForecast() {

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

    private void setToolbar() {

        ((ActionBarActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getForecast(String cityId) {

        forecastWeatherData = new ForecastWeatherData();
        forecastWeatherData.setView(this);
        forecastWeatherData.getForecastByCityId(cityId);
    }

    @Override public void getForecastByCityId(Forecast forecastResponse) {
        forecast = forecastResponse;
        collapsingToolbarLayout.setTitle(forecast.getCity().getName() + ", " + forecast.getCity().getCountry());
        forecastListAdapter = new ForecastListAdapter(getActivity(), forecast.getWeatherResponses(), cityWeatherResponse);
        forecastListAdapter.setRowClick(this);
        recyclerView.setAdapter(forecastListAdapter);

        fab.attachToRecyclerView(recyclerView);

        loading.setVisibility(View.GONE);
    }

    @Override public void onError(Throwable throwable) {
        loading.setVisibility(View.GONE);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVE_FORECAST_RESPONSE_STATE, Parcels.wrap(forecast));
        outState.putParcelable(SAVE_WEATHER_RESPONSE_STATE, Parcels.wrap(cityWeatherResponse));
    }

    @Override public void clickWeatherRow(View v, int i) {

    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void onStorageActionFinished(String cityId) {
        setFabImage(isSaved(cityId));
    }
}
