package com.example.germangirod.rxjavaexample.api.model;

import java.util.List;

/**
 * Created by germangirod on 5/15/15.
 */
public class CurrentWeather {

    private int cnt;
    private List<WeatherResponse> list;


    public int getCount() {
        return cnt;
    }

    public void setCount(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherResponse> getWeatherResponse() {
        return list;
    }

    public void setWeatherResponse(List<WeatherResponse> weatherResponse) {
        this.list = weatherResponse;
    }
}
