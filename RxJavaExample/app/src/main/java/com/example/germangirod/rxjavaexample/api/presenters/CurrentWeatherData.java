package com.example.germangirod.rxjavaexample.api.presenters;

import android.location.Location;
import com.example.germangirod.rxjavaexample.api.WeatherForcastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.CurrentWeather;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 10/7/15.
 */
public class CurrentWeatherData {

    private CurrentWeatherPresenter currentWeatherPresenter;

    public void setView(CurrentWeatherPresenter currentWeatherPresenter){
        this.currentWeatherPresenter=currentWeatherPresenter;
    }

    public void getCurrentWeather(Location location){
        WeatherForcastLocationApi weatherForcastLocationApi = new WeatherForcastLocationApi();
        weatherForcastLocationApi.getCurrentWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CurrentWeather>>() {
                    @Override public void call(final List<CurrentWeather> currentWeathers) {
                        currentWeatherPresenter.getCurrentWeather(currentWeathers);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        currentWeatherPresenter.onError(throwable);
                    }
                });
    }

}
