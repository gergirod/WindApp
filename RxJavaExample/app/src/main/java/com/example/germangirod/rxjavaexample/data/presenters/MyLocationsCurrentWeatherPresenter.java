package com.example.germangirod.rxjavaexample.data.presenters;

import com.example.germangirod.rxjavaexample.data.model.CurrentWeather;

/**
 * Created by germangirod on 10/7/15.
 */
public interface MyLocationsCurrentWeatherPresenter {

    void getCurrentWeather(CurrentWeather currentWeather);

    void onError(Throwable throwable);

}
