package com.example.germangirod.rxjavaexample.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 5/15/15.
 */
public class CurrentWeather {

    private Station station;
    private Double distance;
    @SerializedName("last")
    private WeatherResponse weatherResponse;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}
