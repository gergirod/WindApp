package com.example.germangirod.rxjavaexample.api;

import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.api.model.Forecast;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public interface WeatherForcastLocation {

    Observable<WeatherResponse> getLocationWeather(String name);

    Observable<Forecast> getLocationForecast(String name);

    Observable<List<CurrentWeather>> getCurrentWeather(String lat, String lon);

}
