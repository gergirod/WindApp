package com.example.germangirod.rxjavaexample.api;

import com.example.germangirod.rxjavaexample.BuildConfig;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public class WeatherForecastLocationApi implements WeatherForecastLocation {

    @Override public Observable<List<WeatherResponse>> getLocationsWeatherById(String id) {
        return RestClient.get().getCityListById(id, BuildConfig.APP_KEY);
    }

    @Override public Observable<WeatherResponse> getLocationCurrentWeather(String lat, String lng) {
        return RestClient.get().getWeatherByCoordinates(lat, lng, BuildConfig.APP_KEY);
    }
}
