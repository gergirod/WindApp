package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;

/**
 * Created by germangirod on 10/7/15.
 */
public interface MyLocationsCurrentWeatherPresenter {

    void getCurrentWeather(List<WeatherResponse> currentWeathers);

    void onError(Throwable throwable);

}
