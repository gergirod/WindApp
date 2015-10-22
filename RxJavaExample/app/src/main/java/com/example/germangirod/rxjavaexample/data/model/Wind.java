package com.example.germangirod.rxjavaexample.data.model;

/**
 * Created by germangirod on 5/13/15.
 */
public class Wind {

    public Double speed;
    public Double deg;

    public Double getSpeed() {
        return minutesPerSecondToKnots();
    }

    public Double getDeg() {
        return deg;
    }

    private Double minutesPerSecondToKnots(){
        return speed * 1.943844 ;
    }
}
