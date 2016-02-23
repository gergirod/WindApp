package com.example.germangirod.rxjavaexample.data.api;

import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;
import com.example.germangirod.rxjavaexample.data.model.Forecast;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public interface WeatherForecastLocation {

    Observable<CurrentWeather> getLocationsWeatherById(String id);

    Observable<WeatherResponse> getLocationCurrentWeather(String lat, String lng);

    Observable<Forecast> getForecastByCityId(String cityId);
}
