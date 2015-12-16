package com.example.germangirod.rxjavaexample.data.model;

import java.util.List;
import org.parceler.Parcel;

/**
 * Created by germangirod on 5/15/15.
 */
@Parcel
public class CurrentWeather {

    public int cnt;
    public List<WeatherResponse> list;

    public CurrentWeather(){

    }

    public int getCount() {
        return cnt;
    }

    public List<WeatherResponse> getWeatherResponse() {
        return list;
    }
}
