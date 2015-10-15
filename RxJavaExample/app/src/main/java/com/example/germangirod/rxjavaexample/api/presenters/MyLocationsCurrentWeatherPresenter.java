package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;

/**
 * Created by germangirod on 10/7/15.
 */
public interface MyLocationsCurrentWeatherPresenter {

    void getCurrentWeather(CurrentWeather currentWeather);

    void onError(Throwable throwable);

}
