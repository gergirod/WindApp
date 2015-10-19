package com.example.germangirod.rxjavaexample.uis.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.api.presenters.LocationCurrentWeatherData;
import com.example.germangirod.rxjavaexample.api.presenters.LocationCurrentWeatherDataPresenter;
import com.example.germangirod.rxjavaexample.uis.ForecastActivity;

/**
 * Created by germangirod on 5/13/15.
 */
public class CurrentLocationWeatherFragment extends LocationBaseFragment implements LocationCurrentWeatherDataPresenter {

    private LocationCurrentWeatherData locationCurrentWeatherData;
    private Location auxLocation;
    @InjectView(R.id.card_view) CardView cardView;

    public static Fragment getInstance() {
        CurrentLocationWeatherFragment f = new CurrentLocationWeatherFragment();
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.current_location_fragment, container, false);
        ButterKnife.inject(this, v);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                ForecastActivity.goTo(getActivity(), "123");
            }
        });

        return v;
    }

    @Override public void onLocationChanged(Location location) {
        searchCurrentWeather(location);
    }

    private void searchCurrentWeather(Location location) {
        float distance = 0;
        if (auxLocation != null) {
            distance = location.distanceTo(auxLocation);
        } else {
            distance = -1;
            auxLocation = location;
        }

        if (distance > 3000 || distance == -1) {
            getCurrentWeather(location);
        }
    }

    private void getCurrentWeather(Location location) {
        locationCurrentWeatherData = new LocationCurrentWeatherData();
        locationCurrentWeatherData.setView(this);
        locationCurrentWeatherData.getCurrentWeatherData(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
    }

    @Override public void getCurrentWeather(WeatherResponse currentWeathers) {
        //Todo show data in the card
    }

    @Override public void onError(Throwable throwable) {
        
    }
}
