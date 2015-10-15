package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 10/7/15.
 */
public class MyLocationsCurrentWeatherData {

    private MyLocationsCurrentWeatherPresenter locationsCurrentWeatherPresenter;

    public void setView(MyLocationsCurrentWeatherPresenter locationsCurrentWeatherPresenter) {
        this.locationsCurrentWeatherPresenter = locationsCurrentWeatherPresenter;
    }

    public void getMyCurrentWeatherList(String id) {
        WeatherForecastLocationApi weatherForecastLocationApi = new WeatherForecastLocationApi();
        weatherForecastLocationApi.getLocationsWeatherById(id).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CurrentWeather>() {
                    @Override public void call(CurrentWeather currentWeather) {
                        locationsCurrentWeatherPresenter.getCurrentWeather(currentWeather);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        locationsCurrentWeatherPresenter.onError(throwable);
                    }
                });
    }

}
