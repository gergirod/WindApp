package com.example.germangirod.rxjavaexample.api.model;

/**
 * Created by germangirod on 5/13/15.
 */
public class Sys {

    private String country;
    private int sunrise;
    private int sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }
}
