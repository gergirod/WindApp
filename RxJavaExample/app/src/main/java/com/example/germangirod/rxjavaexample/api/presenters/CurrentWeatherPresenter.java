package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import java.util.List;

/**
 * Created by germangirod on 10/7/15.
 */
public interface CurrentWeatherPresenter {

    void getCurrentWeather(List<CurrentWeather> currentWeathers);

    void onError(Throwable throwable);

}
