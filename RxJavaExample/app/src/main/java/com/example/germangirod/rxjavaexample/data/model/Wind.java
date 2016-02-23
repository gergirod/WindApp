package com.example.germangirod.rxjavaexample.data.model;

import java.text.DecimalFormat;
import org.parceler.Parcel;

/**
 * Created by germangirod on 5/13/15.
 */
@Parcel
public class Wind {

    public Double speed;
    public float deg;

    public Wind(){

    }

    public String getSpeed() {
        return minutesPerSecondToKnots();
    }

    public float getDeg() {
        return deg-180;
    }

    public String degToString(){

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(deg)+ "° "+getWindDireccion();
    }

    private String minutesPerSecondToKnots(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(speed * 1.943844)+" Knots";
    }

    public String getWindDireccion(){
        if(deg ==0 ){
            return "N";
        }
        if(deg>0 && deg<90){
            return "NE";
        }
        if(deg==90){
            return "E";
        }
        if(deg>90 && deg<180 ){
            return "SE";
        }
        if(deg==180){
            return "S";
        }

        if(deg>180 && deg < 270){
            return "SW";
        }

        if(deg==270){
            return "W";
        }
        if(deg>270 && deg <369){
            return "NW";
        }

        return "";
    }
}
