package com.example.germangirod.rxjavaexample.data.api;

import com.example.germangirod.rxjavaexample.BuildConfig;
import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.data.model.Forecast;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public class WeatherForecastLocationApi implements WeatherForecastLocation {

    @Override public Observable<CurrentWeather> getLocationsWeatherById(String id) {
        return RestClient.get().getCityListById(id, BuildConfig.APP_KEY);
    }

    @Override public Observable<WeatherResponse> getLocationCurrentWeather(String lat, String lng) {
        return RestClient.get().getWeatherByCoordinates(lat, lng, BuildConfig.APP_KEY);
    }

    @Override public Observable<Forecast> getForecastByCityId(String cityId) {
        return RestClient.get().getCityForecastById(cityId, BuildConfig.APP_KEY);
    }
}
