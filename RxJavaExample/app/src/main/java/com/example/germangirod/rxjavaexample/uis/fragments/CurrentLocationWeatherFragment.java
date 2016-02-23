package com.example.germangirod.rxjavaexample.uis.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import com.example.germangirod.rxjavaexample.data.presenters.LocationCurrentWeatherData;
import com.example.germangirod.rxjavaexample.data.presenters.LocationCurrentWeatherDataPresenter;
import com.example.germangirod.rxjavaexample.uis.ForecastActivity;
import com.example.germangirod.rxjavaexample.uis.widget.ArrowView;
import com.example.germangirod.rxjavaexample.util.WeatherImageUtil;
import com.google.android.gms.location.LocationServices;
import org.parceler.Parcels;

/**
 * Created by germangirod on 5/13/15.
 */
public class CurrentLocationWeatherFragment extends LocationBaseFragment implements LocationCurrentWeatherDataPresenter, View.OnClickListener {

    private static final String SAVE_WEATHER_RESPONSE_STATE = "weather_response_state";
    @InjectView(R.id.card_view) CardView cardView;
    @InjectView(R.id.city_name) TextView cityName;
    @InjectView(R.id.arrow_wind) ArrowView arrowWind;
    @InjectView(R.id.wind_speed) TextView windSpeed;
    @InjectView(R.id.wind_dg) TextView windDg;
    @InjectView(R.id.weather_image) ImageView weatherImage;
    @InjectView(R.id.temperature) TextView temperature;
    @InjectView(R.id.pressure) TextView pressure;
    @InjectView(R.id.humidity) TextView humidity;
    @InjectView(R.id.date) TextView date;
    private LocationCurrentWeatherData locationCurrentWeatherData;
    private Location auxLocation;
    private WeatherResponse currentWeather;

    public static Fragment getInstance() {
        CurrentLocationWeatherFragment f = new CurrentLocationWeatherFragment();
        return f;
    }

    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.current_location_fragment, container, false);
        ButterKnife.inject(this, v);

        cardView.setOnClickListener(this);

        if (savedInstanceState != null) {
            currentWeather = Parcels.unwrap(savedInstanceState.getParcelable(SAVE_WEATHER_RESPONSE_STATE));
            getCurrentWeather(currentWeather);
        }

        return v;
    }

    @Override public void onConnected(Bundle bundle) {
        super.onConnected(bundle);
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            searchCurrentWeather(location);
        }
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
        currentWeather = currentWeathers;
        cityName.setText(currentWeather.getName());
        arrowWind.setVisibility(View.VISIBLE);
        arrowWind.setImageResource(R.drawable.arrow);
        arrowWind.setAngleRotation(currentWeather.getWind().getDeg());
        windSpeed.setText(currentWeather.getWind().getSpeed());
        windDg.setText(currentWeather.getWind().degToString());
        pressure.setText("Press: " + currentWeather.getMain().getPressure());
        humidity.setText("Hum: " + currentWeather.getMain().getHumidity());
        temperature.setText("Temp: " + currentWeather.getMain().getTemp());
        date.setText(currentWeather.getFullDate());

        setTextAndBackgroundColors(currentWeathers.isDay());

        WeatherImageUtil weatherImageUtil = new WeatherImageUtil(currentWeather);
        weatherImage.setImageResource(weatherImageUtil.setWeatherImage());
    }

    @Override public void onError(Throwable throwable) {

    }

    private void setTextAndBackgroundColors(boolean isDay) {
        if (!isDay) {
            cityName.setTextColor(getResources().getColor(R.color.white));
            windSpeed.setTextColor(getResources().getColor(R.color.white));
            windDg.setTextColor(getResources().getColor(R.color.white));
            cardView.setBackgroundColor(getResources().getColor(R.color.black));
            pressure.setTextColor(getResources().getColor(R.color.white));
            temperature.setTextColor(getResources().getColor(R.color.white));
            humidity.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override public void onClick(View v) {
        if (currentWeather != null) {
            ForecastActivity.goTo(getActivity(), currentWeather);
        }
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVE_WEATHER_RESPONSE_STATE, Parcels.wrap(currentWeather));
    }
}
