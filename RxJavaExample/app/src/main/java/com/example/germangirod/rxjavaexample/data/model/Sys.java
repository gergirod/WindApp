package com.example.germangirod.rxjavaexample.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 5/13/15.
 */
@Parcel
public class Sys {

    public String country;
    public int sunrise;
    public int sunset;

    public Sys(){

    }

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

}
