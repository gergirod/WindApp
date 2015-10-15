package com.example.germangirod.rxjavaexample.api.presenters;

import com.example.germangirod.rxjavaexample.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.api.model.WeatherResponse;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 10/7/15.
 */
public class LocationsCurrentWeatherData {

    private LocationsCurrentWeatherPresenter locationsCurrentWeatherPresenter;

    public void setView(LocationsCurrentWeatherPresenter locationsCurrentWeatherPresenter) {
        this.locationsCurrentWeatherPresenter = locationsCurrentWeatherPresenter;
    }

    public void getCurrentWeather(String id) {
        WeatherForecastLocationApi weatherForecastLocationApi = new WeatherForecastLocationApi();
        weatherForecastLocationApi.getLocationsWeatherById(id).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<WeatherResponse>>() {
                    @Override public void call(List<WeatherResponse> weatherResponses) {
                        locationsCurrentWeatherPresenter.getCurrentWeather(weatherResponses);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        locationsCurrentWeatherPresenter.onError(throwable);
                    }
                });
    }
}
