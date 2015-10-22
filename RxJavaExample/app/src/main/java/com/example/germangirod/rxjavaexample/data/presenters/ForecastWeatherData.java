package com.example.germangirod.rxjavaexample.data.presenters;

import com.example.germangirod.rxjavaexample.data.api.WeatherForecastLocation;
import com.example.germangirod.rxjavaexample.data.api.WeatherForecastLocationApi;
import com.example.germangirod.rxjavaexample.data.model.Forecast;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by germangirod on 10/19/15.
 */
public class ForecastWeatherData {

    ForecastPresenter forecastPresenter;

    public void setView(ForecastPresenter forecastPresenter) {
        this.forecastPresenter = forecastPresenter;
    }

    public void getForecastByCityId(String cityId) {

        WeatherForecastLocation weatherForecastLocation = new WeatherForecastLocationApi();

        weatherForecastLocation.getForecastByCityId(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Forecast>() {
                    @Override public void call(Forecast forecast) {
                        forecastPresenter.getForecastByCityId(forecast);
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        forecastPresenter.onError(throwable);
                    }
                });
    }
}
