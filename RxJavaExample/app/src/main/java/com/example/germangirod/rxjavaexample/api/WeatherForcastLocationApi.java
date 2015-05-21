package com.example.germangirod.rxjavaexample.api;

import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.api.model.Forecast;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public class WeatherForcastLocationApi implements WeatherForcastLocation {

    @Override public Observable<WeatherResponse> getLocationWeather(String name) {
        return RestClient.get().getWeather(name);
    }

    @Override public Observable<Forecast> getLocationForecast(String name) {
        return RestClient.get().getForecast(name);
    }

    @Override public Observable<List<CurrentWeather>> getCurrentWeather(String lat, String lon) {
        return RestClient.get().getCurrentWeather(lat,lon);
    }
}
