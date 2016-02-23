package com.example.germangirod.rxjavaexample.data.model;

import org.parceler.Parcel;

/**
 * Created by germangirod on 5/1/15.
 */
@Parcel
public class Weather {

    public int id;
    public String main;
    public String description;

    public Weather(){

    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
