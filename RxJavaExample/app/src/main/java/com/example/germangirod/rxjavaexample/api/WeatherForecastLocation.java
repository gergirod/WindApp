package com.example.germangirod.rxjavaexample.api;

import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by germangirod on 5/13/15.
 */
public interface WeatherForecastLocation {

    Observable<List<WeatherResponse>> getLocationsWeatherById(String id);

}
