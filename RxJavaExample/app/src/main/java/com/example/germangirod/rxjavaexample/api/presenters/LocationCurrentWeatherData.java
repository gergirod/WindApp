package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.WeatherForecastLocation;
import com.example.germangirod.rxjavaexample.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 10/15/15.
 */
public class LocationCurrentWeatherData {

    LocationCurrentWeatherDataPresenter locationCurrentWeatherDataPresenter;

    public void setView(LocationCurrentWeatherDataPresenter locationCurrentWeatherDataPresenter) {
        this.locationCurrentWeatherDataPresenter = locationCurrentWeatherDataPresenter;
    }

    public void getCurrentWeatherData(String lat, String lng) {

        WeatherForecastLocation weatherForecastLocation = new WeatherForecastLocationApi();
        weatherForecastLocation.getLocationCurrentWeather(lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeatherResponse>() {
                    @Override public void call(WeatherResponse weatherResponse) {
                        locationCurrentWeatherDataPresenter.getCurrentWeather(weatherResponse);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        locationCurrentWeatherDataPresenter.onError(throwable);
                    }
                });
    }
}
