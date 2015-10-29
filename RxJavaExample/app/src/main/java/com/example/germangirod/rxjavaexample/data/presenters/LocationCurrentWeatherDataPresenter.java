package com.example.germangirod.rxjavaexample.data.presenters;

import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;

/**
 * Created by germangirod on 10/15/15.
 */
public interface LocationCurrentWeatherDataPresenter {

    void getCurrentWeather(WeatherResponse currentWeathers);

    void onError(Throwable throwable);
}
