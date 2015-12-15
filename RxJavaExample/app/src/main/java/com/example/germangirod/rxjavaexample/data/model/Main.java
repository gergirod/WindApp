package com.example.germangirod.rxjavaexample.data.model;

import java.text.DecimalFormat;
import org.parceler.Parcel;

/**
 * Created by germangirod on 5/13/15.
 */
@Parcel
public class Main {

    public Double temp;
    public Double humidity;
    public Double pressure;
    public Double temp_min;
    public Double temp_max;

    public Main(){

    }

    public String getTemp() {
        return getTemperatureInCelsius()+"°C";
    }

    public String getHumidity() {
        return humidity+"%";
    }

    public String getPressure() {
        return pressure+" hPa";
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    private String getTemperatureInCelsius(){
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return decimalFormat.format(temp- 273.15);

    }


}
