package com.example.germangirod.rxjavaexample.data.model;

import java.util.List;

/**
 * Created by germangirod on 5/15/15.
 */
public class CurrentWeather {

    public int cnt;
    public List<WeatherResponse> list;

    public int getCount() {
        return cnt;
    }

    public List<WeatherResponse> getWeatherResponse() {
        return list;
    }
}
