package com.example.germangirod.rxjavaexample.util;

import com.example.germangirod.rxjavaexample.R;
import com.example.germangirod.rxjavaexample.data.model.WeatherResponse;

/**
 * Created by germangirod on 12/14/15.
 */
public class WeatherImageUtil {

    WeatherResponse weatherResponse;

    public WeatherImageUtil(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public int setWeatherImage() {

        if (weatherResponse.isDay()) {
            if (weatherResponse.getClouds().all == 0) {

                return R.drawable.sunny;
            }
            if (weatherResponse.getClouds().all > 0 && weatherResponse.getClouds().all <= 50) {

                return R.drawable.cloudy;
            }
        }else{
           return R.drawable.moon;
        }

        if (weatherResponse.getRain() != null) {

            if (weatherResponse.getClouds().getAll() == 0) {
                return R.drawable.sunny;
            } else if (weatherResponse.getClouds().getAll() > 0 && weatherResponse.getClouds().getAll() < 50) {
                return R.drawable.cloudy;
            } else if (weatherResponse.getClouds().getAll() > 50) {
                return R.drawable.mostly_cloudy;
            } else {
                if (weatherResponse.getRain().getValue() < 0.5) {
                    return R.drawable.slight_drizzle;
                } else {
                    return R.drawable.drizzle;
                }
            }
        } else {
            if (weatherResponse.getClouds().all > 50) {
                return R.drawable.cloudy;
            }
        }

        return 0;
    }
}
